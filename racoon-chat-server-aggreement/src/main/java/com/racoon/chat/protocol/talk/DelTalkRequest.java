package com.racoon.chat.protocol.talk;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

@Data
public class DelTalkRequest extends Packet {
    private String userId;
    private String talkId; //对话框id
    public DelTalkRequest() {
    }

    public DelTalkRequest(String userId, String talkId) {
        this.userId = userId;
        this.talkId = talkId;
    }
    @Override
    public Byte getCommand() {
        return Command.DelTalkRequest;
    }
}
