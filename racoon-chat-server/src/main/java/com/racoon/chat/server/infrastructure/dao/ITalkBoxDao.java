package com.racoon.chat.server.infrastructure.dao;

import com.racoon.chat.server.infrastructure.po.TalkBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@Mapper
public interface ITalkBoxDao {

    List<TalkBox> queryTalkBoxList(String userId);

    void addTalkBox(TalkBox talkBox);

    void deleteUserTalk(String userId, String talkId);

    TalkBox queryTalkBox(@Param("userId") String userId, @Param("talkId")String talkId);

    List<String> queryTalkBoxGroupsIdList(String userId);

}
