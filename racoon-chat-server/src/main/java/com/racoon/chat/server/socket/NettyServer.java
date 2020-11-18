package com.racoon.chat.server.socket;

import com.racoon.chat.server.application.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

@Service("nettyServer")
public class NettyServer implements Callable<Channel> {
    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Resource
    private UserService userService;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private final EventLoopGroup workGroup = new NioEventLoopGroup();

    private Channel channel;

    //这里面就是传的数据库的接口UserService,提供一个统一的接口调用
    public NettyServer(UserService userService){
        this.userService =userService;
    }
    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ServerChannelInitializer(userService));

            channelFuture = b.bind(new InetSocketAddress(7397)).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            logger.error("socket server start error", e.getMessage());
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("socket server start done. ");
            } else {
                logger.error("socket server start error. ");
            }
        }
        return channel;
    }
    public void destroy() {
        if (null == channel) return;
        channel.close();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    public Channel channel() {
        return channel;
    }
}
