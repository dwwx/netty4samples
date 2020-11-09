package com.racoon.chat.protocol.login.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 *
 * 网络数据传输对象；聊天对话框
 */
@Data
public class ChatTalkDto {

    private String talkId;      // 对话框ID
    private Integer talkType;   // 对话框类型；0好友、1群组
    private String talkName;    // 用户昵称
    private String talkHead;    // 用户头像
    private String talkSketch;  // 消息简述
    private Date talkDate;      // 消息时间

    private List<ChatRecordDto> chatRecordList;  // 聊天记录

}
