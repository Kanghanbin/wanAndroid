package com.kanghanbin.wanandroid.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.ui.fragment.WechatListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import com.kanghanbin.wanandroid.base.BaseListActivity;
import com.kanghanbin.wanandroid.contract.WechatContract;
import com.kanghanbin.wanandroid.model.bean.WxarticleBean;
import com.kanghanbin.wanandroid.presenter.WechatPresenter;
import com.kanghanbin.wanandroid.ui.adapter.WechatFragementAdapter;
import com.kanghanbin.wanandroid.util.IntentUtil;

public class WechatActivity extends BaseListActivity<WechatPresenter> implements WechatContract.View {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tl_wechat)
    TabLayout tlWechat;
    @BindView(R.id.rv_main)
    ViewPager rvMain;

    private List<WechatListFragment> listFragments = new ArrayList<>();
    private List<WxarticleBean> articleBeanList = new ArrayList<>();

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        showLoading();
        mPresenter.geWechatTree();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setToolBar(toolBar,getString(R.string.wechat));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                WxarticleBean bean = articleBeanList.get(tlWechat.getSelectedTabPosition());
                IntentUtil.startWechatSearchActivtiy(mContext, bean);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new WechatPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wechat;
    }

    @Override
    public void showWechatTree(List<WxarticleBean> wxarticleBeanList) {
        closeLoading();
        showSuccess();
        for (WxarticleBean bean: wxarticleBeanList){
            listFragments.add(WechatListFragment.newInstance(bean.getId(),null));
            articleBeanList.add(bean);
        }
        rvMain.setAdapter(new WechatFragementAdapter(getSupportFragmentManager(),listFragments,articleBeanList));
        tlWechat.setupWithViewPager(rvMain);
        tlWechat.setBackgroundColor(ThemeUtils.getThemeColorStateList(mContext,R.color.theme_color_primary_dark).getDefaultColor());
        tlWechat.setSelectedTabIndicatorColor(ThemeUtils.getThemeColorStateList(mContext,R.color.theme_color_primary_trans).getDefaultColor());
    }

}
