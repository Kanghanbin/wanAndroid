package com.kanghanbin.wanandroid.db;

import com.blankj.utilcode.util.SPUtils;

import java.util.HashSet;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.util.Constant;

/**
 * 创建时间：2018/11/8
 * 编写人：kanghb
 * 功能描述：
 */
public class SharePreferencesHelper implements ISharePreference {

    private static SharePreferencesHelper mSharePreferencesHelper;
    private SPUtils spUtils;

    private SharePreferencesHelper() {
        spUtils = SPUtils.getInstance(R.string.app_name);
    }

    public static SharePreferencesHelper getInstance() {
        if (mSharePreferencesHelper == null) {
            synchronized (SharePreferencesHelper.class) {
                if (mSharePreferencesHelper == null) {
                    mSharePreferencesHelper = new SharePreferencesHelper();
                }
            }
        }
        return mSharePreferencesHelper;
    }

    @Override
    public void setLoginAccount(String account) {
        spUtils.put(Constant.ACCOUNT, account);
    }

    @Override
    public void setLoginPassword(String password) {
        spUtils.put(Constant.PASSWORD, password);
    }

    @Override
    public void setLoginStatus(boolean b) {
        spUtils.put(Constant.LOGIN_STATUS, b);
    }

    @Override
    public void setCookie(HashSet<String> cookie) {
        spUtils.put(Constant.COOKIES, cookie);
    }

    @Override
    public void setCurrentItem(int currentItem) {
        spUtils.put(Constant.CURRENT_ITEM, currentItem);
    }

    @Override
    public void setNightMode(boolean b) {
        spUtils.put(Constant.NIGHT_MODE, b);
    }

    @Override
    public void setNoImage(boolean b) {
        spUtils.put(Constant.NOIMAGE_MODE, b);
    }

    @Override
    public void setAutoCache(boolean b) {
        spUtils.put(Constant.AUTO_CACHE, b);
    }

    @Override
    public String getLoginAccount() {
        return spUtils.getString(Constant.ACCOUNT);
    }

    @Override
    public String getLoginPassword() {
        return spUtils.getString(Constant.PASSWORD);
    }

    @Override
    public boolean getLoginStatus() {
        return spUtils.getBoolean(Constant.LOGIN_STATUS);
    }

    @Override
    public HashSet<String> getCookie() {
        return (HashSet<String>) spUtils.getStringSet(Constant.COOKIES, new HashSet<String>());
    }

    @Override
    public int getCurrentItem() {
        return spUtils.getInt(Constant.CURRENT_ITEM);
    }

    @Override
    public void removeCookie() {
        spUtils.remove(Constant.COOKIES);
    }

    @Override
    public boolean getNightMode() {
        return spUtils.getBoolean(Constant.NIGHT_MODE);
    }

    @Override
    public boolean getNoImageMode() {
        return spUtils.getBoolean(Constant.NOIMAGE_MODE);
    }

    @Override
    public boolean getAutoCache() {
        return spUtils.getBoolean(Constant.AUTO_CACHE);
    }


}
