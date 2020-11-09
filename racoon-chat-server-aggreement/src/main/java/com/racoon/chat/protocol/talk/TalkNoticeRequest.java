package com.racoon.chat.protocol.talk;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

@Data
public class TalkNoticeRequest extends Packet {

    private String userId;       // 用户ID
    private String friendUserId; // 好友ID
    private Integer talkType;    // 对话框类型[0好友、1群组]

    public TalkNoticeRequest(){}

    public TalkNoticeRequest(String userId, String friendUserId, Integer talkType) {
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.talkType = talkType;
    }



    @Override
    public Byte getCommand() {
        return Command.TalkNoticeRequest;
    }
}
