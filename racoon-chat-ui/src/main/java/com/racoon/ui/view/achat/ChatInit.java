package com.racoon.ui.view.achat;

import com.racoon.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class ChatInit extends UIObject {

    private static final String RESOURCE_NAME = "/fxml/chat/chat.fxml";

    //定义一些必要的信息
    public String userId; //用户ID
    public String userNickName;//用户昵称
    public String userHead;

    public IChatEvent chatEvent;
    public TextArea txt_input; //输入框

    ChatInit(IChatEvent chatEvent){
        try{
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        }catch (IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/chat/img/head/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }
    private void obtain(){
        //可以预加载
        txt_input = $("txt_input", TextArea.class);
    }
}
