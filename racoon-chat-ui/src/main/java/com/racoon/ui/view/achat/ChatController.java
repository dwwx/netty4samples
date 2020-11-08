package com.racoon.ui.view.achat;

import com.racoon.ui.util.CacheUtil;
import com.racoon.ui.util.Ids;
import com.racoon.ui.view.achat.element.ElementTalk;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

public class ChatController extends ChatInit implements IChatMethod{
    private ChatEventDefine chatEventDefine;
    @Override
    public void initView() {

    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this);
    }

    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {
        //填充到对话框
        ListView<Pane> talkList = $("talkList", ListView.class);
        //判断该会话框是否有该对象
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);
        if(null != elementTalk){
            Node talkNode = talkList.lookup("#"+ Ids.ElementTalkId.createTalkPaneId(talkId));
            if(talkNode == null){
                talkList.getItems().add(talkIdx, elementTalk.pane());
            }
            if(selected){
                talkList.getSelectionModel().select(elementTalk.pane());
            }
            return;
        }
        // 初始化对话框元素
        ElementTalk talkElement = new ElementTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        CacheUtil.talkMap.put(talkId, talkElement);
        // 填充到对话框
        ObservableList<Pane> items = talkList.getItems();
        Pane talkElementPane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, talkElementPane);  // 添加到第一个位置
        } else {
            items.add(talkElementPane);           // 顺序添加
        }
        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            System.out.println("点击对话框：" + talkName);
        });
        // 鼠标事件[移入/移出]
        talkElementPane.setOnMouseEntered(event -> {
            talkElement.delete().setVisible(true);
        });
        talkElementPane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        // 从对话框中删除
        talkElement.delete().setOnMouseClicked(event -> {
            System.out.println("删除对话框：" + talkName);
            talkList.getItems().remove(talkElementPane);
            talkElement.clearMsgSketch();
        });
    }
}
