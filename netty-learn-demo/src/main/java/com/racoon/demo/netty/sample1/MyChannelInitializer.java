package com.racoon.demo.netty.sample1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

//继承ChannelInitializer的类，对channel的初始化连接做一个监控
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + ch.localAddress().getHostName());
        System.out.println("链接报告Port:" + ch.localAddress().getPort());
        System.out.println("链接报告完毕");
        //此时得到的Channel可以拿到，再往pipeline里面增加相应的链路
        ch.pipeline().addLast(new MyServerHandler());
    }
}
