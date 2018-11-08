package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.HierarchyDetailContract;
import kanghb.com.wanandroid.contract.HierarchyDetailListContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyDetailPresenter extends RxPresenter<HierarchyDetailContract.View> implements HierarchyDetailContract.Presenter {

    private ApiService mApiService;

    public HierarchyDetailPresenter() {
        mApiService = RetrofitHelper.getInstance().getApiService();
    }

}
