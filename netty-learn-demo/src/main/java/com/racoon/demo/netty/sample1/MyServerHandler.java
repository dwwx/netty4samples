package com.racoon.demo.netty.sample1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
        //当有客户端来进行连接的时候，添加到ChannelGroup通信组
        ChannelHandler.channelGroup.add(ctx.channel());
        //当channel激活的时候进行反馈 日志信息
        SocketChannel ch = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + ch.localAddress().getHostName());
        System.out.println("链接报告Port:" + ch.localAddress().getPort());
        System.out.println("链接报告完毕");

        //通知客户端建立连接成功,再通过服务端添加编码器,就省略了自己封装的一个过程，反馈客户端
        String str = "通知客户端建立连接成功"+new Date()+ch.localAddress().getHostName()+"\r\n";
//        ByteBuf buf = Unpooled.buffer(str.getBytes().length);
//        buf.writeBytes(str.getBytes("GBK"));
//        ctx.writeAndFlush(buf);
        ctx.writeAndFlush(str);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接"+ctx.channel().localAddress().toString());
        //当有客户端退出的时候，从ChannelGroup中移除
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息 \r\n"+cause.getMessage());
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
       //给客户端回消息
        String string = "服务单收到你发的消息"+msg+"\r\n";
//        ctx.writeAndFlush(string);
        //进行一个群发消息
        ChannelHandler.channelGroup.writeAndFlush(string);
    }
}
