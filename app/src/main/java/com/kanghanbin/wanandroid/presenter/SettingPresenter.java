package com.kanghanbin.wanandroid.presenter;

import android.text.TextUtils;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.SettingContract;
import com.kanghanbin.wanandroid.util.Constant;

/**
 * 创建时间：2018/11/20
 * 编写人：kanghb
 * 功能描述：
 */
public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {
    @Override
    public boolean getNoImageModeState() {
        return sharePreferencesHelper.getNoImageMode();
    }

    @Override
    public boolean getAutoCacheState() {
        return sharePreferencesHelper.getAutoCache();
    }

    @Override
    public boolean getNigthModeState() {
        return sharePreferencesHelper.getNightMode();
    }

    @Override
    public void setNoImageModeState(boolean b) {
        sharePreferencesHelper.setNoImage(b);
    }

    @Override
    public void setAutoCacheModeState(boolean b) {
        sharePreferencesHelper.setAutoCache(b);
    }

    @Override
    public void setNightModeState(boolean b) {
        sharePreferencesHelper.setNightMode(b);
    }

    @Override
    public void clearCache(File file) {
        FileUtils.delete(new File(Constant.PATH_CACHE));
    }

    @Override
    public String getCacheSize4Format(File file) {
        return TextUtils.isEmpty(FileUtils.getSize(file)) ? "0K" : FileUtils.getSize(file);
    }

}
