package com.racoon.ui.view.alogin;

import com.racoon.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 初始化login页面的相关东西
 * 加载这基本的对话框
 * 组合这个对话框所 外部的相关实现方法
 * 初始化加载root和相关的页面上需要使用的组件对象Buttor,Text。方便后续直接调用
 *
 * 继承了UIObject，可以调用父类的一些基本方法，和具体实现父类的抽象方法
 */
public abstract class LoginInit extends UIObject {

    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    protected ILoginEvent loginEvent;

    public Button login_min;
    public Button login_close;
    public Button login_button;
    public TextField userId;
    public PasswordField userPassword;

    LoginInit(ILoginEvent loginEvent){
        this.loginEvent = loginEvent;
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
        this.getIcons().add(new Image("/fxml/login/img/system/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }

    private void obtain(){
        login_min = $("login_min", Button.class);
        login_close = $("login_close", Button.class);
        login_button = $("login_button", Button.class);
        userId = $("userId", TextField.class);
        userPassword = $("userPassword", PasswordField.class);
    }

}
