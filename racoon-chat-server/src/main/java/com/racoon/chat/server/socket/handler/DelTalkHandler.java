package com.racoon.chat.server.socket.handler;

import com.racoon.chat.protocol.talk.DelTalkRequest;
import com.racoon.chat.server.application.UserService;
import com.racoon.chat.server.socket.MyBizHandler;
import io.netty.channel.Channel;


/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class DelTalkHandler extends MyBizHandler<DelTalkRequest> {

    public DelTalkHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, DelTalkRequest msg) {
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId());
    }
}
