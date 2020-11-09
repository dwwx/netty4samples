package com.racoon.chat.protocol.talk;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

import java.util.Date;

@Data
public class TalkNoticeResponse extends Packet {

    private String talkId;     // 对话框ID[好友ID、群ID]
    private String talkName;   // 对话框名称[好友名称、群名称]
    private String talkHead;   // 对话框头像[好友头像、群头像]
    private String talkSketch; // 消息简讯
    private Date talkDate;     // 消息时间
    @Override
    public Byte getCommand() {
        return Command.TalkNoticeResponse;
    }
}
