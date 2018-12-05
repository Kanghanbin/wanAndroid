package com.kanghanbin.wanandroid.presenter;

import java.util.List;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.UseContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.FriendBean;

/**
 * 创建时间：2018/11/9
 * 编写人：kanghb
 * 功能描述：
 */
public class UsePresenter extends RxPresenter<UseContract.View> implements UseContract.Presenter {

    @Override
    public void getUseFriend() {
        addSubscribe(apiService.getFriend()
                .compose(RxUtil.<BaseResponse<List<FriendBean>>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<List<FriendBean>>handleResult())
                .subscribeWith(new BaseSubscriber<List<FriendBean>>(mView) {
                    @Override
                    public void onNext(List<FriendBean> friendBeans) {
                        mView.showUseFriend(friendBeans);
                    }
                }));
    }
}
