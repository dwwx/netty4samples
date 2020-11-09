package com.racoon.chat.server.application;

import com.racoon.chat.server.domain.inet.model.ChannelUserInfo;
import com.racoon.chat.server.domain.inet.model.ChannelUserReq;
import com.racoon.chat.server.domain.inet.model.InetServerInfo;

import java.util.List;

public interface InetService {
    InetServerInfo queryNettyServerInfo();

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);
}
