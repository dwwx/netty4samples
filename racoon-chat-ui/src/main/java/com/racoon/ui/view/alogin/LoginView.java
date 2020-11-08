package com.racoon.ui.view.alogin;

public class LoginView {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    public LoginView(LoginInit loginInit, ILoginEvent loginEvent){
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
    }
}
