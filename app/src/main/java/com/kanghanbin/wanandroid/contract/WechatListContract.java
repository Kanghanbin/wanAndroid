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
public interface WechatListContract {
    interface View extends BaseView {
        void showRefreshWechatArticleList(ArticleListBean articleListBean);

        void showMoreWechatArticleList(ArticleListBean articleListBean);


        void showAddCollectArticle(int position, ArticleBean articleBean);

        void showCancelCollectArticle(int position, ArticleBean articleBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getRefreshWechatArticleList(int id, int page);

        void getMoreWechatArticleList(int id, int page);

        void addCollectArticle(int position, ArticleBean articleBean);

        void cancelCollectArticle(int position, ArticleBean articleBean);
    }
}
