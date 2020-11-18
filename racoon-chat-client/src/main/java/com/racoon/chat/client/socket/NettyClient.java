package com.racoon.chat.client.socket;

import com.racoon.chat.client.application.UIService;
import com.racoon.chat.client.infrastructure.util.BeanUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class NettyClient implements Callable<Channel> {
    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private String inetHost = "127.0.0.1";
    private int inetPort = 7397;

    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    private UIService uiService;

    public NettyClient(UIService uiService) {
        this.uiService = uiService;
    }
    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try{
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new ClientChannelInitializer(uiService));
            //开始的时候就进行连接
            channelFuture = b.connect(inetHost, inetPort).syncUninterruptibly();
            this.channel = channelFuture.channel();
            //启动的时候添加到BeanUtil中，方便后续处理的时候进行提取，在程序中就可以随时获取到这个客户端的连接Channel
            BeanUtil.addBean("channel", channel);
        }catch (Exception e){
            logger.error("socket client start error", e.getMessage());
        }finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("socket client start done. ");
            } else {
                logger.error("socket client start error. ");
            }
        }
        return channel;
    }
    public void destory(){
        if(null == channel)return;
        channel.close();
        workerGroup.shutdownGracefully();
    }
    public boolean isActive(){
        return channel.isActive();
    }
    public Channel channel(){
        return channel;
    }
}
