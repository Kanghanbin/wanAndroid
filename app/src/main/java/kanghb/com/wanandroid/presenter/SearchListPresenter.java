package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.MyApplication;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.SearchListContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/11/13
 * 编写人：kanghb
 * 功能描述：
 */
public class SearchListPresenter extends RxPresenter<SearchListContract.View> implements SearchListContract.Presenter {
    private int currentPage = 0;

    @Override
    public void getSearchListArticles(String key) {
        currentPage = 0;
        addSubscribe(apiService.getSearchArticleList(currentPage, key)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showSearchListArticles(articleListBean);
                    }
                }));
    }

    @Override
    public void getMoreSearchListArticles(String key) {
        currentPage++;
        addSubscribe(apiService.getSearchArticleList(currentPage, key)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        if (articleListBean == null || articleListBean.getDatas() == null || articleListBean.getDatas().size() == 0) {
                            currentPage--;
                        }
                        mView.showMoreSearchListArticles(articleListBean);

                    }
                }));
    }

    @Override
    public void addCollectArticle(final int position, final ArticleBean articleBean) {
        addSubscribe(apiService.addCollect(articleBean.getId())
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.collect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        articleBean.setCollect(true);
                        mView.showAddCollectArticle(position,articleBean);
                        mView.showToast(s);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(final int position, final ArticleBean articleBean) {
        addSubscribe(apiService.unCollect(articleBean.getId())
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.uncollect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        articleBean.setCollect(false);
                        mView.showCancelCollectArticle(position,articleBean);
                        mView.showToast(s);
                    }
                }));

    }
}