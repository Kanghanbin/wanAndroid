package kanghb.com.wanandroid.presenter;


import java.util.List;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.NavigationContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.NavigationBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class NavigationPresenter extends RxPresenter<NavigationContract.View> implements NavigationContract.Presenter {

    private ApiService apiService;

    public NavigationPresenter() {
        apiService = RetrofitHelper.getInstance().getApiService();
    }

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
