package kanghb.com.wanandroid.ui.main;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseMvpActivity;
import kanghb.com.wanandroid.contract.MainContract;
import kanghb.com.wanandroid.presenter.MainPresenter;
import kanghb.com.wanandroid.util.BottomNavigationViewHelper;


public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.botton_navigation)
    BottomNavigationView bottonNavigation;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setToolBar(toolBar,getString(R.string.home_pager));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolBar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showSwitchProject() {

    }

    @Override
    public void showSwitchHierarchy() {

    }

    @Override
    public void showSwitchNavigation() {

    }


}
