package kanghb.com.wanandroid.contract;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/11/13
 * 编写人：kanghb
 * 功能描述：
 */
public interface SearchListContract {
    interface View extends BaseView {
        void showSearchListArticles(ArticleListBean articleListBean);

        void showMoreSearchListArticles(ArticleListBean articleListBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getSearchListArticles(String key);

        void getMoreSearchListArticles(String key);
    }
}
