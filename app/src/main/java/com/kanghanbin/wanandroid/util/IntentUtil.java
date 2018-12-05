package com.kanghanbin.wanandroid.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;

import com.kanghanbin.wanandroid.model.bean.ToDoBean;
import com.kanghanbin.wanandroid.model.bean.WxarticleBean;
import com.kanghanbin.wanandroid.ui.activity.ArticleDetailActivity;
import com.kanghanbin.wanandroid.ui.activity.HotKeySearchActivity;
import com.kanghanbin.wanandroid.ui.activity.LoginActivity;
import com.kanghanbin.wanandroid.ui.activity.ProjectDetailActivity;
import com.kanghanbin.wanandroid.ui.activity.SearchListActivity;
import com.kanghanbin.wanandroid.ui.activity.TodoActivity;
import com.kanghanbin.wanandroid.ui.activity.TodoDetailActivity;
import com.kanghanbin.wanandroid.ui.activity.UseActivity;
import com.kanghanbin.wanandroid.ui.activity.WechatActivity;
import com.kanghanbin.wanandroid.ui.activity.WechatSearchActivity;

/**
 * 创建时间：2018/11/7
 * 编写人：kanghb
 * 功能描述：页面跳转类
 */
public class IntentUtil {
    /**
     * @param mActivity
     * @param activityOptions
     * @param id
     * @param articleTitle
     * @param articleLink
     * @param articleImg
     * @param isCollect
     * @param isCollectPage   当前页是否是收藏
     */
    public static void startProjectDetailActivity(Context mActivity, ActivityOptionsCompat activityOptions, int id, String articleTitle,
                                                  String articleLink, String articleImg, boolean isCollect, boolean isCollectPage, boolean isCollectSupport) {
        Intent intent = new Intent(mActivity, ProjectDetailActivity.class);
        intent.putExtra(Constant.ARTICLE_ID, id);
        intent.putExtra(Constant.ARTICLE_TITLE, articleTitle);
        intent.putExtra(Constant.ARTICLE_LINK, articleLink);
        intent.putExtra(Constant.ARTICLE_IMG, articleImg);
        intent.putExtra(Constant.IS_COLLECT, isCollect);
        intent.putExtra(Constant.IS_COLLECT_PAGE, isCollectPage);
        intent.putExtra(Constant.IS_COLLECT_SUPPORT, isCollectSupport);
        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }
    }


    public static void startUseActivity(Context mActivity) {
        Intent intent = new Intent(mActivity, UseActivity.class);
        mActivity.startActivity(intent);
    }


    public static void startHotKeySearchActivity(Context mActivity) {
        Intent intent = new Intent(mActivity, HotKeySearchActivity.class);
        mActivity.startActivity(intent);
    }

    public static void startArticleDetailActivity(Context mActivity, ActivityOptionsCompat activityOptions, int id, String articleTitle,
                                                  String articleLink, boolean isCollect, boolean isCollectPage) {
        Intent intent = new Intent(mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constant.ARTICLE_ID, id);
        intent.putExtra(Constant.ARTICLE_TITLE, articleTitle);
        intent.putExtra(Constant.ARTICLE_LINK, articleLink);
        intent.putExtra(Constant.IS_COLLECT, isCollect);
        intent.putExtra(Constant.IS_COLLECT_PAGE, isCollectPage);
        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }
    }

    public static void startSearchListActivity(Context mActivity, String searchText) {
        Intent intent = new Intent(mActivity, SearchListActivity.class);
        intent.putExtra(Constant.ARG_PARAM1, searchText);
        mActivity.startActivity(intent);
    }

    public static void startLoginActivity(Context mActivity) {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
    }

    public static void startTodoActivity(Context mActivity) {
        Intent intent = new Intent(mActivity, TodoActivity.class);
        mActivity.startActivity(intent);
    }

    public static void startTodoDetailActiviy(Context mContext, ToDoBean toDoBean) {
        Intent intent = new Intent(mContext, TodoDetailActivity.class);
        intent.putExtra(Constant.TODOBEAN, toDoBean);
        mContext.startActivity(intent);
    }

    public static void startWechatActivity(Context mActivity) {
        Intent intent = new Intent(mActivity, WechatActivity.class);
        mActivity.startActivity(intent);
    }

    public static void startWechatSearchActivtiy(Context mActivity, WxarticleBean wxarticleBean) {
        Intent intent = new Intent(mActivity, WechatSearchActivity.class);
        intent.putExtra(Constant.WXARTICLEBEAN, wxarticleBean);
        mActivity.startActivity(intent);
    }

}
