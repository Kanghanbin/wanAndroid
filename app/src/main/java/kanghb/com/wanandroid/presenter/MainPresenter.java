package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.MainContract;
import kanghb.com.wanandroid.db.SharePreferencesHelper;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.util.Constant;

/**
 * 创建时间：2018/10/30
 * 编写人：kanghb
 * 功能描述：
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {


    @Override
    public void setCurrentItem(int currentItem) {
        sharePreferencesHelper.setCurrentItem(currentItem);
    }

    @Override
    public int getCurrentItem() {
        return sharePreferencesHelper.getCurrentItem();
    }

    @Override
    public void logout() {
        addSubscribe(apiService.logout()
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleResult())
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        clearCookie();
                        setLoginAccount("");
                        setLoginPassword("");
                        setLoginStatus(false);
                        mView.showLogout();

                    }
                }));
    }
}
