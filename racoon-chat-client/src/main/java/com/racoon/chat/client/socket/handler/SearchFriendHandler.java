package com.racoon.chat.client.socket.handler;

import com.racoon.chat.client.application.UIService;
import com.racoon.chat.protocol.friend.SearchFriendResponse;
import com.racoon.chat.protocol.friend.dto.UserDto;
import com.racoon.ui.view.achat.IChatMethod;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class SearchFriendHandler extends SimpleChannelInboundHandler<SearchFriendResponse> {

    private UIService uiService;

    public SearchFriendHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SearchFriendResponse msg) throws Exception {
        List<UserDto> list = msg.getList();
        if (null == list || list.isEmpty()) return;
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            for (UserDto user : list) {
                chat.addLuckFriend(user.getUserId(), user.getUserNickName(), user.getUserHead(), user.getStatus());
            }
        });
    }

}
