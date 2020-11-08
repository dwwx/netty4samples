package com.racoon.ui.view.achat.data;

import lombok.Data;

/**
 * 对话框用户数据
 */
@Data
public class TalkData {
    private String talkName;
    private String talkHead;

    public TalkData(){

    }
    public TalkData(String talkName, String talkHead){
        this.talkHead = talkHead;
        this.talkName = talkName;
    }
}
