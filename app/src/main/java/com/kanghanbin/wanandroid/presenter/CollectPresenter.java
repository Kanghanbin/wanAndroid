package com.kanghanbin.wanandroid.presenter;

import com.kanghanbin.wanandroid.MyApplication;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.CollectContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/11/13
 * 编写人：kanghb
 * 功能描述：
 */
public class CollectPresenter extends RxPresenter<CollectContract.View> implements CollectContract.Presenter {

    private int currentPage = 0;


    @Override
    public void getCollectListArticles() {
        currentPage = 0;
        addSubscribe(apiService.getCollectArticleList(currentPage)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showCollectListArticles(articleListBean);
                    }
                }));
    }

    @Override
    public void getMoreCollectListArticles() {
        currentPage++;
        addSubscribe(apiService.getCollectArticleList(currentPage)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        if (articleListBean == null || articleListBean.getDatas() == null || articleListBean.getDatas().size() == 0) {
                            currentPage--;
                        }
                        mView.showMoreCollectListArticles(articleListBean);
                    }
                }));
    }

    @Override
    public void unCollectFromCollectPage(final int pos, final ArticleBean articleBean) {
        addSubscribe(apiService.unCollectFromCollectPage(articleBean.getId(), articleBean.getOriginId())
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.uncollect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        articleBean.setCollect(false);
                        mView.showUnCollectFromCollectPage(pos,articleBean);
                        mView.showToast(s);
                    }
                }));
    }


}
