package com.kanghanbin.wanandroid.contract;

import java.util.List;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.FriendBean;

/**
 * 创建时间：2018/11/9
 * 编写人：kanghb
 * 功能描述：
 */
public interface UseContract {
    interface View extends BaseView {
        void showUseFriend(List<FriendBean> friendBeanList);
    }

    interface Presenter extends BasePresenter<View> {
        void getUseFriend();
    }
}
