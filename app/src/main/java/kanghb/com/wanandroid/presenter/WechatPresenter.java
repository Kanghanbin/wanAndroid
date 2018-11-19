package kanghb.com.wanandroid.presenter;

import java.util.List;

import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.WechatContract;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.WxarticleBean;

/**
 * 创建时间：2018/11/19
 * 编写人：kanghb
 * 功能描述：
 */
public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {
    @Override
    public void geWechatTree() {
        addSubscribe(apiService.getWxarticleList()
        .compose(RxUtil.<BaseResponse<List<WxarticleBean>>>rxFlowableSchedulerHelper())
        .compose(RxUtil.<List<WxarticleBean>>handleResult())
        .subscribeWith(new BaseSubscriber<List<WxarticleBean>>(mView) {
            @Override
            public void onNext(List<WxarticleBean> wxarticleBeanList) {
                mView.showWechatTree(wxarticleBeanList);
            }
        }));
    }
}
