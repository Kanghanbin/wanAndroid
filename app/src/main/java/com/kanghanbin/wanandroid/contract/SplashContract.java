package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;

/**
 * 创建时间：2018/11/20
 * 编写人：kanghb
 * 功能描述：
 */

public interface SplashContract {

    interface View extends BaseView {

        void jumpToMain();
    }

    interface Presenter extends BasePresenter<View> {

    }

}
