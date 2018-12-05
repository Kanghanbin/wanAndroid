package com.kanghanbin.wanandroid.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.text.HtmlCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kanghanbin.wanandroid.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.OnClick;
import com.kanghanbin.wanandroid.base.BaseMvpActivity;
import com.kanghanbin.wanandroid.contract.ArticleDetailContract;
import com.kanghanbin.wanandroid.presenter.ArticleDetailPresenter;
import com.kanghanbin.wanandroid.ui.customView.ProgressWebview;
import com.kanghanbin.wanandroid.util.Constant;
import com.kanghanbin.wanandroid.util.GlideUtil;

public class ProjectDetailActivity extends BaseMvpActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {

    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_main)
    ProgressWebview viewMain;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;

    private Bundle bundle;
    private int articleId;
    private String articleLink;
    private String articleImgLink;
    private String title;
    private boolean isCollect, isCollectPage, isCollectSupport;



    @Override
    protected void initPresenter() {
        mPresenter = new ArticleDetailPresenter();
    }


    @Override
    protected void initEventAndData() {
        viewMain.loadUrl(articleLink);
        if (articleLink != null && !TextUtils.isEmpty(articleImgLink) && !articleImgLink.equals("")) {
            GlideUtil.loadImage(mContext, articleImgLink, detailBarImage);
        }
        if (isCollectPage) {
            fabLike.show();
        } else {
            fabLike.hide();
        }
        if(isCollect){
            fabLike.setSelected(true);
        }else {
            fabLike.setSelected(false);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_project_detail;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        getBundleData();
        setToolBar(viewToolbar, HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT).toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        isCollectSupport = getIntent().getExtras().getBoolean(Constant.IS_COLLECT_SUPPORT);
        if (isCollectSupport) {
            fabLike.show();
        } else {
            fabLike.hide();
        }
        getMenuInflater().inflate(R.menu.menu_article_nocollect, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                mPresenter.shareEventPermissionVerify(new RxPermissions(this));
                break;
            case R.id.item__browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink)));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 让菜单同时显示图标和文字
     *
     * @param featureId Feature id
     * @param menu      Menu
     * @return menu if opened
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (Constant.MENU_BUILDER.equalsIgnoreCase(menu.getClass().getSimpleName())) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void getBundleData() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString(Constant.ARTICLE_TITLE);
            articleLink = bundle.getString(Constant.ARTICLE_LINK);
            articleImgLink = bundle.getString(Constant.ARTICLE_IMG);
            articleId = bundle.getInt(Constant.ARTICLE_ID);
            isCollect = bundle.getBoolean(Constant.IS_COLLECT);
            isCollectPage = bundle.getBoolean(Constant.IS_COLLECT_PAGE);
            isCollectSupport = bundle.getBoolean(Constant.IS_COLLECT_SUPPORT);
        }

    }

    @Override
    public void showAddCollect() {
        fabLike.setSelected(true);
    }

    @Override
    public void showCancelCollect() {
        fabLike.setSelected(false);
    }

    @OnClick(R.id.fab_like)
    public void onViewClicked() {
        collectEvent();
    }


    @Override
    public void shareEvent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_type_url, getString(R.string.app_name), title, articleLink));
        intent.setType("text/plain");
        startActivity(intent);
    }

    @Override
    public void shareError() {
        showToast(getString(R.string.write_permission_not_allowed));
    }

    private void collectEvent() {
        if (!mPresenter.getLoginStatus()) {
            startLoginActivity();
        } else {
            if (!fabLike.isSelected()) {
                mPresenter.addCollect(articleId);
            } else {
                mPresenter.cancelCollect(articleId);
            }
        }
    }
}
