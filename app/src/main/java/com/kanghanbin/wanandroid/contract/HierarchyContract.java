package com.kanghanbin.wanandroid.contract;

import java.util.List;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.HierarchyBean;

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
