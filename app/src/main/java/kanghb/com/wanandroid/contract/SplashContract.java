package kanghb.com.wanandroid.contract;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;

/**
 * 创建时间：2018/11/20
 * 编写人：kanghb
 * 功能描述：
 */

public interface SplashContract {

    interface View extends BaseView {

        void jumpToMain();
    }

    interface Presenter extends BasePresenter<View> {

    }

}
