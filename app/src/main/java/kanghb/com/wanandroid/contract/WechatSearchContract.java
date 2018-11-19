package kanghb.com.wanandroid.contract;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;

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
