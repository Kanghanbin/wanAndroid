package kanghb.com.wanandroid.contract;

import com.youth.banner.Banner;

import java.util.List;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.BannerBean;

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
