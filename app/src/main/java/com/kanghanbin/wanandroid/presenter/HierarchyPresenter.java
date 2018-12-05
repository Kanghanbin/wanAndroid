package com.kanghanbin.wanandroid.presenter;

import java.util.List;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.HierarchyContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.HierarchyBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyPresenter extends RxPresenter<HierarchyContract.View> implements HierarchyContract.Presenter {


    @Override
    public void getHierarchyTree() {
        addSubscribe(apiService.getHierarchyTree()
        .compose(RxUtil.<BaseResponse<List<HierarchyBean>>>rxFlowableSchedulerHelper())
        .compose(RxUtil.<List<HierarchyBean>>handleResult())
        .subscribeWith(new BaseSubscriber<List<HierarchyBean>>(mView) {
            @Override
            public void onNext(List<HierarchyBean> hierarchyBeanList) {
                mView.shoHierarchyTree(hierarchyBeanList);
            }
        }));
    }
}
