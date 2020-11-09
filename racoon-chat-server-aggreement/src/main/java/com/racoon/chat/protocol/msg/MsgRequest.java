package com.racoon.chat.protocol.msg;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

import java.util.Date;

@Data
public class MsgRequest extends Packet {
    private String userId;   // 用户ID[个人]
    private String friendId; // 好友ID[对方]
    private String msgText;  // 传输消息内容
    private Integer msgType; // 消息类型；0文字消息、1固定表情
    private Date msgDate;    // 消息时间

    public MsgRequest() {
    }

    public MsgRequest(String userId, String friendId, String msgText, Integer msgType, Date msgDate) {
        this.userId = userId;
        this.friendId = friendId;
        this.msgText = msgText;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }
    @Override
    public Byte getCommand() {
        return Command.MsgRequest;
    }
}
