package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public interface CollectContract {
    interface View extends BaseView {
        void showCollectListArticles(ArticleListBean articleListBean);

        void showMoreCollectListArticles(ArticleListBean articleListBean);

        void showUnCollectFromCollectPage(int pos, ArticleBean articleBean);

    }

    interface Presenter extends BasePresenter<View> {
        void getCollectListArticles();

        void getMoreCollectListArticles();

        void unCollectFromCollectPage(int pos, ArticleBean articleBean);

    }
}
