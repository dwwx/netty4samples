package com.racoon.chat.client.socket.handler;

import com.racoon.chat.client.application.UIService;
import com.racoon.chat.client.socket.MyBizHandler;
import com.racoon.chat.protocol.friend.AddFriendResponse;
import com.racoon.ui.view.achat.IChatMethod;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 *
 * 添加好友
 */
public class AddFriendHandler extends MyBizHandler<AddFriendResponse> {

    public AddFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendResponse msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addFriendUser(true, msg.getFriendId(), msg.getFriendNickName(), msg.getFriendHead());
        });
    }

}
