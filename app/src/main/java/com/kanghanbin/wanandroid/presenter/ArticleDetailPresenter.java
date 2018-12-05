package com.kanghanbin.wanandroid.presenter;

import android.Manifest;

import com.kanghanbin.wanandroid.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
import com.kanghanbin.wanandroid.MyApplication;
import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.ArticleDetailContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;

/**
 * 创建时间：2018/11/7
 * 编写人：kanghb
 * 功能描述：
 */
public class ArticleDetailPresenter extends RxPresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    @Override
    public void addCollect(int id) {
        addSubscribe(apiService.addCollect(id)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.collect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showToast(s);
                        mView.showAddCollect();
                    }
                }));
    }

    @Override
    public void cancelCollect(int id) {
        addSubscribe(apiService.unCollect(id)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.uncollect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showToast(s);
                        mView.showCancelCollect();
                    }
                }));
    }

    @Override
    public void cancelCollectFromCollect(int id) {
        addSubscribe(apiService.unCollectFromCollectPage(id,-1)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.uncollect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showToast(s);
                        mView.showCancelCollect();
                    }
                }));
    }

    @Override
    public void shareEventPermissionVerify(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if(aBoolean){
                    mView.shareEvent();
                }else {
                    mView.shareError();
                }
            }
        }));
    }
}
