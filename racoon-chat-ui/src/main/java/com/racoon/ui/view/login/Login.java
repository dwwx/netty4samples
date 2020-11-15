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
 * 这样写代码的话，感觉逻辑就很简单，但是没有用到编程的思想
 */

public class Login extends Stage {
    //加载fxml文件，然后准备加载
    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    private Parent root;

    public Login(){
        try{
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        }catch (IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        //设置了透明的颜色
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        //样式也是透明的
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        //设置任务状态栏的图标
        this.getIcons().add(new Image("/fxml/login/img/system/logo.png"));

        //初始化相关的事件
        initEvent();
    }
    //后台根据id获取前端的值 root需要好好看看源码，看其是怎样实现领域驱动的
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
        //root是我们登陆窗体的面板元素
        root.setOnMousePressed(event -> {
            //按下鼠标时记录位置
            xOffset = getX() - event.getSceneX();
            yOffset = getY() - event.getSceneY();
            //设置鼠标样式
            root.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            //拖动时设置位置
            setX(event.getSceneX() + xOffset);
            setY(event.getSceneY() + yOffset);
        });
        root.setOnMouseReleased(event -> {
            //释放鼠标时，恢复鼠标的默认样式
            root.setCursor(Cursor.DEFAULT);
        });
    }
    private void min(){
        Button login_min = $("login_min", Button.class);
        login_min.setOnAction(event -> {
            System.out.println("最小化窗体");
            //最小化
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
        //登陆验证时，不会使用明文传输，需要使用非对称加密，这是自己可以拓展的一部分
        TextField userId = $("userId", TextField.class);
        PasswordField userPassword = $("userPassword", PasswordField.class);
        Button login_button = $("login_button",Button.class);
        //设置鼠标的点击事件
        login_button.setOnAction(event -> {
            System.out.println("登录操作");
            System.out.println("用户id:"+userId.getText());
            System.out.println("用户密码："+userPassword.getText());
        });
    }
}
