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
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.BannerBean;
import kanghb.com.wanandroid.ui.fragment.HomeFragment;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private int current;

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

    @Override
    public void addCollectArticle(final int position, final ArticleBean articleBean) {
        addSubscribe(apiService.addCollect(articleBean.getId())
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.collect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        articleBean.setCollect(true);
                        mView.showAddCollectArticle(position,articleBean);
                        mView.showToast(s);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(final int position, final ArticleBean articleBean) {
        addSubscribe(apiService.unCollect(articleBean.getId())
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.uncollect_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        articleBean.setCollect(false);
                        mView.showCancelCollectArticle(position,articleBean);
                        mView.showToast(s);
                    }
                }));

    }


}
