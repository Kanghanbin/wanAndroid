package com.kanghanbin.wanandroid.base;

/**
 * 创建时间：2018/10/22
 * 编写人：kanghb
 * 功能描述：Presenter基类
 */
public interface BasePresenter<T extends BaseView> {

    /**
     * @param view 绑定view避免activity意外结束，造成空指针异常
     */
    void attachView(T view);

    void detachView();

    void getLoginAccount();

    void getLoignPassword();

    boolean getLoginStatus();

    void setLoginAccount(String account);

    void setLoginPassword(String password);

    void setLoginStatus(boolean b);

    void clearCookie();

}
