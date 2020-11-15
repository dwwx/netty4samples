package com.racoon.ui.view.alogin;

/**
 * 方法接口类，事件触发回调之后，进行页面的跳转和数据的填充
 */
public interface ILoginMethod {
    /**
     * 打开登陆窗口
     */
    void doShow();

    /**
     * 登陆失败
     */
    void doLoginError();

    /**
     * 登陆成功 跳转到聊天窗口，关闭登陆窗口，打开新窗口
     */
    void doLoginSuccess();
}
