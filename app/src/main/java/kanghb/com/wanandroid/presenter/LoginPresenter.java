package kanghb.com.wanandroid.presenter;

import android.text.TextUtils;

import kanghb.com.wanandroid.MyApplication;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.RxBus;
import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.LoginContract;
import kanghb.com.wanandroid.eventtype.EventLogin;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.UserBean;
import kanghb.com.wanandroid.db.SharePreferencesHelper;

/**
 * 创建时间：2018/10/26
 * 编写人：kanghb
 * 功能描述：
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter() {
        apiService = RetrofitHelper.getInstance().getApiService();
    }

    @Override
    public void login(final String username, final String password) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            mView.showToast("用户名或密码不能为空");
            return;
        }
        addSubscribe(apiService.login(username, password)
                .compose(RxUtil.<BaseResponse<UserBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<UserBean>handleResult())
                .subscribeWith(new BaseSubscriber<UserBean>(mView) {
                    @Override
                    public void onNext(UserBean userBean) {
                        mView.showToast("登录成功");
                        mView.gotoMain();
                        sharePreferencesHelper.setLoginAccount(username);
                        sharePreferencesHelper.setLoginPassword(password);
                        sharePreferencesHelper.setLoginStatus(true);
                        RxBus.getDefault().post(new EventLogin(true));
                    }
                }));
    }

    @Override
    public void regist(String username, String password, String repassword) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)){
            mView.showToast("用户名或密码不能为空");
            return;
        }
        addSubscribe(apiService.register(username, password, password)
                .compose(RxUtil.<BaseResponse<UserBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<UserBean>handleResult())
                .subscribeWith(new BaseSubscriber<UserBean>(mView, MyApplication.getInstance().getString(R.string.regist_fail)) {
                    @Override
                    public void onNext(UserBean userBean) {
                        mView.showToast("注册成功，请登录");
                        mView.gotoLogin();
                    }
                }));
    }
}
