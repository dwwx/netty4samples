package com.racoon.ui.view.achat.element.group_bar_friend;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
public class ElementFriendGroupList {
    private Pane pane;
    private ListView<Pane> groupListView; // 群组列表

    public ElementFriendGroupList() {
        pane = new Pane();
        pane.setId("friendGroupList");
        pane.setPrefWidth(314);
        pane.setPrefHeight(0);// 自动计算；groupListView.setPrefHeight(70 * items.size() + 10);
        pane.setLayoutX(-10);
        pane.getStyleClass().add("elementFriendGroupList");
        ObservableList<Node> children = pane.getChildren();

        groupListView = new ListView<>();
        groupListView.setId("groupListView");
        groupListView.setPrefWidth(314);
        groupListView.setPrefHeight(0); // 自动计算；groupListView.setPrefHeight(70 * items.size() + 10);
        groupListView.setLayoutX(-10);
        groupListView.getStyleClass().add("elementFriendGroupList_listView");
        children.add(groupListView);
    }

    public Pane pane() {
        return pane;
    }
}
