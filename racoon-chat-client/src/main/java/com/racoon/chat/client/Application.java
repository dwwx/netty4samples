package com.racoon.chat.client;

import com.racoon.chat.client.application.UIService;
import com.racoon.chat.client.event.ChatEvent;
import com.racoon.chat.client.event.LoginEvent;
import com.racoon.chat.client.infrastructure.util.CacheUtil;
import com.racoon.chat.client.socket.NettyClient;
import com.racoon.chat.protocol.login.ReconnectRequest;
import com.racoon.ui.view.achat.ChatController;
import com.racoon.ui.view.achat.IChatMethod;
import com.racoon.ui.view.alogin.ILoginMethod;
import com.racoon.ui.view.alogin.LoginController;
import io.netty.channel.Channel;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Application extends javafx.application.Application {
    private Logger logger = LoggerFactory.getLogger(Application.class);

    //默认线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. 启动窗口
        //ChatEvent和LoginEvent分别实现了UI里面的IChatEvent和ILoginEvent
        //在UI调用时进行多态加载
        //ChatController是IChatMethod和ChatInit 构造时传入了ChatEvent()
        IChatMethod chat = new ChatController(new ChatEvent());

        ILoginMethod login = new LoginController(new LoginEvent(), chat);

        login.doShow();

        //封装的UIService包装了ChatController和LoginController
        UIService uiService = new UIService();
        uiService.setChat(chat);
        uiService.setLogin(login);
        // 2. 启动socket连接
        logger.info("NettyClient连接服务开始 inetHost：{} inetPort：{}", "127.0.0.1", 7397);
        // 启动nettyClient 再把uiService封装到NettyClient
        // uiService会封装到每一个Handler中，在每一个Handler中对Chat和Login的UI接口进行操作
        NettyClient nettyClient = new NettyClient(uiService);
        Future<Channel> future = executorService.submit(nettyClient);

        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("netty client start error channel is null");

        while (!nettyClient.isActive()) {
            logger.info("NettyClient启动服务 ...");
            Thread.sleep(500);
        }
        logger.info("NettyClient连接服务完成 {}", channel.localAddress());

        // Channel状态定时巡检；3秒后每5秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            while (!nettyClient.isActive()) {
                System.out.println("通信管道巡检：通信管道状态 " + nettyClient.isActive());
                try {
                    System.out.println("通信管道巡检：断线重连[Begin]");
                    Channel freshChannel = executorService.submit(nettyClient).get();
                    if (null == CacheUtil.userId) continue;
                    freshChannel.writeAndFlush(new ReconnectRequest(CacheUtil.userId));
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("通信管道巡检：断线重连[Error]");
                }
            }
        }, 3, 5, TimeUnit.SECONDS);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
