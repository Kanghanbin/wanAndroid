package kanghb.com.wanandroid;

import android.app.Application;

/**
 * 创建时间：2018/10/19
 * 编写人：kanghb
 * 功能描述：
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
