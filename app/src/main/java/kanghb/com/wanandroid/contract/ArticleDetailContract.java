package kanghb.com.wanandroid.contract;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.HierarchyBean;

/**
 * 创建时间：2018/11/7
 * 编写人：kanghb
 * 功能描述：
 */
public interface ArticleDetailContract {
    interface View extends BaseView {
        void showAddCollect();

        void showCancelCollect();

        void shareEvent();

        void shareError();
    }

    interface Presenter extends BasePresenter<View> {

        void addCollect(int id);

        void cancelCollect(int id);

        void cancelCollectFromCollect(int id);

        void shareEventPermissionVerify(RxPermissions rxPermissions);
    }
}
