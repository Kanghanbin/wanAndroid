package com.kanghanbin.wanandroid.presenter;


import java.util.List;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.NavigationContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.NavigationBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class NavigationPresenter extends RxPresenter<NavigationContract.View> implements NavigationContract.Presenter {


    @Override
    public void getNavigation() {
        addSubscribe(apiService.getNavigationList()
                .compose(RxUtil.<BaseResponse<List<NavigationBean>>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<List<NavigationBean>>handleResult())
                .subscribeWith(new BaseSubscriber<List<NavigationBean>>(mView) {
                    @Override
                    public void onNext(List<NavigationBean> navigationBeanList) {
                        mView.showNavigationList(navigationBeanList);
                    }
                }));
    }
}
