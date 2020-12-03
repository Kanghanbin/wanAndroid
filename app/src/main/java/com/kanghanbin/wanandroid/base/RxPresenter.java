package com.kanghanbin.wanandroid.base;

import com.kanghanbin.wanandroid.db.SharePreferencesHelper;
import com.kanghanbin.wanandroid.http.ApiService;
import com.kanghanbin.wanandroid.http.RetrofitHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 创建时间：2018/10/24
 * 编写人：kanghb
 * 功能描述：基于Rx的Presenter封装,控制订阅的生命周期
 * unsubscribe() 这个方法很重要，
 * 因为在 subscribe() 之后， Observable 会持有 Subscriber 的引用，
 * 这个引用如果不能及时被释放，将有内存泄露的风险。
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;
    protected SharePreferencesHelper sharePreferencesHelper;
    protected ApiService apiService;

    public RxPresenter() {
        apiService = RetrofitHelper.getInstance().getApiService();
        sharePreferencesHelper = SharePreferencesHelper.getInstance();
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }


    @Override
    public void getLoginAccount() {
        mView.showLoginAccount(sharePreferencesHelper.getLoginAccount());
    }

    @Override
    public void getLoignPassword() {
        mView.showLoginPassword(sharePreferencesHelper.getLoginPassword());
    }

    @Override
    public boolean getLoginStatus() {
        return sharePreferencesHelper.getLoginStatus();
    }

    @Override
    public void setLoginAccount(String account) {
        sharePreferencesHelper.setLoginAccount(account);
    }

    @Override
    public void setLoginPassword(String password) {
        sharePreferencesHelper.setLoginPassword(password);
    }

    @Override
    public void setLoginStatus(boolean b) {
        sharePreferencesHelper.setLoginStatus(b);
    }

    @Override
    public void clearCookie() {
        sharePreferencesHelper.removeCookie();
    }


}
