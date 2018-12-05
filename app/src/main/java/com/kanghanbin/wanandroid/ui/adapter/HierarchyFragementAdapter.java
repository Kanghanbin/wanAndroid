package com.kanghanbin.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.text.HtmlCompat;

import com.kanghanbin.wanandroid.ui.fragment.HierarchyListFragment;

import java.util.List;

import com.kanghanbin.wanandroid.model.bean.HierarchyBean;

/**
 * 创建时间：2018/11/5
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyFragementAdapter extends FragmentStatePagerAdapter {
    private List<HierarchyListFragment> fragmentList;
    private List<HierarchyBean.ChildrenBean> childrenBeans;
    public HierarchyFragementAdapter(FragmentManager fm, List<HierarchyListFragment> fragmentList, List<HierarchyBean.ChildrenBean> childrenBeanList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.childrenBeans = childrenBeanList;
    }

    public HierarchyFragementAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return childrenBeans.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return HtmlCompat.fromHtml(childrenBeans.get(position).getName(),HtmlCompat.FROM_HTML_MODE_COMPACT);
    }
}
