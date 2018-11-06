package kanghb.com.wanandroid.presenter;

import java.util.List;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.ProjectContract;
import kanghb.com.wanandroid.contract.ProjectListContract;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.ProjectBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class ProjectListPresenter extends RxPresenter<ProjectListContract.View> implements ProjectListContract.Presenter {

    private ApiService apiService;

    public ProjectListPresenter() {
        apiService = RetrofitHelper.getInstance().getApiService();
    }

    @Override
    public void getProjectArticleList(int page, int cid) {
        addSubscribe(apiService.getProjectArticleList(page, cid)
                .compose(RxUtil.<BaseResponse<ArticleListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ArticleListBean>handleResult())
                .subscribeWith(new BaseSubscriber<ArticleListBean>(mView) {

                    @Override
                    public void onNext(ArticleListBean articleListBean) {
                        mView.showProjectArticleResult(articleListBean);
                    }
                }));
    }
}
