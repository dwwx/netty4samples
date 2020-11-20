package com.racoon.chat.server.socket.handler;

import com.alibaba.fastjson.JSON;
import com.racoon.chat.protocol.friend.SearchFriendRequest;
import com.racoon.chat.protocol.friend.SearchFriendResponse;
import com.racoon.chat.protocol.friend.dto.UserDto;
import io.netty.channel.Channel;
import com.racoon.chat.server.application.UserService;
import com.racoon.chat.server.domain.user.model.LuckUserInfo;

import com.racoon.chat.server.socket.MyBizHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友添加的Handler
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequest> {

    public SearchFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, SearchFriendRequest msg) {
        logger.info("搜索好友请求处理：{}", JSON.toJSONString(msg));
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setStatus(userInfo.getStatus());
            userDtoList.add(userDto);
        }
        SearchFriendResponse response = new SearchFriendResponse();
        response.setList(userDtoList);
        channel.writeAndFlush(response);
    }

}
