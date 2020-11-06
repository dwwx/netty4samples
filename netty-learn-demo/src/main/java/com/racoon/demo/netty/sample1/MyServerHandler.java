package com.racoon.demo.netty.sample1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;


import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

//继承ChannelInboundHandlerAdapter让我的Handler进行入栈消息的读取
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当channel激活的时候进行反馈
        SocketChannel ch = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + ch.localAddress().getHostName());
        System.out.println("链接报告Port:" + ch.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //System.out.println(msg);
        //接收消息,读数据这一块还不是还不懂,再加了Handler后就不用自己处理数据了
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] msgByte = new byte[buf.readableBytes()];
//        buf.readBytes(msgByte);
//        System.out.print(new Date() + "接收到消息:");
//        System.out.println(new String(msgByte, Charset.forName("GBK")));
       System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 接收到消息："+msg);
    }
}
