package com.racoon.chat.server.socket;

import com.racoon.chat.server.application.UserService;
import com.racoon.chat.server.infrastructure.common.SocketChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//抽象类供其子类实现channelRead方法，因为其他方法都是一样的，可能每个Handler的ChannelRead方法的实现逻辑不一样
public abstract class MyBizHandler<T> extends SimpleChannelInboundHandler<T> {
    protected Logger logger = LoggerFactory.getLogger(MyBizHandler.class);
    protected UserService userService;
    public MyBizHandler(UserService userService){
        this.userService = userService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("客户端连接通知：{}", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        SocketChannelUtil.removeChannel(ctx.channel().id().toString());
        SocketChannelUtil.removeChannelGroupByChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("服务端异常断开", cause.getMessage());
        SocketChannelUtil.removeChannel(ctx.channel().id().toString());
        SocketChannelUtil.removeChannelGroupByChannel(ctx.channel());
    }
    public abstract void channelRead(Channel channel, T msg);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }
}
