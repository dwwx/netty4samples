package com.racoon.ui.view.alogin;

/**
 * 窗体的展示，主要用于扩展一些随着用户操作新展示的元素，例如后续在聊天窗体新增的消息提醒等
 * 组合LoginInit，ILoginEvent
 */
public class LoginView {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    public LoginView(LoginInit loginInit, ILoginEvent loginEvent){
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
    }
}
