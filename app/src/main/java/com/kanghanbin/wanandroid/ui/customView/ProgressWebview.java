package com.kanghanbin.wanandroid.ui.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.NetworkUtils;
import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.db.SharePreferencesHelper;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


/**
 * 创建时间：2018/11/8
 * 编写人：kanghb
 * 功能描述：一个自带进度条的x5内核webview
 */
public class ProgressWebview extends WebView {
    //进度条
    private ProgressBar progressbar;

    //进度条的高度，默认10px
    private int progressHeight = 15;

    public ProgressWebview(Context context) {
        super(context);
    }

    public ProgressWebview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        WebSettings webSettings = getSettings();
        //开启js脚本支持
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        //创建进度条
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        //设置加载进度条的高度
        progressbar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, progressHeight));

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.laylist_progress_bar);
        progressbar.setProgressDrawable(drawable);

        //添加进度到WebView
        addView(progressbar);

        //适配手机大小
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        webSettings.setDisplayZoomControls(false);
        if (SharePreferencesHelper.getInstance().getNoImageMode()) {
            webSettings.setBlockNetworkImage(true);
        } else {
            webSettings.setBlockNetworkImage(false);
        }
        if (SharePreferencesHelper.getInstance().getAutoCache()) {
            webSettings.setAppCacheEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            if (NetworkUtils.isConnected()) {
                webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        } else {
            webSettings.setAppCacheEnabled(false);
            webSettings.setDomStorageEnabled(false);
            webSettings.setDatabaseEnabled(false);
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        setWebChromeClient(new WVChromeClient());
        setWebViewClient(new WVClient());
    }


    /**
     * 进度显示
     */
    private class WVChromeClient extends WebChromeClient {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }

            if (mListener != null) {
                mListener.onProgressChange(view, newProgress);
            }

            super.onProgressChanged(view, newProgress);
        }

    }

    private class WVClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            //在当前Activity打开
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //https忽略证书问题
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            progressbar.setVisibility(GONE);
            if (mListener != null) {
                mListener.onPageFinish(view);
            }

            super.onPageFinished(view, url);

        }

    }

    private onWebViewListener mListener;

    public void setOnWebViewListener(onWebViewListener listener) {
        this.mListener = listener;
    }

    //进度回调接口
    public interface onWebViewListener {
        void onProgressChange(WebView view, int newProgress);

        void onPageFinish(WebView view);
    }


}
