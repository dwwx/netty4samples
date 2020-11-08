package com.racoon.ui.view.achat.data;

import lombok.Data;

@Data
public class TalkBoxData {
    private String talkId;    // 对话Id
    private Integer talkType; // 对话类型
    private String talkName;  // 对话名称
    private String talkHead;  // 对话头像

    public TalkBoxData() {
    }

    public TalkBoxData(String talkId, Integer talkType, String talkName, String talkHead) {
        this.talkId = talkId;
        this.talkType = talkType;
        this.talkName = talkName;
        this.talkHead = talkHead;
    }
}
