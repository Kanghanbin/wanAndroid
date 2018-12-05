package com.kanghanbin.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.text.HtmlCompat;

import com.kanghanbin.wanandroid.ui.fragment.ProjectListFragment;

import java.util.List;

import com.kanghanbin.wanandroid.model.bean.ProjectBean;

/**
 * 创建时间：2018/11/5
 * 编写人：kanghb
 * 功能描述：
 */
public class ProjectFragementAdapter extends FragmentStatePagerAdapter {
    private List<ProjectListFragment> fragmentList;
    private List<ProjectBean> projectBeanList;
    public ProjectFragementAdapter(FragmentManager fm, List<ProjectListFragment> fragmentList, List<ProjectBean> projectList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.projectBeanList = projectList;
    }

    public ProjectFragementAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return projectBeanList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return HtmlCompat.fromHtml(projectBeanList.get(position).getName(),HtmlCompat.FROM_HTML_MODE_COMPACT);
    }
}
