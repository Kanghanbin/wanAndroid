package kanghb.com.wanandroid.ui.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseMvpActivity;
import kanghb.com.wanandroid.contract.MainContract;
import kanghb.com.wanandroid.presenter.MainPresenter;
import kanghb.com.wanandroid.ui.fragment.HierarchyFragment;
import kanghb.com.wanandroid.ui.fragment.HomeFragment;
import kanghb.com.wanandroid.ui.fragment.NavigationFragment;
import kanghb.com.wanandroid.ui.fragment.ProjectFragment;
import kanghb.com.wanandroid.util.Constant;
import me.yokeyword.fragmentation.SupportFragment;


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
    private HomeFragment homeFragment;
    private HierarchyFragment hierarchyFragment;
    private ProjectFragment projectFragment;
    private NavigationFragment navigationFragment;
    private int currentFragmentType = Constant.TYPE_HOME;
    private int hideFragmentType= Constant.TYPE_HOME;

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();

    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initFragments();

    }

    private void initFragments() {
        homeFragment = HomeFragment.newInstance(null,null);
        hierarchyFragment = HierarchyFragment.newInstance(null,null);
        projectFragment = ProjectFragment.newInstance(null,null);
        navigationFragment = NavigationFragment.newInstance(null,null);
        loadMultipleRootFragment(R.id.fl_contain,0,homeFragment,hierarchyFragment,navigationFragment,projectFragment);
        bottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tab_main_pager:
                        toolBar.setTitle(getString(R.string.home_pager));
                        currentFragmentType = Constant.TYPE_HOME;
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        toolBar.setTitle(getString(R.string.knowledge_hierarchy));
                        currentFragmentType = Constant.TYPE_HIERARCHY;
                        break;
                    case R.id.tab_navigation:
                        toolBar.setTitle(getString(R.string.navigation));
                        currentFragmentType = Constant.TYPE_NAVIGATION;
                        break;
                    case R.id.tab_project:
                        toolBar.setTitle(getString(R.string.project));
                        currentFragmentType = Constant.TYPE_PROJECT;
                        break;
                }
                showHideFragment(getTargetFragment(currentFragmentType),getTargetFragment(hideFragmentType));
                hideFragmentType = currentFragmentType;
                return true;
            }
        });
    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constant.TYPE_HOME:
                return homeFragment;
            case Constant.TYPE_HIERARCHY:
                return hierarchyFragment;
            case Constant.TYPE_PROJECT:
                return projectFragment;
            case Constant.TYPE_NAVIGATION:
                return navigationFragment;
        }
        return homeFragment;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_use:
                break;
            case R.id.item_hotsearch:
                break;
        }
        return super.onOptionsItemSelected(item);
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
