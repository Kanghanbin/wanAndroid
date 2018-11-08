package kanghb.com.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListActivity;
import kanghb.com.wanandroid.contract.HierarchyDetailContract;
import kanghb.com.wanandroid.model.bean.HierarchyBean;
import kanghb.com.wanandroid.presenter.HierarchyDetailPresenter;
import kanghb.com.wanandroid.ui.adapter.HierarchyFragementAdapter;
import kanghb.com.wanandroid.ui.fragment.HierarchyListFragment;
import kanghb.com.wanandroid.util.Constant;

/**
 * 创建时间：2018/11/7
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyDetailActivity extends BaseListActivity<HierarchyDetailPresenter> implements HierarchyDetailContract.View {

    @BindView(R.id.tl_hierarchy)
    TabLayout tlHierarchy;


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.rv_main)
    ViewPager rvMain;

    private HierarchyBean hierarchyBean;
    private List<HierarchyListFragment> fragments = new ArrayList<>();
    private List<HierarchyBean.ChildrenBean> childrenBeans = new ArrayList<>();

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        for (HierarchyBean.ChildrenBean childrenBean : hierarchyBean.getChildren()) {
            fragments.add(HierarchyListFragment.newInstance(childrenBean.getId(),null));
        }
        rvMain.setAdapter(new HierarchyFragementAdapter(getSupportFragmentManager(),fragments,childrenBeans ));
        tlHierarchy.setupWithViewPager(rvMain);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HierarchyDetailPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_list_hierarchy;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        hierarchyBean = (HierarchyBean) getIntent().getSerializableExtra(Constant.ARG_PARAM1);
        childrenBeans = hierarchyBean.getChildren();
        setToolBar(toolBar, hierarchyBean.getName());
    }


}
