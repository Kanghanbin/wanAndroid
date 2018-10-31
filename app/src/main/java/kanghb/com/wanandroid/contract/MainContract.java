package kanghb.com.wanandroid.contract;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;

/**
 * 创建时间：2018/10/30
 * 编写人：kanghb
 * 功能描述：
 */
public interface MainContract {
    interface View extends BaseView{
        void showSwitchProject();

        void showSwitchHierarchy();

        void showSwitchNavigation();


    }
    interface Presenter extends BasePresenter<View>{

        void setCurrentItem();
    }
}
