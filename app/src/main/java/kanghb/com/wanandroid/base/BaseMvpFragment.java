package kanghb.com.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.SnackbarUtils;

import kanghb.com.wanandroid.util.IntentUtil;


/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    protected T mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void showToast(String message) {
        SnackbarUtils.with(mActivity.findViewById(android.R.id.content)).setMessage(message).setDuration(SnackbarUtils.LENGTH_SHORT).show();
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
    public void startLoginActivity() {
        IntentUtil.startLoginActivity(mActivity);
    }

    protected abstract void initPresenter();

    @Override
    public void showLoginAccount(String account) {

    }

    @Override
    public void showLoginPassword(String pwd) {

    }
}
