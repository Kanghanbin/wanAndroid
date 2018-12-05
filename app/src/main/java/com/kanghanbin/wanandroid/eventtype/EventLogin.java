package com.kanghanbin.wanandroid.eventtype;

/**
 * 创建时间：2018/11/8
 * 编写人：kanghb
 * 功能描述：
 */
public class EventLogin {
    public EventLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private boolean isLogin;
}
