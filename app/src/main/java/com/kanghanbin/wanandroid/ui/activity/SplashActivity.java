package com.kanghanbin.wanandroid.ui.activity;

import android.animation.Animator;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.BaseMvpActivity;
import com.kanghanbin.wanandroid.contract.SplashContract;
import com.kanghanbin.wanandroid.presenter.SplashPresenter;

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {


    @BindView(R.id.lottieAnim_splash)
    LottieAnimationView lottieAnimSplash;
    @BindView(R.id.lottieAnim_welcome)
    LottieAnimationView lottieAnimWelcome;

    @Override
    protected void initPresenter() {
        mPresenter = new SplashPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        StatusBarUtil.immersive(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        lottieAnimWelcome.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieAnimSplash.playAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lottieAnimSplash.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                jumpToMain();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void jumpToMain() {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(lottieAnimSplash, lottieAnimSplash.getWidth() / 2, lottieAnimSplash.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(mContext, new Intent(this, MainActivity.class), compat.toBundle());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        lottieAnimWelcome.cancelAnimation();
        lottieAnimSplash.cancelAnimation();
        onBackPressedSupport();
    }


}
