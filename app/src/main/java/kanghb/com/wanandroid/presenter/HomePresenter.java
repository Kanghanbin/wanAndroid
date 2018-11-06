package kanghb.com.wanandroid.presenter;

import java.util.List;

import kanghb.com.wanandroid.MyApplication;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.HomeContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.BannerBean;
import kanghb.com.wanandroid.ui.fragment.HomeFragment;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {
    private ApiService apiService;
    private int current;

    public HomePresenter() {
        apiService = RetrofitHelper.getInstance().getApiService();
    }

    @Override
    public void getBanner() {
        addSubscribe(apiService.getBannerList()
                .compose(RxUtil.<BaseResponse<List<BannerBean>>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<List<BannerBean>>handleResult())
                .subscribeWith(new BaseSubscriber<List<BannerBean>>(mView) {

                    @Override
                    public void onNext(List<BannerBean> bannerBeanList) {
                        mView.showBanner(bannerBeanList);
                    }
                }));
    }

    @Override
    public void getArticleList() {
        addSubscribe(apiService.getArticleList(current)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showArticleList(articleListBean);
                    }
                }));
    }

    @Override
    public void getArticleMore() {
        addSubscribe(apiService.getArticleList(current)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {
                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showArticleListMore(articleListBean);
                    }
                }));
    }

    @Override
    public void autoRefresh() {
        current = 0;
        getBanner();
        getArticleList();

    }

    @Override
    public void loadMore() {
        current++;
        getArticleMore();
    }


}
