package kanghb.com.wanandroid.base;

/**
 * 创建时间：2018/10/22
 * 编写人：kanghb
 * 功能描述：View基类
 */
public interface BaseView {
    /**
     * 显示Loading
     */
    void showLoading();

    /**
     * 关闭Loading
     */
    void closeLoading();

    /**
     * 显示吐丝
     */
    void showToast(String message);

    /**
     * 显示请求成功
     */
    void showSuccess();

    /**
     * 失败重试
     */
    void showFailed();


    /**
     * 重试
     */
    void onRetry();


    void startLoginActivity();

    void showLoginAccount(String account);

    void showLoginPassword(String pwd);



}
