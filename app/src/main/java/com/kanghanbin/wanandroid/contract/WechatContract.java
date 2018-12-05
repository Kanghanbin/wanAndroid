package com.kanghanbin.wanandroid.contract;

import java.util.List;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.WxarticleBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public interface WechatContract {
    interface View extends BaseView {
        void showWechatTree(List<WxarticleBean> wxarticleBeanList);
    }

    interface Presenter extends BasePresenter<View> {

        void geWechatTree();
    }
}
