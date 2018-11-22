package kanghb.com.wanandroid.presenter;

import io.reactivex.functions.Function;
import kanghb.com.wanandroid.MyApplication;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.RxBus;
import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.MainContract;
import kanghb.com.wanandroid.db.SharePreferencesHelper;
import kanghb.com.wanandroid.eventtype.EventNightMode;
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
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registEvent();
    }

    private void registEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(EventNightMode.class)
                .compose(RxUtil.<EventNightMode>rxFlowableSchedulerHelper())
                .map(new Function<EventNightMode, Boolean>() {
                    @Override
                    public Boolean apply(EventNightMode eventNightMode) throws Exception {
                        return eventNightMode.isNight();
                    }
                })
                .subscribeWith(new BaseSubscriber<Boolean>(mView, MyApplication.getInstance().getString(R.string.cast_night_mode_failed)) {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.showUseNightMode(aBoolean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        registEvent();
                    }
                }));
    }

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
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.logout_success)))
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
