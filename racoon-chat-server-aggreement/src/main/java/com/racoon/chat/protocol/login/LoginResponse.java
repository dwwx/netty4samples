package com.racoon.chat.protocol.login;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import com.racoon.chat.protocol.login.dto.ChatTalkDto;
import com.racoon.chat.protocol.login.dto.GroupsDto;
import com.racoon.chat.protocol.login.dto.UserFriendDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoginResponse extends Packet {
    private boolean success;                    // 登陆反馈
    private String userId;                      // 用户ID
    private String userHead;                    // 用户头像
    private String userNickName;                // 用户昵称
    private List<ChatTalkDto> chatTalkList = new ArrayList<>();     // 聊天对话框数据[success is true]
    private List<GroupsDto> groupsList = new ArrayList<>();         // 群组列表
    private List<UserFriendDto> userFriendList = new ArrayList<>(); // 好友列表
    public LoginResponse(){}

    public LoginResponse(boolean success){
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
    @Override
    public Byte getCommand() {
        return Command.LoginResponse;
    }
}
