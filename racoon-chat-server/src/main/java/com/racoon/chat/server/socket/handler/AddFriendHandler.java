package com.racoon.chat.server.socket.handler;

import com.racoon.chat.protocol.friend.AddFriendRequest;
import com.racoon.chat.protocol.friend.AddFriendResponse;
import com.racoon.chat.server.application.UserService;
import com.racoon.chat.server.domain.user.model.UserInfo;
import com.racoon.chat.server.infrastructure.common.SocketChannelUtil;
import com.racoon.chat.server.infrastructure.po.UserFriend;
import com.racoon.chat.server.socket.MyBizHandler;
import io.netty.channel.Channel;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AddFriendHandler extends MyBizHandler<AddFriendRequest> {

    public AddFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendRequest msg) {
        // 1. 添加好友到数据库中[A->B B->A]
        List<UserFriend> userFriendList = new ArrayList<>();
        userFriendList.add(new UserFriend(msg.getUserId(), msg.getFriendId()));
        userFriendList.add(new UserFriend(msg.getFriendId(), msg.getUserId()));
        userService.addUserFriend(userFriendList);
        // 2. 推送好友添加完成 A
        UserInfo userInfo = userService.queryUserInfo(msg.getFriendId());
        channel.writeAndFlush(new AddFriendResponse(userInfo.getUserId(), userInfo.getUserNickName(), userInfo.getUserHead()));
        // 3. 推送好友添加完成 B
        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if (null == friendChannel) return;
        UserInfo friendInfo = userService.queryUserInfo(msg.getUserId());
        friendChannel.writeAndFlush(new AddFriendResponse(friendInfo.getUserId(), friendInfo.getUserNickName(), friendInfo.getUserHead()));
    }
}
