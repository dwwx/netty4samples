package com.racoon.tomcat;

import com.racoon.tomcat.http.GPRequest;
import com.racoon.tomcat.http.GPResponse;
import com.racoon.tomcat.http.GPServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//Netty就是一个同时支持多协议的网络通信框架
public class GPTomcat {
    private int port = 8080;
    private Map<String, GPServlet> servletMap = new HashMap<String, GPServlet>();
    private Properties webXml = new Properties();

    //加载properties文件进行初始化
    private void init(){
        try{
            Class clazz = this.getClass();
            URL u = clazz.getResource("/");
            String path = u.getPath();
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF+"web.properties");
            webXml.load(fis);
            for (Object k: webXml.keySet()){
                String key = k.toString();
                if(key.endsWith(".url")){
                    String servletName = key.replaceAll("\\.url$","");
                    String url = webXml.getProperty(key);
                    //得到的是某一个class的名字
                    String className = webXml.getProperty(servletName+".className");
                    GPServlet o = (GPServlet) Class.forName(className).newInstance();
                    servletMap.put(url,o);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void start(){
        init();

        //Netty封装了NIO Reactor模型
        //boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //创建netty服务
            ServerBootstrap server = new ServerBootstrap();
            //配置参数 链路式编程
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 无锁化串行编程
                            //Netty对HTTP协议的封装，顺序有要求
                            // HttpResponseEncoder 编码器
                            // 责任链模式，双向链表Inbound OutBound
                            ch.pipeline().addLast(new HttpRequestEncoder());
                            ch.pipeline().addLast(new HttpRequestDecoder());
                            //业务逻辑处理
                            ch.pipeline().addLast(new GPTomcatHandler());
                        }
                    })
                    //针对主线程的配置，分配线程的最大数量128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //针对子线程的配置 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //启动服务
            ChannelFuture f = server.bind(port).sync();
            System.out.println("racoon tomcat is start, listening port is :"+ port);
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public class GPTomcatHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if(msg instanceof HttpRequest){
                System.out.println("hello");
                HttpRequest req = (HttpRequest) msg;
                GPRequest request = new GPRequest(ctx, req);
                GPResponse response = new GPResponse(ctx, req);
                String url = request.getUrl();
                if(servletMap.containsKey(url)){
                    servletMap.get(url).service(request,response);
                }else {
                    response.write("404-Not Found");
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
