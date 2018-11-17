package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.MainContract;
import kanghb.com.wanandroid.db.SharePreferencesHelper;
import kanghb.com.wanandroid.util.Constant;

/**
 * 创建时间：2018/10/30
 * 编写人：kanghb
 * 功能描述：
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {


    @Override
    public void setCurrentItem(int currentItem) {
        sharePreferencesHelper.setCurrentItem(currentItem);
    }

    @Override
    public int getCurrentItem() {
        return sharePreferencesHelper.getCurrentItem();
    }
}
