package com.kanghanbin.wanandroid.presenter;

import java.util.List;

import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.ProjectContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.ProjectBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class ProjectPresenter extends RxPresenter<ProjectContract.View> implements ProjectContract.Presenter {


    @Override
    public void getProjectList() {
        addSubscribe(apiService.getProjectList()
                .compose(RxUtil.<BaseResponse<List<ProjectBean>>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<List<ProjectBean>>handleResult())
                .subscribeWith(new BaseSubscriber<List<ProjectBean>>(mView) {
                    @Override
                    public void onNext(List<ProjectBean> projectBeanList) {
                        mView.showProjectResult(projectBeanList);
                    }
                }));
    }


}
