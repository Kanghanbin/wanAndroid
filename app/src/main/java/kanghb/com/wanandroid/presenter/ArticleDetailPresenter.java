package kanghb.com.wanandroid.presenter;

import android.app.Application;

import kanghb.com.wanandroid.MyApplication;
import kanghb.com.wanandroid.R;
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
public class ArticleDetailPresenter extends RxPresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    @Override
    public void addCollect(int id) {
        addSubscribe(apiService.addCollect(id)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.collect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showToast(s);
                        mView.showAddCollect();
                    }
                }));
    }

    @Override
    public void cancelCollect(int id) {
        addSubscribe(apiService.unCollect(id)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.uncollect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showToast(s);
                        mView.showCancelCollect();
                    }
                }));
    }

    @Override
    public void cancelCollectFromCollect(int id) {
        addSubscribe(apiService.unCollectFromCollectPage(id,-1)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.uncollect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showToast(s);
                        mView.showCancelCollect();
                    }
                }));
    }
}
