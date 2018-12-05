package com.kanghanbin.wanandroid.presenter;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.HierarchyDetailListContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RetrofitHelper;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

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
