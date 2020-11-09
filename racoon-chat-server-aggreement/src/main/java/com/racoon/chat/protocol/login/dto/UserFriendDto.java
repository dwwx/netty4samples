package com.racoon.chat.protocol.login.dto;

import lombok.Data;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
@Data
public class UserFriendDto {

    private String friendId;    // 好友ID
    private String friendName;  // 好友名称
    private String friendHead;  // 好友头像

}
