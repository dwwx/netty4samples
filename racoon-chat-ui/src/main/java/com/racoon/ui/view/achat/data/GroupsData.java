package com.racoon.ui.view.achat.data;

import lombok.Data;

/**
 * 群组数据
 */
@Data
public class GroupsData {
    private String groupId;    // 群组ID
    private String groupName;  // 群组名称
    private String groupHead;  // 群组头像

    public GroupsData() {
    }

    public GroupsData(String groupId, String groupName, String groupHead) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupHead = groupHead;
    }

}
