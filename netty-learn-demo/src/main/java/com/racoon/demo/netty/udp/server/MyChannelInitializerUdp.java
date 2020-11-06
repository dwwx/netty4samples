package com.racoon.demo.netty.udp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class MyChannelInitializerUdp extends ChannelInitializer<NioDatagramChannel> {
    private EventLoopGroup group = new NioEventLoopGroup();
    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ch.pipeline().addLast(group, new MyServerHandlerUdp());
    }
}
