package com.kanghanbin.wanandroid.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.kanghanbin.wanandroid.R;


/**
 * 创建时间：2018/10/29
 * 编写人：kanghb
 * 功能描述：
 */
public abstract class BaseListFragment<T extends BasePresenter> extends BaseMvpFragment<T> {
    //    private static final int LOADING = 0x00;
//    private static final int SUCCESS = 0x01;
//    private static final int FAIL = 0x02;

    private int errorLayout = R.layout.view_error;
    private int loadLayout = R.layout.view_loading;
    private LottieAnimationView lottieAnimationView;
    private Button btnRetry;

    private ViewGroup parentView;
    private View rvMainView;
    private View viewLoading;
    private View viewError;

    @Override
    protected void initEventAndData() {
        if (mView == null)
            return;
        rvMainView = mView.findViewById(R.id.rv_main);
        if (rvMainView == null) {
            throw new IllegalStateException("The subclass of BaseMvpActivity must contain a View named 'rv_main'.");
        }
        if (!(rvMainView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("rv_main's ParentView should be a ViewGroup.");
        }
        parentView = (ViewGroup) rvMainView.getParent();
        View.inflate(mContext, errorLayout, parentView);
        View.inflate(mContext, loadLayout, parentView);
        viewLoading = parentView.findViewById(R.id.fl_loading);
        viewError = parentView.findViewById(R.id.ll_error);
        lottieAnimationView = viewLoading.findViewById(R.id.lottieAnim);
        btnRetry = viewError.findViewById(R.id.btn_retry);
        rvMainView.setVisibility(View.VISIBLE);
        viewLoading.setVisibility(View.GONE);
        viewError.setVisibility(View.GONE);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetry();
            }
        });

    }


    @Override
    public void showLoading() {
        viewLoading.setVisibility(View.VISIBLE);
        rvMainView.setVisibility(View.GONE);
        viewError.setVisibility(View.GONE);
        if (lottieAnimationView == null) {
            lottieAnimationView = new LottieAnimationView(mContext);
            lottieAnimationView.setAnimation("bounching_ball.json");
            lottieAnimationView.setRepeatMode(LottieDrawable.RESTART);
            lottieAnimationView.playAnimation();
        }
    }

    @Override
    public void closeLoading() {
        viewLoading.setVisibility(View.VISIBLE);
        rvMainView.setVisibility(View.GONE);
        viewError.setVisibility(View.GONE);
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
    }


    @Override
    public void showFailed() {
        viewLoading.setVisibility(View.GONE);
        rvMainView.setVisibility(View.GONE);
        viewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess() {
        viewLoading.setVisibility(View.GONE);
        viewError.setVisibility(View.GONE);
        rvMainView.setVisibility(View.VISIBLE);

    }
}
