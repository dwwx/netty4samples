package com.racoon.chat.client.event;

import com.racoon.chat.client.infrastructure.util.BeanUtil;
import com.racoon.chat.client.infrastructure.util.CacheUtil;
import com.racoon.chat.protocol.login.LoginRequest;
import com.racoon.ui.view.alogin.ILoginEvent;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginEvent implements ILoginEvent {
    private Logger logger = LoggerFactory.getLogger(LoginEvent.class);
    @Override
    public void doLoginCheck(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
        CacheUtil.userId = userId;
    }
}
