package com.racoon.chat.client.socket.handler;

import com.racoon.chat.client.application.UIService;
import com.racoon.chat.client.socket.MyBizHandler;
import com.racoon.chat.protocol.friend.AddFriendResponse;
import com.racoon.ui.view.achat.IChatMethod;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 *
 * 添加好友
 */
public class AddFriendHandler extends MyBizHandler<AddFriendResponse> {

    public AddFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendResponse msg) {
        //是ChatController实现了IChatMethod
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addFriendUser(true, msg.getFriendId(), msg.getFriendNickName(), msg.getFriendHead());
        });
    }
}
