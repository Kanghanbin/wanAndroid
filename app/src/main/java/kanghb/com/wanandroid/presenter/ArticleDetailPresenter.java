package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.ArticleDetailContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;

/**
 * 创建时间：2018/11/7
 * 编写人：kanghb
 * 功能描述：
 */
public class ArticleDetailPresenter extends RxPresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter{
    private ApiService mApiService;

    public ArticleDetailPresenter() {
        mApiService = RetrofitHelper.getInstance().getApiService();
    }

    @Override
    public void addCollect(int id) {
        addSubscribe(mApiService.addCollect(id)
        .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
        .compose(RxUtil.<String>handleResult())
        .subscribeWith(new BaseSubscriber<String>(mView) {
            @Override
            public void onNext(String s) {
                mView.showAddCollect();
            }
        }));
    }

    @Override
    public void cancelCollect(int id) {
        addSubscribe(mApiService.unCollect(id)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleResult())
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showCancelCollect();
                    }
                }));
    }
}
