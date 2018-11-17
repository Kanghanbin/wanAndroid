package kanghb.com.wanandroid.db;

import com.blankj.utilcode.util.SPUtils;

import java.util.HashSet;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.util.Constant;

/**
 * 创建时间：2018/11/8
 * 编写人：kanghb
 * 功能描述：
 */
public class SharePreferencesHelper implements ISharePreference {

    private static SharePreferencesHelper mSharePreferencesHelper;
    private SPUtils spUtils;

    private SharePreferencesHelper() {
        spUtils = SPUtils.getInstance(R.string.app_name);
    }

    public static SharePreferencesHelper getInstance() {
        if (mSharePreferencesHelper == null) {
            synchronized (SharePreferencesHelper.class) {
                if (mSharePreferencesHelper == null) {
                    mSharePreferencesHelper = new SharePreferencesHelper();
                }
            }
        }
        return mSharePreferencesHelper;
    }

    @Override
    public void setLoginAccount(String account) {
        spUtils.put(Constant.ACCOUNT, account);
    }

    @Override
    public void setLoginPassword(String password) {
        spUtils.put(Constant.PASSWORD, password);
    }

    @Override
    public void setLoginStatus(boolean b) {
       spUtils.put(Constant.LOGIN_STATUS,b);
    }

    @Override
    public void setCookie(HashSet<String> cookie) {
        spUtils.put(Constant.COOKIES,cookie);
    }

    @Override
    public void setCurrentItem(int currentItem) {
        spUtils.put(Constant.CURRENT_ITEM,currentItem);
    }

    @Override
    public String getLoginAccount() {
        return spUtils.getString(Constant.ACCOUNT);
    }

    @Override
    public String getLoginPassword() {
        return spUtils.getString(Constant.PASSWORD);
    }

    @Override
    public boolean getLoginStatus() {
        return spUtils.getBoolean(Constant.LOGIN_STATUS);
    }

    @Override
    public HashSet<String> getCookie() {
        return (HashSet<String>) spUtils.getStringSet(Constant.COOKIES,new HashSet<String>());
    }

    @Override
    public int getCurrentItem() {
        return spUtils.getInt(Constant.CURRENT_ITEM);
    }


}
