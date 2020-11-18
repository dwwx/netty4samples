package com.racoon.ui.view.alogin;

/**
 * 事件的定义，基本事件，最大化，最小化，退出
 */

public class LoginEventDefine {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    private ILoginMethod loginMethod;

    public LoginEventDefine(LoginInit loginInit, ILoginEvent loginEvent, ILoginMethod loginMethod){
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
        this.loginMethod = loginMethod;
        loginInit.move();
        min();
        quit();
        doEventLogin();
    }
    // 事件；最小化
    private void min() {
        loginInit.login_min.setOnAction(event -> {
            System.out.println("最小化");
            loginInit.setIconified(true);
        });
    }

    // 事件；退出
    private void quit() {
        loginInit.login_close.setOnAction(event -> {
            System.out.println("退出");
            loginInit.close();
            System.exit(0);
        });
    }

    public void doEventLogin(){
        //点击登陆按钮的时候，交给具体的业务逻辑的时候去处理
        loginInit.login_button.setOnAction(event -> {
            //loginEvent是接口，这里调用的是其子类的实现方法
            loginEvent.doLoginCheck(loginInit.userId.getText(), loginInit.userPassword.getText());
        });
    }
}
