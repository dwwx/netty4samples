package com.racoon.demo.netty.sample1;

import com.racoon.demo.netty.codec.MyDecoder;
import com.racoon.demo.netty.codec.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//继承ChannelInitializer的类，对channel的初始化连接做一个监控
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    //netty可以把消息进行转String,还能在网络传输过程中合理处理半包粘包
    //不仅在String下有处理半包粘包的解码器在处理其他的数据格式也有，其中谷歌的protobuf数据格式就是其中一个。对于String的有以下常用的三种：
    //LineBasedFrameDecoder 基于换行
    //DelimiterBasedFrameDecoder 基于指定字符串
    //FixedLengthFrameDecoder 基于字符串长度
    protected void initChannel(SocketChannel ch) throws Exception {
//        //在Channel的初始化的时候进行Handler的添加
//        //基于换行符的，LineBasedFrameDecoder。在使用网络调试助手发消息时，必须在消息后加一个\n换行符
//        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//        //基于指定字符的
//        //ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,false, Delimiters.lineDelimiter()));
//        //基于最大长度的
//        //ch.pipeline().addLast(new FixedLengthFrameDecoder(4));
//        //网络调试助手使用的是GBK编码，对客户端发过来的消息进行解码
//        ch.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
//        //增加自己的编码格式，这里再转换成自己的编码
//        ch.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
//        //此时得到的Channel可以拿到，再往pipeline里面增加相应的链路
//        ch.pipeline().addLast(new MyServerHandler());
        //添加自定义的编码解码器
        ch.pipeline().addLast(new MyDecoder());
        ch.pipeline().addLast(new MyEncoder());
        ch.pipeline().addLast(new MyServerHandler());
    }
}
