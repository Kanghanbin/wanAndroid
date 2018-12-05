package com.kanghanbin.wanandroid.db;

import java.util.HashSet;

/**
 * 创建时间：2018/11/8
 * 编写人：kanghb
 * 功能描述：
 */
public interface ISharePreference {

    void setLoginAccount(String account);

    void setLoginPassword(String password);

    void setLoginStatus(boolean b);

    void setCookie(HashSet<String> cookie);

    void setCurrentItem(int currentItem);

    void setNightMode(boolean b);

    void setNoImage(boolean b);

    void setAutoCache(boolean b);

    String getLoginAccount();

    String getLoginPassword();

    boolean getLoginStatus();

    HashSet<String> getCookie();

    int getCurrentItem();

    void removeCookie();

    boolean getNightMode();

    boolean getNoImageMode();

    boolean getAutoCache();

}
