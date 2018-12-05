package com.kanghanbin.wanandroid.ui.activity;

import android.app.Activity;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.kanghanbin.wanandroid.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.kanghanbin.wanandroid.base.BaseListActivity;
import com.kanghanbin.wanandroid.contract.UseContract;
import com.kanghanbin.wanandroid.model.bean.FriendBean;
import com.kanghanbin.wanandroid.presenter.UsePresenter;
import com.kanghanbin.wanandroid.util.ColorUtil;
import com.kanghanbin.wanandroid.util.IntentUtil;


public class UseActivity extends BaseListActivity<UsePresenter> implements UseContract.View {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tabflow)
    TagFlowLayout flowlayout;

    private List<FriendBean> friendBeans = new ArrayList<>();
    private Activity mActivity;

    @Override
    protected void initPresenter() {
        mPresenter = new UsePresenter();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_use;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setToolBar(toolBar, mContext.getString(R.string.useWebsite));
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mActivity = this;
        showLoading();
        mPresenter.getUseFriend();
        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                FriendBean friendBean = friendBeans.get(position);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, view, mContext.getString(R.string.shareView));
                IntentUtil.startArticleDetailActivity(mContext, activityOptionsCompat,
                        friendBean.getId(), friendBean.getName().trim(),
                        friendBean.getLink().trim(), false,false);
                return true;
            }
        });
    }

    @Override
    public void showUseFriend(List<FriendBean> friendBeanList) {
        closeLoading();
        showSuccess();
        friendBeans = friendBeanList;
        flowlayout.setAdapter(new TagAdapter<FriendBean>(friendBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, FriendBean friendBean) {

                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tv,
                        parent, false);
                tv.setText(friendBean.getName());
                tv.setBackgroundColor(ColorUtil.randomTagColor());
                return tv;
            }

        });
    }

}
