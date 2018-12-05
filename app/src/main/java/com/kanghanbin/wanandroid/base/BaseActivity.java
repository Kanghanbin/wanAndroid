package com.kanghanbin.wanandroid.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.kanghanbin.wanandroid.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 创建时间：2018/10/24
 * 编写人：kanghb
 * 功能描述：activity基类(抽象类)
 */
public abstract class BaseActivity extends SupportActivity {
    protected Context mContext;
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        ActivityUtils.getActivityList().add(this);
        initToolBar();
        initEventAndData();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ActivityUtils.getActivityList().remove(this);
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        //给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //使左上角图标是否显示，如果设成false，则没有程序图标
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }
    protected abstract int getLayout();
    protected abstract void onViewCreated();
    protected abstract void initToolBar();
    protected abstract void initEventAndData();
}
