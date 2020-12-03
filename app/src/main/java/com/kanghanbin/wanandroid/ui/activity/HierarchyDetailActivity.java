package com.kanghanbin.wanandroid.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.ui.fragment.HierarchyListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.kanghanbin.wanandroid.base.BaseListActivity;
import com.kanghanbin.wanandroid.contract.HierarchyDetailContract;
import com.kanghanbin.wanandroid.model.bean.HierarchyBean;
import com.kanghanbin.wanandroid.presenter.HierarchyDetailPresenter;
import com.kanghanbin.wanandroid.ui.adapter.HierarchyFragementAdapter;
import com.kanghanbin.wanandroid.util.Constant;

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
        tlHierarchy.setBackgroundColor(ThemeUtils.getThemeColorStateList(mContext,R.color.theme_color_primary_dark).getDefaultColor());
        tlHierarchy.setSelectedTabIndicatorColor(ThemeUtils.getThemeColorStateList(mContext,R.color.theme_color_primary_trans).getDefaultColor());
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
