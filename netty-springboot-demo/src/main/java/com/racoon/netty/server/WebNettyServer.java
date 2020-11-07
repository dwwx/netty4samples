package com.racoon.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component("webNettyServer")
public class WebNettyServer {
    private Logger logger = LoggerFactory.getLogger(WebNettyServer.class);
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;
    //提供对外接口
    public ChannelFuture bind(InetSocketAddress address){
        ChannelFuture channelFuture = null;
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new WebChannelInitializer());
            channelFuture = b.bind(address).syncUninterruptibly();
            channel = channelFuture.channel();
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            if(null != channelFuture && channelFuture.isSuccess()){
                logger.error("server start done");
            }else {
                logger.error("server start error");
            }
        }
        return channelFuture;
    }
    public void destory(){
        if(null == channel)return;
        channel.close();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
    public Channel getChannel(){
        return channel;
    }
}
