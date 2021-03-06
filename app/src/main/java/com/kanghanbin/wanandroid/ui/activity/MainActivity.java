package com.kanghanbin.wanandroid.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.ui.fragment.AboutFragment;
import com.kanghanbin.wanandroid.ui.fragment.CollectFragment;
import com.kanghanbin.wanandroid.ui.fragment.HierarchyFragment;
import com.kanghanbin.wanandroid.ui.fragment.HomeFragment;
import com.kanghanbin.wanandroid.ui.fragment.NavigationFragment;
import com.kanghanbin.wanandroid.ui.fragment.ProjectFragment;
import com.kanghanbin.wanandroid.ui.fragment.SettingFragment;

import butterknife.BindView;

import com.kanghanbin.wanandroid.base.BaseMvpActivity;
import com.kanghanbin.wanandroid.contract.MainContract;
import com.kanghanbin.wanandroid.presenter.MainPresenter;
import com.kanghanbin.wanandroid.util.Constant;
import com.kanghanbin.wanandroid.util.IntentUtil;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View, MenuItem.OnMenuItemClickListener {

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
    private CollectFragment collectFragment;
    private AboutFragment aboutFragment;
    private SettingFragment settingFragment;

    private int currentFragmentType = Constant.TYPE_HOME;
    private int hideFragmentType = Constant.TYPE_HOME;
    private TextView tvLogin;
    private MenuItem menuItemHome, menuItemWechat, menuItemTodo, menuItemCollect, menuItemSetting, menuItemAbout, menuItemLogout;
    private MenuItem lastMenuItem;
    private MenuItem currentMenuItem;

    private MenuItem menuUse, menuHotsearch;

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
        initNavigation();
        mPresenter.getLoginAccount();
        mPresenter.getLoignPassword();

    }

    private void initFragments() {
        homeFragment = HomeFragment.newInstance(null, null);
        hierarchyFragment = HierarchyFragment.newInstance(null, null);
        projectFragment = ProjectFragment.newInstance(null, null);
        navigationFragment = NavigationFragment.newInstance(null, null);
        collectFragment = CollectFragment.newInstance(null, null);
        aboutFragment = AboutFragment.newInstance(null, null);
        settingFragment = SettingFragment.newInstance(null, null);
        loadMultipleRootFragment(R.id.fl_contain, 0, homeFragment, hierarchyFragment, navigationFragment, projectFragment, collectFragment, aboutFragment, settingFragment);
        bottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_main_pager:
                        setToolBarTitle(Constant.TYPE_HOME);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        setToolBarTitle(Constant.TYPE_HIERARCHY);
                        break;
                    case R.id.tab_navigation:
                        setToolBarTitle(Constant.TYPE_NAVIGATION);
                        break;
                    case R.id.tab_project:
                        setToolBarTitle(Constant.TYPE_PROJECT);
                        break;
                    default:
                        break;
                }
                switchFragment();
                return true;
            }
        });
    }

    /**
     * 设置当前项
     *
     * @param currentType
     */
    private void setToolBarTitle(int currentType) {
        currentFragmentType = currentType;
        switch (currentType) {
            case Constant.TYPE_HOME:
                toolBar.setTitle(getString(R.string.home_pager));
                break;
            case Constant.TYPE_HIERARCHY:
                toolBar.setTitle(getString(R.string.knowledge_hierarchy));
                break;
            case Constant.TYPE_NAVIGATION:
                toolBar.setTitle(getString(R.string.navigation));
                break;
            case Constant.TYPE_PROJECT:
                toolBar.setTitle(getString(R.string.project));
                break;
            case Constant.TYPE_COLLECT:
                toolBar.setTitle(getString(R.string.collect));
                break;
            case Constant.TYPE_ABOUT:
                toolBar.setTitle(getString(R.string.about));
                break;
            case Constant.TYPE_SETTING:
                toolBar.setTitle(getString(R.string.setting));
                break;

        }


    }

    private void switchFragment() {
        drawerlayout.closeDrawers();
        if (currentFragmentType == Constant.TYPE_HOME) {
            if (menuHotsearch != null && menuUse != null) {
                menuHotsearch.setVisible(true);
                menuUse.setVisible(true);
            }

        } else {
            if (menuHotsearch != null && menuUse != null) {
                menuHotsearch.setVisible(false);
                menuUse.setVisible(false);
            }

        }
        if (currentFragmentType >= Constant.TYPE_HOME && currentFragmentType <= Constant.TYPE_PROJECT) {
            bottonNavigation.setVisibility(View.VISIBLE);
        } else {
            bottonNavigation.setVisibility(View.GONE);
        }
        showHideFragment(getTargetFragment(currentFragmentType), getTargetFragment(hideFragmentType));
        hideFragmentType = currentFragmentType;

    }

    private void initNavigation() {
        tvLogin = navigation.getHeaderView(0).findViewById(R.id.tv_login);
        menuItemHome = navigation.getMenu().findItem(R.id.item_home);
        menuItemWechat = navigation.getMenu().findItem(R.id.item_wechat);
        menuItemTodo = navigation.getMenu().findItem(R.id.item_Todo);
        menuItemCollect = navigation.getMenu().findItem(R.id.item_collect);
        menuItemSetting = navigation.getMenu().findItem(R.id.item_setting);
        menuItemAbout = navigation.getMenu().findItem(R.id.item_about);
        menuItemLogout = navigation.getMenu().findItem(R.id.item_logout);
        lastMenuItem = menuItemHome;
        currentMenuItem = menuItemHome;

        menuItemHome.setOnMenuItemClickListener(this);
        menuItemTodo.setOnMenuItemClickListener(this);
        menuItemWechat.setOnMenuItemClickListener(this);
        menuItemCollect.setOnMenuItemClickListener(this);
        menuItemSetting.setOnMenuItemClickListener(this);
        menuItemAbout.setOnMenuItemClickListener(this);
        menuItemLogout.setOnMenuItemClickListener(this);


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
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
            case Constant.TYPE_COLLECT:
                return collectFragment;
            case Constant.TYPE_ABOUT:
                return aboutFragment;
            case Constant.TYPE_SETTING:
                return settingFragment;
        }
        return homeFragment;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setToolBar(toolBar, getString(R.string.home_pager));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolBar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawerlayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        actionBarDrawerToggle.syncState();
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        menuUse = menu.findItem(R.id.item_use);
        menuHotsearch = menu.findItem(R.id.item_hotsearch);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_use:
                IntentUtil.startUseActivity(mContext);
                break;
            case R.id.item_hotsearch:
                IntentUtil.startHotKeySearchActivity(mContext);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                setToolBarTitle(mPresenter.getCurrentItem());
                switchFragment();
                currentMenuItem = menuItemHome;
                break;
            case R.id.item_wechat:
                if (hideFragmentType >= Constant.TYPE_HOME && hideFragmentType <= Constant.TYPE_PROJECT) {
                    mPresenter.setCurrentItem(hideFragmentType);
                }
                IntentUtil.startWechatActivity(mContext);
                currentMenuItem = menuItemWechat;
                break;
            case R.id.item_Todo:
                if (hideFragmentType >= Constant.TYPE_HOME && hideFragmentType <= Constant.TYPE_PROJECT) {
                    mPresenter.setCurrentItem(hideFragmentType);
                }
                if (mPresenter.getLoginStatus()) {
                    IntentUtil.startTodoActivity(mContext);
                } else {
                    startLoginActivity();
                }
                currentMenuItem = menuItemTodo;
                break;
            case R.id.item_collect:
                //切换前保存bottomnavigation，确保下次切回来还是那一项
                if (hideFragmentType >= Constant.TYPE_HOME && hideFragmentType <= Constant.TYPE_PROJECT) {
                    mPresenter.setCurrentItem(hideFragmentType);
                }
                if (mPresenter.getLoginStatus()) {
                    setToolBarTitle(Constant.TYPE_COLLECT);
                    switchFragment();
                } else {
                    startLoginActivity();
                }
                currentMenuItem = menuItemCollect;
                break;
            case R.id.item_setting:
                if (hideFragmentType >= Constant.TYPE_HOME && hideFragmentType <= Constant.TYPE_PROJECT) {
                    mPresenter.setCurrentItem(hideFragmentType);
                }
                setToolBarTitle(Constant.TYPE_SETTING);
                switchFragment();
                currentMenuItem = menuItemSetting;
                break;
            case R.id.item_about:
                if (hideFragmentType >= Constant.TYPE_HOME && hideFragmentType <= Constant.TYPE_PROJECT) {
                    mPresenter.setCurrentItem(hideFragmentType);
                }
                setToolBarTitle(Constant.TYPE_ABOUT);
                switchFragment();
                currentMenuItem = menuItemAbout;
                break;
            case R.id.item_logout:
                showLogoutDialog();
                break;

        }

        if (lastMenuItem != null) {
            lastMenuItem.setChecked(false);
        }
        currentMenuItem.setChecked(true);
        lastMenuItem = currentMenuItem;
        return true;
    }

    private void showLogoutDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setMessage("确定退出当前账号吗？");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.logout();
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void showLoginAccount(String account) {
        if (!account.equals("") && !TextUtils.isEmpty(account) && mPresenter.getLoginStatus()) {
            tvLogin.setText(account);
            tvLogin.setOnClickListener(null);
            menuItemLogout.setVisible(true);
        } else {
            tvLogin.setText(R.string.goto_login);
            tvLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
            });
            menuItemLogout.setVisible(false);
        }

    }


    @Override
    public void showLogout() {
        menuItemLogout.setVisible(false);
        startLoginActivity();
    }

    @Override
    public void restoreBeforeNight() {
        //切换夜间模式成功后直接去设置界面
        setToolBarTitle(Constant.TYPE_SETTING);
        switchFragment();
        currentMenuItem = menuItemSetting;
        if (lastMenuItem != null) {
            lastMenuItem.setChecked(false);
        }
        currentMenuItem.setChecked(true);
        lastMenuItem = currentMenuItem;

    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

}
