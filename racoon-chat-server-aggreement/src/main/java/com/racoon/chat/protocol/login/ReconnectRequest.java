package com.racoon.chat.protocol.login;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

@Data
public class ReconnectRequest extends Packet {
    private String userId;
    public ReconnectRequest(String userId) {
        this.userId = userId;
    }
    @Override
    public Byte getCommand() {
        return Command.ReconnectRequest;
    }
}
