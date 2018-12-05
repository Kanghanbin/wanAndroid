package com.kanghanbin.wanandroid.util;

import android.graphics.Color;

import com.kanghanbin.wanandroid.MyApplication;

import java.io.File;

/**
 * 创建时间：2018/10/19
 * 编写人：kanghb
 * 功能描述：
 */
public class Constant {


    public static String BaseUrl = "http://www.wanandroid.com";

    public static final String PATH_DATA = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";


    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";

    public static final int TYPE_ONE = 0;

    public static final int TYPE_WORK = 1;

    public static final int TYPE_STUDY = 2;

    public static final int TYPE_LIFE = 3;

    public static final int TYPE_HOME = 101;

    public static final int TYPE_HIERARCHY = 102;

    public static final int TYPE_NAVIGATION = 103;

    public static final int TYPE_PROJECT = 104;

    public static final int TYPE_USE = 105;

    public static final int TYPE_HOTSEARCH = 106;

    public static final int TYPE_ABOUT = 107;

    public static final int TYPE_SETTING = 108;

    public static final int TYPE_COLLECT = 109;

    public static final int TYPE_LIKE = 110;


    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String LOGIN_STATUS = "login_status";

    public static final String COOKIES = "cookies";

    public static final String CURRENT_ITEM = "current_item";

    public static final String NIGHT_MODE = "night_mode";

    public static final String NOIMAGE_MODE = "noimage_mode";

    public static final String AUTO_CACHE = "auto_cache";


    public static final String ARTICLE_LINK = "article_link";

    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_IMG = "article_img";

    public static final String ARTICLE_ID = "article_id";

    public static final String IS_COLLECT_SUPPORT = "is_collect_support";

    public static final String IS_COLLECT = "is_collect";

    public static final String IS_COLLECT_PAGE = "is_collect_page";

    public static final String TODOBEAN = "todobean";

    public static final String WXARTICLEBEAN = "wxarticlebean";

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#FF4500"),
            Color.parseColor("#98FB98"),
            Color.parseColor("#DC143C"),
            Color.parseColor("#CD853F"),
            Color.parseColor("#BA55D3"),
            Color.parseColor("#1E90FF"),
            Color.parseColor("#32CD32")
    };

    public static final int HISTORY_LIST_SIZE = 10;

    public static final int SUCCESS = 0;

    public static final int UN_LOGIN = -1001;

    public static final String UN_LOGIN_MESSAGE = "请先登录";

    public static final String MENU_BUILDER = "MenuBuilder";


}
