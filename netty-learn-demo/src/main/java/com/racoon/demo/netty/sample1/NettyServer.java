package com.racoon.demo.netty.sample1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty开发中，客户端与服务端需要保持同样的；半包粘包处理，编码解码处理、收发数据方式，这样才能保证数据通信正常。
 * 可以自定义编码解码器处理半包，粘包问题
 * 在实际应用场景里，只要是支持sokcet通信的都可以和Netty交互，比如中继器、下位机、PLC等
 * 数据传输过程中有各种情况；整包数据、半包数据、粘包数据，比如我们设定开始符号02、结束符号03；
 * 整包数据；02 89 78 54 03
 * 半包数据；02 89 78
 * 粘包数据；02 89 78 54 03 02 89
 */

public class NettyServer {
    public static void main(String[] args) {
        new NettyServer().bind(7397);
    }
    private void bind(int port){
        //配置服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) //设置非阻塞模式
                    .option(ChannelOption.SO_BACKLOG, 128) //设置最大连接个数
                    .childHandler(new MyChannelInitializer());
            ChannelFuture f = server.bind(port).sync();
            f.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
