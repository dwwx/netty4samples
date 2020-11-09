package com.racoon.chat.server.infrastructure.dao;

import com.racoon.chat.server.infrastructure.po.Groups;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(String groupsId);

}
