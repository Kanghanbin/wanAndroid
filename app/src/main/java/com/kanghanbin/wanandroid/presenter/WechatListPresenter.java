package com.kanghanbin.wanandroid.presenter;

import com.kanghanbin.wanandroid.MyApplication;


import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.WechatListContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/11/19
 * 编写人：kanghb
 * 功能描述：
 */
public class WechatListPresenter extends RxPresenter<WechatListContract.View> implements WechatListContract.Presenter {
    @Override
    public void getRefreshWechatArticleList(int id, int page) {
        addSubscribe(apiService.getHistoryWxarticleList(id, page)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showRefreshWechatArticleList(articleListBean);
                    }
                }));
    }

    @Override
    public void getMoreWechatArticleList(int id, int page) {
        addSubscribe(apiService.getHistoryWxarticleList(id, page)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showMoreWechatArticleList(articleListBean);
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
