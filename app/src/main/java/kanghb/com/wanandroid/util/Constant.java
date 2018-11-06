package kanghb.com.wanandroid.util;

import java.io.File;

import kanghb.com.wanandroid.MyApplication;

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




}
