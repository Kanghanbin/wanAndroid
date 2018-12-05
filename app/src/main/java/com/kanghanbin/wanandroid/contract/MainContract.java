package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;

/**
 * 创建时间：2018/10/30
 * 编写人：kanghb
 * 功能描述：
 */
public interface MainContract {
    interface View extends BaseView {
        void showLogout();
        void restoreBeforeNight();
    }

    interface Presenter extends BasePresenter<View> {
        void setCurrentItem(int pos);

        int getCurrentItem();

        void logout();


    }
}
