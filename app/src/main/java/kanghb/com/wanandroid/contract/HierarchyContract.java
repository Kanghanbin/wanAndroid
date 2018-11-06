package kanghb.com.wanandroid.contract;

import java.util.List;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.HierarchyBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public interface HierarchyContract {
    interface View extends BaseView{
        void shoHierarchyTree(List<HierarchyBean> hierarchyBeanList);
    }

    interface Presenter extends BasePresenter<View>{

        void getHierarchyTree();
    }
}
