package com.racoon.ui.view.alogin;

import com.racoon.ui.view.achat.IChatEvent;
import com.racoon.ui.view.achat.IChatMethod;

/**
 * 窗体的控制管理类，也是一个窗体的管家，继承了窗体的初始化，实现了窗体具体的操作方法
 */
public class LoginController extends LoginInit implements ILoginMethod{
    private IChatMethod chat;
    private LoginView loginView;
    private LoginEventDefine loginEventDefine;

    public LoginController(ILoginEvent loginEvent, IChatMethod chatMethod) {
        super(loginEvent);
        this.chat = chatMethod;
    }

    @Override
    public void initView() {
        loginView = new LoginView(this, loginEvent);
    }

    @Override
    public void initEventDefine() {
        loginEventDefine = new LoginEventDefine(this, loginEvent, this);
    }

    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void doLoginError() {
        System.out.println("登陆失败，执行提示操作");
    }

    @Override
    public void doLoginSuccess() {
        System.out.println("登陆成功，执行跳转操作");
        // 关闭原窗口
        close();
        chat.doShow();
    }
}
