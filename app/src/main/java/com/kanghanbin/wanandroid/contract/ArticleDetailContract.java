package com.kanghanbin.wanandroid.contract;

import com.tbruyelle.rxpermissions2.RxPermissions;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;

/**
 * 创建时间：2018/11/7
 * 编写人：kanghb
 * 功能描述：
 */
public interface ArticleDetailContract {
    interface View extends BaseView {
        void showAddCollect();

        void showCancelCollect();

        void shareEvent();

        void shareError();
    }

    interface Presenter extends BasePresenter<View> {

        void addCollect(int id);

        void cancelCollect(int id);

        void cancelCollectFromCollect(int id);

        void shareEventPermissionVerify(RxPermissions rxPermissions);
    }
}
