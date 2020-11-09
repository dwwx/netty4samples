package com.racoon.chat.protocol.login;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

@Data
public class LoginRequest extends Packet {

    private String userId;        // 用户ID
    private String userPassword;  // 用户密码

    public LoginRequest(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    @Override
    public Byte getCommand() {
        return Command.LoginRequest;
    }
}
