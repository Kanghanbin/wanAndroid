package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;

/**
 * 创建时间：2018/10/22
 * 编写人：kanghb
 * 功能描述：
 */
public interface LoginContract {
    interface View extends BaseView{

        void gotoMain();

        void gotoRegist();

        void gotoLogin();
    }

    interface Presenter extends BasePresenter<View>{

        void login(String username, String password);

        void regist(String username, String password, String repassword);
    }
}
