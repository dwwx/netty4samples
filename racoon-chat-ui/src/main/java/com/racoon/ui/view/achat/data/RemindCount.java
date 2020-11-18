package com.racoon.ui.view.achat.data;


/**
 * 消息提醒的机制，因为每一个对话框都有一个消息提醒的机制
 * 所以单独的将其抽象出来
 */

public class RemindCount {
    private int count = 0;  // 消息提醒条数

    public RemindCount() {
    }

    public RemindCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
