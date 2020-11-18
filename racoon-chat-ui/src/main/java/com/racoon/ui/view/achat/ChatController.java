package com.racoon.ui.view.achat;

import com.racoon.ui.util.CacheUtil;
import com.racoon.ui.util.Ids;
import com.racoon.ui.view.achat.data.GroupsData;
import com.racoon.ui.view.achat.data.RemindCount;
import com.racoon.ui.view.achat.data.TalkData;
import com.racoon.ui.view.achat.element.group_bar_chat.ElementInfoBox;
import com.racoon.ui.view.achat.element.group_bar_chat.ElementTalk;
import com.racoon.ui.view.achat.element.group_bar_friend.ElementFriendGroup;
import com.racoon.ui.view.achat.element.group_bar_friend.ElementFriendLuckUser;
import com.racoon.ui.view.achat.element.group_bar_friend.ElementFriendUser;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

public class ChatController extends ChatInit implements IChatMethod{
    private ChatEventDefine chatEventDefine;
    private ChatView chatView;
    public ChatController(IChatEvent chatEvent){
        super(chatEvent);
    }
    @Override
    public void initView() {
        chatView = new ChatView(this, chatEvent);
    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this, chatEvent, this);
    }

    @Override
    public double getToolFaceX() {
        return getX() + getWidth() -960;
    }

    @Override
    public double getToolFaceY() {
        return getY() +getHeight()-180;
    }

    @Override
    public void doShow() {
        super.show();
    }

    //设置客户端用户的基本信息
    @Override
    public void setUserInfo(String userId, String userNickName, String userHead) {
        super.userId = userId;
        super.userNickName = userNickName;
        super.userHead = userHead;
        Button button = $("bar_portrait", Button.class);
        button.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
    }

    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {
        //填充到对话框
        //得到会话的ListView
        //会话栏
        ListView<Pane> talkList = $("talkList", ListView.class);
        //判断该会话框是否有该对象 对每一个会话进行了一个缓存，talkId为key, ElementTalk为value
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);

        //如果已经存在
        if(null != elementTalk){
            //根据talkId去找到对应的Node
            Node talkNode = talkList.lookup("#"+ Ids.ElementTalkId.createTalkPaneId(talkId));

            //操作ListView的库函数
            if(talkNode == null){
                talkList.getItems().add(talkIdx, elementTalk.pane());
            }
            if(selected){
                //选中某一个pane
                talkList.getSelectionModel().select(elementTalk.pane());
            }
            // 填充对话框消息栏
            fillInfoBox(elementTalk, talkName);
            return;
        }
        // 如果不存在
        // 初始化对话框元素
        ElementTalk talkElement = new ElementTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        //加入缓存
        CacheUtil.talkMap.put(talkId, talkElement);
        // 填充到对话框
        ObservableList<Pane> items = talkList.getItems();
        //talkElement.pane()得到相应的pane
        Pane talkElementPane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, talkElementPane);  // 添加到第一个位置
        } else {
            items.add(talkElementPane);           // 顺序添加
        }
        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        //对话框的事件也在这里进行处理
        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            System.out.println("点击对话框：" + talkName);
            fillInfoBox(talkElement, talkName);
            // 清除消息提醒
            Label msgRemind = talkElement.msgRemind();
            msgRemind.setUserData(new RemindCount(0));
            msgRemind.setVisible(false);
        });
        // 鼠标事件[移入/移出]
        talkElementPane.setOnMouseEntered(event -> {
            talkElement.delete().setVisible(true);
        });
        talkElementPane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        //填充对话框消息栏
        fillInfoBox(talkElement, talkName);
        // 从对话框中删除 点击删除按钮的时候
        talkElement.delete().setOnMouseClicked(event -> {

            System.out.println("删除对话框：" + talkName);

            talkList.getItems().remove(talkElementPane);

            Pane pane = $("info_pane_box", Pane.class);
            pane.getChildren().clear();
            pane.setUserData(null);
            Label label = $("info_name", Label.class);
            label.setText("");
            //ListView<Pane>
            talkElement.infoBoxList().getItems().clear();
            //清空消息
            talkElement.clearMsgSketch();
        });
    }

    @Override
    public void addTalkMsgUserLeft(String talkId, String msg, Integer msgType, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        //得到初始化没有数据的ListView<Pane>,其中有talkName talkHead
        ListView<Pane> listView = talkElement.infoBoxList();

        TalkData talkUserData = (TalkData) listView.getUserData();

        Pane left = new ElementInfoBox().left(talkUserData.getTalkName(), talkUserData.getTalkHead(), msg);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);

        talkElement.fillMsgSketch(msg, msgData);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
        // 填充对话框聊天窗口
        fillInfoBox(talkElement, talkUserData.getTalkName());
    }

    @Override
    public void addTalkMsgGroupLeft(String talkId, String userId, String userNickName, String userHead, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        // 自己的消息抛弃
        if (super.userId.equals(userId)) return;

        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);

        if (null == talkElement) {
            Pane pane = $(Ids.ElementTalkId.createFriendGroupId(talkId), Pane.class);

            GroupsData groupsData = (GroupsData) pane.getUserData();

            if (null == groupsData) return;
            addTalkBox(0, 1, talkId, groupsData.getGroupName(), groupsData.getGroupHead(), userNickName + "：" + msg, msgDate, false);
            talkElement = CacheUtil.talkMap.get(talkId);
            // 事件通知(开启与群组发送消息)
            System.out.println("事件通知(开启与群组发送消息)");
        }
        ListView<Pane> listView = talkElement.infoBoxList();

        TalkData talkData = (TalkData) listView.getUserData();

        Pane left = new ElementInfoBox().left(userNickName, userHead, msg);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch(userNickName + "：" + msg, msgDate);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(1, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
        // 填充对话框聊天窗口
        fillInfoBox(talkElement, talkData.getTalkName());
    }

    @Override
    public void addTalkMsgRight(String talkId, String msg, Integer msgType, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        Pane right = new ElementInfoBox().right(userNickName, userHead, msg);
        // 消息填充
        listView.getItems().add(right);
        // 滚动条
        listView.scrollTo(right);
        talkElement.fillMsgSketch(msg, msgData);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
    }

    /**
     * 私有方法
     * 填充对话框消息内容
     *
     * @param talkElement 对话框元素
     * @param talkName    对话框名称
     */
    private void fillInfoBox(ElementTalk talkElement, String talkName) {
        String talkId = talkElement.pane().getUserData().toString();
        // 填充对话列表
        Pane info_pane_box = $("info_pane_box", Pane.class);
        String boxUserId = (String) info_pane_box.getUserData();
        // 判断是否已经填充[talkId]，当前已填充则返回
        if (talkId.equals(boxUserId)) return;
        ListView<Pane> listView = talkElement.infoBoxList();
        info_pane_box.setUserData(talkId);
        info_pane_box.getChildren().clear();
        info_pane_box.getChildren().add(listView);
        // 对话框名称
        Label info_name = $("info_name", Label.class);
        info_name.setText(talkName);
    }


    @Override
    public void addFriendGroup(String groupId, String groupName, String groupHead) {
        ElementFriendGroup elementFriendGroup = new ElementFriendGroup(groupId, groupName, groupHead);
        Pane pane = elementFriendGroup.pane();
        // 添加到群组列表
        ListView<Pane> groupListView = $("groupListView", ListView.class);
        ObservableList<Pane> items = groupListView.getItems();
        items.add(pane);
        groupListView.setPrefHeight(80 * items.size());
        Pane pane1 = $("friendGroupList", Pane.class);
        pane1.setPrefHeight(80 * items.size());

        // 群组，内容框[初始化，未装载]，承载群组信息内容，点击按钮时候填充
        Pane detailContent = new Pane();
        detailContent.setPrefSize(850, 560);
        detailContent.getStyleClass().add("friendGroupDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(groupId);
        sendMsgButton.getStyleClass().add("friendGroupSendMsgButton");
        sendMsgButton.setPrefSize(176, 50);
        sendMsgButton.setLayoutX(337);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setText("发送消息");
        chatEventDefine.doEventOpenFriendGroupSendMsg(sendMsgButton, groupId, groupName, groupHead);
        children.add(sendMsgButton);

        // 添加监听事件
        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll($("friendList", ListView.class), $("userListView", ListView.class));
            chatView.setContentPaneBox(groupId, groupName, detailContent);
        });
        chatView.setContentPaneBox(groupId, groupName, detailContent);
    }

    @Override
    public void addFriendUser(boolean selected, String userFriendId, String userFriendNickName, String userFriendHead) {
        ElementFriendUser friendUser = new ElementFriendUser(userFriendId, userFriendNickName, userFriendHead);
        Pane pane = friendUser.pane();
        // 添加到好友列表
        ListView<Pane> userListView = $("userListView", ListView.class);
        ObservableList<Pane> items = userListView.getItems();
        items.add(pane);
        userListView.setPrefHeight(80 * items.size());
        Pane pane1 = $("friendUserList", Pane.class);
        pane1.setPrefHeight(80 * items.size());
        // 选中
        if (selected) {
            userListView.getSelectionModel().select(pane);
        }

        // 好友，内容框[初始化，未装载]，承载好友信息内容，点击按钮时候填充
        Pane detailContent = new Pane();
        detailContent.setPrefSize(850, 560);
        detailContent.getStyleClass().add("friendUserDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(userFriendId);
        sendMsgButton.getStyleClass().add("friendUserSendMsgButton");
        sendMsgButton.setPrefSize(176, 50);
        sendMsgButton.setLayoutX(337);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setText("发送消息");
        chatEventDefine.doEventOpenFriendUserSendMsg(sendMsgButton, userFriendId, userFriendNickName, userFriendHead);
        children.add(sendMsgButton);
        // 添加监听事件
        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll($("friendList", ListView.class), $("groupListView", ListView.class));
            chatView.setContentPaneBox(userFriendId, userFriendNickName, detailContent);
        });
        chatView.setContentPaneBox(userFriendId, userFriendNickName, detailContent);
    }

    @Override
    public void addLuckFriend(String userId, String userNickName, String userHead, Integer status) {
        ElementFriendLuckUser friendLuckUser = new ElementFriendLuckUser(userId, userNickName, userHead, status);
        Pane pane = friendLuckUser.pane();
        // 添加到好友列表
        ListView<Pane> friendLuckListView = $("friendLuckListView", ListView.class);
        ObservableList<Pane> items = friendLuckListView.getItems();
        items.add(pane);
        // 点击事件
        friendLuckUser.statusLabel().setOnMousePressed(event -> {
            chatEvent.doEventAddLuckUser(super.userId, userId);
        });
    }
}
