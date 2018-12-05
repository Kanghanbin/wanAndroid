package com.kanghanbin.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.BaseListFragment;
import com.kanghanbin.wanandroid.contract.ProjectContract;
import com.kanghanbin.wanandroid.model.bean.ProjectBean;
import com.kanghanbin.wanandroid.presenter.ProjectPresenter;
import com.kanghanbin.wanandroid.ui.adapter.ProjectFragementAdapter;

import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM1;
import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM2;


public class ProjectFragment extends BaseListFragment<ProjectPresenter> implements ProjectContract.View {


    @BindView(R.id.tl_project)
    TabLayout tlProject;
    @BindView(R.id.rv_main)
    ViewPager vpProject;

    private List<ProjectBean> projectBeanList;
    private List<ProjectListFragment> fragmentList;
    public static ProjectFragment newInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        projectBeanList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        mPresenter.getProjectList();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }


    @Override
    protected void initPresenter() {
        mPresenter = new ProjectPresenter();
    }


    @Override
    public void showProjectResult(final List<ProjectBean> projectBeanList) {
        for (ProjectBean project : projectBeanList) {
            ProjectListFragment projectListFragment = ProjectListFragment.newInstance(project.getId(),null);
            fragmentList.add(projectListFragment);
        }
        //1.Fragment嵌套Fragment要用getChildFragmentManager,所得到的是在fragment  里面子容器的管理器
        vpProject.setAdapter(new ProjectFragementAdapter(getChildFragmentManager(),fragmentList,projectBeanList));
        tlProject.setupWithViewPager(vpProject);
        vpProject.setCurrentItem(0);
    }


}
