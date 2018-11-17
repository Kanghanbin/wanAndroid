package kanghb.com.wanandroid.contract;

import java.util.List;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.ProjectBean;
import retrofit2.http.Field;
import retrofit2.http.Path;

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
