package com.racoon.ui.view.achat.data;

import lombok.Data;

@Data
public class RemindCount {
    private int count = 0;  // 消息提醒条数

    public RemindCount() {
    }

    public RemindCount(int count) {
        this.count = count;
    }
}
