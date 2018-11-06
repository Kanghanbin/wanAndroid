package kanghb.com.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListFragment;
import kanghb.com.wanandroid.contract.ProjectContract;
import kanghb.com.wanandroid.model.bean.ProjectBean;
import kanghb.com.wanandroid.presenter.ProjectPresenter;
import kanghb.com.wanandroid.ui.adapter.ProjectFragementAdapter;

import static kanghb.com.wanandroid.util.Constant.ARG_PARAM1;
import static kanghb.com.wanandroid.util.Constant.ARG_PARAM2;


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
