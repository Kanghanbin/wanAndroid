package com.kanghanbin.wanandroid.contract;

import java.util.List;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;
import com.kanghanbin.wanandroid.model.bean.BannerBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public interface HomeContract {
    interface View extends BaseView {
        void showArticleList(ArticleListBean articleListBean);

        void showArticleListMore(ArticleListBean articleListBean);

        void showBanner(List<BannerBean> bannerBeanList);

        void showAddCollectArticle(int position, ArticleBean articleBean);

        void showCancelCollectArticle(int position, ArticleBean articleBean);

    }

    interface Presenter extends BasePresenter<View> {

        void getBanner();

        void getArticleList();

        void getArticleMore();

        void autoRefresh();

        void loadMore();

        void addCollectArticle(int position, ArticleBean articleBean);

        void cancelCollectArticle(int position, ArticleBean articleBean);

    }
}
