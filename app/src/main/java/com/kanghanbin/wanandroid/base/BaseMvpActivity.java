package com.kanghanbin.wanandroid.base;

import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;

import com.blankj.utilcode.util.SnackbarUtils;
import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.ui.activity.MainActivity;
import com.kanghanbin.wanandroid.util.IntentUtil;



/**
 * 创建时间：2018/10/25
 * 编写人：kanghb
 * 功能描述：MVP activity基类(抽象类)
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    protected T mPresenter;


    @Override
    protected void onViewCreated() {
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }


    @Override
    public void showToast(String message) {
        SnackbarUtils.with(findViewById(android.R.id.content)).setMessage(message).setDuration(SnackbarUtils.LENGTH_SHORT).show();
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFailed() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void showUseNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        //recreate();会有退出应用效果，闪屏效果
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_activity,R.anim.exit_activity);
        finish();

    }

    @Override
    public void startLoginActivity() {
        onBackPressedSupport();
        IntentUtil.startLoginActivity(mContext);
        mPresenter.setLoginStatus(false);
        mPresenter.setLoginAccount("");
        mPresenter.setLoginPassword("");
    }

    @Override
    public void showLoginPassword(String pwd) {

    }

    @Override
    public void showLoginAccount(String account) {

    }

    protected abstract void initPresenter();


}
