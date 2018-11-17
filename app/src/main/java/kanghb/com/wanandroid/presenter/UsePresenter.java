package kanghb.com.wanandroid.presenter;

import java.util.List;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.UseContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.FriendBean;

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
