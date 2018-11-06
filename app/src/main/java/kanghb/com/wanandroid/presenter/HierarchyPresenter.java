package kanghb.com.wanandroid.presenter;

import java.util.List;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.HierarchyContract;
import kanghb.com.wanandroid.contract.HomeContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.HierarchyBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyPresenter extends RxPresenter<HierarchyContract.View> implements HierarchyContract.Presenter {

    private ApiService mApiService;

    public HierarchyPresenter(){
        mApiService = RetrofitHelper.getInstance().getApiService();
    }


    @Override
    public void getHierarchyTree() {
        addSubscribe(mApiService.getHierarchyTree()
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
