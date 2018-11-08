package kanghb.com.wanandroid.contract;

import java.util.List;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.HierarchyBean;

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
