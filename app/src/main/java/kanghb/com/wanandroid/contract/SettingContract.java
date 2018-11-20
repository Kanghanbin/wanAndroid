package kanghb.com.wanandroid.contract;

import java.io.File;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;

/**
 * 创建时间：2018/11/20
 * 编写人：kanghb
 * 功能描述：
 */
public interface SettingContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        boolean getNoImageModeState();

        boolean getAutoCacheState();

        boolean getNigthModeState();

        void setNoImageModeState(boolean b);

        void setAutoCacheModeState(boolean b);

        void setNightModeState(boolean b);

        void clearCache(File file);

        String getCacheSize4Format(File file);
    }
}
