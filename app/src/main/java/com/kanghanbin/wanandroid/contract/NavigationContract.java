package com.kanghanbin.wanandroid.contract;

import java.util.List;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.NavigationBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public interface NavigationContract {
    interface View extends BaseView {
        void showNavigationList(List<NavigationBean> navigationBeanList);
    }

    interface Presenter extends BasePresenter<View> {
        void getNavigation();
    }
}
