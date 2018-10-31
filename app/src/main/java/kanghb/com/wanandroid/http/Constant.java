package kanghb.com.wanandroid.http;

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
}
