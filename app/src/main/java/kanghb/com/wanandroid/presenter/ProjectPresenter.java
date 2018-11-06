package kanghb.com.wanandroid.presenter;

import java.util.List;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.HomeContract;
import kanghb.com.wanandroid.contract.ProjectContract;
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
public class ProjectPresenter extends RxPresenter<ProjectContract.View> implements ProjectContract.Presenter {

    private ApiService apiService;

    public ProjectPresenter() {
        apiService = RetrofitHelper.getInstance().getApiService();
    }

    @Override
    public void getProjectList() {
        addSubscribe(apiService.getProjectList()
                .compose(RxUtil.<BaseResponse<List<ProjectBean>>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<List<ProjectBean>>handleResult())
                .subscribeWith(new BaseSubscriber<List<ProjectBean>>(mView) {
                    @Override
                    public void onNext(List<ProjectBean> projectBeanList) {
                        mView.showProjectResult(projectBeanList);
                    }
                }));
    }


}
