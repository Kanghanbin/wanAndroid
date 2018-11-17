package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.HierarchyDetailListContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyDetailListPresenter extends RxPresenter<HierarchyDetailListContract.View> implements HierarchyDetailListContract.Presenter {

    public HierarchyDetailListPresenter() {
        apiService = RetrofitHelper.getInstance().getApiService();
    }


    @Override
    public void getRefreshHierarchyArticleList(int page, int cid) {
        addSubscribe(apiService.getHierarchyArticleList(page, cid)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showRefreshHierarchyArticleList(articleListBean);
                    }
                }));
    }

    @Override
    public void getMoreHierarchyArticleList(int page, int cid) {
        addSubscribe(apiService.getHierarchyArticleList(page, cid)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showMoreHierarchyArticleList(articleListBean);
                    }
                }));
    }
}
