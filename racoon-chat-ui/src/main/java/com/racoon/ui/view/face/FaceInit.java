package com.racoon.ui.view.face;

import com.racoon.ui.view.UIObject;
import com.racoon.ui.view.achat.ChatInit;
import com.racoon.ui.view.achat.IChatEvent;
import com.racoon.ui.view.achat.IChatMethod;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * 模态窗口的创建示例
 */
public abstract class FaceInit extends UIObject {
    private static final String RESOURCE_NAME = "/fxml/face/face.fxml";
    public Pane rootPane;

    public ChatInit chatInit;
    public IChatEvent chatEvent;
    public IChatMethod chatMethod;
    FaceInit(final UIObject obj){
        try{
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        }catch (Exception e){
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);

        //模态窗口
        initModality(Modality.APPLICATION_MODAL);
        initOwner(obj);

        //初始化页面事件
        obtain();
        initView();
        initEventDefine();
    }

    private void obtain() {
        rootPane = $("face", Pane.class);
    }

    public Parent root(){
        return super.root;
    }
}
