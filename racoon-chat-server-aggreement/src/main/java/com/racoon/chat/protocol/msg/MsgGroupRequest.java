package com.racoon.chat.protocol.msg;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

import java.util.Date;

@Data
public class MsgGroupRequest extends Packet {
    private String talkId;   // 对话框ID
    private String userId;   // 群员ID
    private String msgText;  // 传输消息内容
    private Integer msgType;  // 消息类型；0文字消息、1固定表情
    private Date msgDate;    // 消息时间

    public MsgGroupRequest() {
    }

    public MsgGroupRequest(String talkId, String userId, String msgText,Integer msgType, Date msgDate) {
        this.talkId = talkId;
        this.userId = userId;
        this.msgText = msgText;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }
    @Override
    public Byte getCommand() {
        return Command.MsgGroupRequest;
    }
}
