package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public interface HierarchyDetailListContract {
    interface View extends BaseView {
        void showRefreshHierarchyArticleList(ArticleListBean articleListBean);

        void showMoreHierarchyArticleList(ArticleListBean articleListBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getRefreshHierarchyArticleList(int page, int cid);

        void getMoreHierarchyArticleList(int page, int cid);
    }
}
