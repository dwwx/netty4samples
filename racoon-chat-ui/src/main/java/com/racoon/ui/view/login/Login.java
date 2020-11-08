package com.racoon.ui.view.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 请体会是怎么用逻辑来控制前后端的
 */

public class Login extends Stage {
    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    private Parent root;

    public Login(){
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

        //初始化相关的事件
        initEvent();
    }
    //后台根据id获取前端的值
    public <T> T $(String id, Class<?> clazz){
        return (T)root.lookup('#'+id);
    }
    private void initEvent(){
        this.move();
        this.min();
        this.quit();
        this.login();
    }
    private double xOffset;
    private double yOffset;
    private void move(){
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getSceneX();
            yOffset = getY() - event.getSceneY();
            root.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            setX(event.getSceneX() + xOffset);
            setY(event.getSceneY() + yOffset);
        });
        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }
    private void min(){
        Button login_min = $("login_min", Button.class);
        login_min.setOnAction(event -> {
            System.out.println("最小化窗体");
            setIconified(true);
        });
    }

    //事件，退出
    private void quit(){
        Button login_close = $("login_close",Button.class);
        login_close.setOnAction(event -> {
            System.out.println("退出窗体");
            close();
            System.exit(0);
        });
    }

    //事件 登录
    private void login(){
        TextField userId = $("userId", TextField.class);
        PasswordField userPassword = $("userPassword", PasswordField.class);
        Button login_button = $("login_button",Button.class);
        login_button.setOnAction(event -> {
            System.out.println("登录操作");
            System.out.println("用户id:"+userId.getText());
            System.out.println("用户密码："+userPassword.getText());
        });
    }
}
