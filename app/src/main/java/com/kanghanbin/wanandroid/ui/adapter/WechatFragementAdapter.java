package com.kanghanbin.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.text.HtmlCompat;

import com.kanghanbin.wanandroid.ui.fragment.WechatListFragment;

import java.util.List;

import com.kanghanbin.wanandroid.model.bean.WxarticleBean;

/**
 * 创建时间：2018/11/5
 * 编写人：kanghb
 * 功能描述：
 */
public class WechatFragementAdapter extends FragmentStatePagerAdapter {
    private List<WechatListFragment> fragmentList;
    private List<WxarticleBean> childrenBeans;
    public WechatFragementAdapter(FragmentManager fm, List<WechatListFragment> fragmentList, List<WxarticleBean> childrenBeanList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.childrenBeans = childrenBeanList;
    }

    public WechatFragementAdapter(FragmentManager fm) {
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
