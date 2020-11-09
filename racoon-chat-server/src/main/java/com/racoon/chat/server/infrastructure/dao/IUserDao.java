package com.racoon.chat.server.infrastructure.dao;

import com.racoon.chat.server.domain.inet.model.ChannelUserReq;
import com.racoon.chat.server.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@Mapper
public interface IUserDao {

    String queryUserPassword(String userId);

    User queryUserById(String userId);

    List<User> queryFuzzyUserList(String userId, String searchKey);

    Long queryChannelUserCount(ChannelUserReq req);

    List<User> queryChannelUserList(ChannelUserReq req);

}
