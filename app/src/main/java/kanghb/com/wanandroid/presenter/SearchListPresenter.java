package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.SearchListContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
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
}
