package com.kanghanbin.wanandroid.presenter;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.ProjectListContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class ProjectListPresenter extends RxPresenter<ProjectListContract.View> implements ProjectListContract.Presenter {


    @Override
    public void getRefreshProjectArticleList(int page, int cid) {
        addSubscribe(apiService.getProjectArticleList(page, cid)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {

                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showRefreshProjectArticleResult(articleListBean);
                    }
                }));
    }

    @Override
    public void getMoreProjectArticleList(int page, int cid) {
        addSubscribe(apiService.getProjectArticleList(page, cid)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {

                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showMoreProjectArticleResult(articleListBean);
                    }
                }));
    }
}
