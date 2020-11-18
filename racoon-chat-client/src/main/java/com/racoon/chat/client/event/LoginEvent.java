package com.racoon.chat.client.event;

import com.racoon.chat.client.infrastructure.util.BeanUtil;
import com.racoon.chat.client.infrastructure.util.CacheUtil;
import com.racoon.chat.protocol.login.LoginRequest;
import com.racoon.ui.view.alogin.ILoginEvent;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//UI里面最终调用的是这个LoginEvent实现类
public class LoginEvent implements ILoginEvent {
    private Logger logger = LoggerFactory.getLogger(LoginEvent.class);
    @Override
    public void doLoginCheck(String userId, String userPassword) {
        //从缓存中得到channel,这个是客户端缓存自己的channel
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        //发送LoginRequest请求对象给服务器端
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
        //每个客户端缓存自己的userId
        CacheUtil.userId = userId;
    }
}
