package com.kanghanbin.wanandroid.http;

import android.text.TextUtils;

import com.kanghanbin.wanandroid.MyApplication;
import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.util.Constant;

import io.reactivex.subscribers.ResourceSubscriber;
import com.kanghanbin.wanandroid.base.BaseView;

import retrofit2.HttpException;

/**
 * 创建时间：2018/10/29
 * 编写人：kanghb
 * 功能描述：
 */
public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState = true;

    public BaseSubscriber(BaseView mView) {
        this.mView = mView;
    }

    public BaseSubscriber(BaseView mView, boolean isShowErrorState) {
        this.mView = mView;
        this.isShowErrorState = isShowErrorState;
    }

    public BaseSubscriber(BaseView mView, String mErrorMsg) {
        this.mView = mView;
        this.mErrorMsg = mErrorMsg;
    }

    public BaseSubscriber(BaseView mView, String mErrorMsg, boolean isShowErrorState) {
        this.mView = mView;
        this.mErrorMsg = mErrorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {
        if(mView == null){
            return;
        }
        if(mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)){
            mView.showToast(mErrorMsg);
        }else if(t instanceof ApiException){
            if(((ApiException) t).getCode() == Constant.UN_LOGIN || t.getMessage().equals(Constant.UN_LOGIN_MESSAGE)){
                mView.startLoginActivity();
            }
            mView.showToast(t.getMessage().toString());
        }else if(t instanceof HttpException){
            mView.showToast(MyApplication.getInstance().getString(R.string.data_fail));
        }else {
            mView.showToast(MyApplication.getInstance().getString(R.string.unknow_error));
        }
        if(isShowErrorState){
            mView.showFailed();
        }
    }
}
