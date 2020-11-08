package com.racoon.ui.view.alogin;

/**
 * 登陆窗口实现，外部给予实现
 */
public interface ILoginEvent {
    /**
     * 登陆验证
     * @param userId
     * @param userPassword
     */
    void doLoginCheck(String userId, String userPassword);
}
