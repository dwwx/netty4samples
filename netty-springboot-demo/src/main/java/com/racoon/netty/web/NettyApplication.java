package com.racoon.netty.web;

import com.racoon.netty.server.WebNettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.net.InetSocketAddress;

@SpringBootApplication
@ComponentScan("com.racoon.netty")
public class NettyApplication implements CommandLineRunner {
    @Value("${netty.host}")
    private String host;
    @Value("${netty.port}")
    private int port;
    @Autowired
    private WebNettyServer nettyServer;

    public void run(String... args) throws Exception {
        //相当于重写的run方法
        InetSocketAddress address = new InetSocketAddress(host, port);
        ChannelFuture channelFuture = nettyServer.bind(address);
        Runtime.getRuntime().addShutdownHook(new Thread(()->nettyServer.destory()));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }

}
