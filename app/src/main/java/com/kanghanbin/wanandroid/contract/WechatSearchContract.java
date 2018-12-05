package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/11/19
 * 编写人：kanghb
 * 功能描述：
 */
public interface WechatSearchContract {
    interface View extends BaseView {
        void showSearchListWechat(ArticleListBean articleListBean);

        void showMoreSearchListWechat(ArticleListBean articleListBean);

        void showAddCollectArticle(int position, ArticleBean articleBean);

        void showCancelCollectArticle(int position, ArticleBean articleBean);
    }

    interface Presener extends BasePresenter<View> {
        void getSearchListtWechat(int id, int page, String key);

        void getMoreSearchListtWechat(int id, int page, String key);

        void addCollectArticle(int position, ArticleBean articleBean);

        void cancelCollectArticle(int position, ArticleBean articleBean);
    }
}
