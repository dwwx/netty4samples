package com.racoon.chat.protocol.friend.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;       // 用户ID
    private String userNickName; // 用户昵称
    private String userHead;     // 用户头像
    private Integer status;      // 状态；0添加、1[保留]、2已添加

    public UserDto() {
    }

    public UserDto(String userId, String userNickName, String userHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
    }
}
