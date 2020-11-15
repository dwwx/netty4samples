package com.racoon.ui.view.alogin;

/**
 * 登陆窗口实现，外部给予实现。例如我们在点击登陆后将属于窗体的功能处理完毕后，实际的验证交给外部
 *
 */
public interface ILoginEvent {
    /**
     * 登陆验证
     * @param userId
     * @param userPassword
     *
     * 如果是实际的业务逻辑开发，还需传递IP地址，设备信息，请求时间，用于判断是否正常登陆
     */
    void doLoginCheck(String userId, String userPassword);
}
