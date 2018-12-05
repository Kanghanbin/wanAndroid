package com.kanghanbin.wanandroid.presenter;

import java.util.List;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.WechatContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.WxarticleBean;

/**
 * 创建时间：2018/11/19
 * 编写人：kanghb
 * 功能描述：
 */
public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {
    @Override
    public void geWechatTree() {
        addSubscribe(apiService.getWxarticleList()
        .compose(RxUtil.<BaseResponse<List<WxarticleBean>>>rxFlowableSchedulerHelper())
        .compose(RxUtil.<List<WxarticleBean>>handleResult())
        .subscribeWith(new BaseSubscriber<List<WxarticleBean>>(mView) {
            @Override
            public void onNext(List<WxarticleBean> wxarticleBeanList) {
                mView.showWechatTree(wxarticleBeanList);
            }
        }));
    }
}
