package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public interface ProjectListContract {
    interface View extends BaseView {

        void showRefreshProjectArticleResult(ArticleListBean articleListBean);

        void showMoreProjectArticleResult(ArticleListBean articleListBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getRefreshProjectArticleList(int page, int cid);

        void getMoreProjectArticleList(int page, int cid);
    }
}
