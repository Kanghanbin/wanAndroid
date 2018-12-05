package com.kanghanbin.wanandroid.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.text.HtmlCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kanghanbin.wanandroid.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Method;

import butterknife.BindView;
import com.kanghanbin.wanandroid.base.BaseMvpActivity;
import com.kanghanbin.wanandroid.contract.ArticleDetailContract;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.presenter.ArticleDetailPresenter;
import com.kanghanbin.wanandroid.ui.customView.ProgressWebview;
import com.kanghanbin.wanandroid.util.Constant;

public class ArticleDetailActivity extends BaseMvpActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.article_detail_web_view)
    ProgressWebview articleDetailWebView;


    private Bundle bundle;
    private int articleId;
    private String articleLink;
    private String title;
    private boolean isCollect;
    private boolean isCollectPage;
    private ArticleBean articleBean;
    private MenuItem collectMenuItem;

    @Override
    protected void initPresenter() {
        mPresenter = new ArticleDetailPresenter();
    }


    @Override
    protected void initEventAndData() {
        if (articleBean != null) {
            articleDetailWebView.loadUrl(articleBean.getLink());
        } else if (articleLink != null) {
            articleDetailWebView.loadUrl(articleLink);
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        getBundleData();
        if (title != null) {
            setToolBar(toolBar, HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT).toString());
        }
        if (articleBean != null) {
            setToolBar(toolBar, HtmlCompat.fromHtml(articleBean.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acticle, menu);
        collectMenuItem = menu.findItem(R.id.item_collect);
        isCollect = getIntent().getExtras().getBoolean(Constant.IS_COLLECT);
        if (isCollect) {
            collectMenuItem.setIcon(R.mipmap.ic_toolbar_like_p);
            collectMenuItem.setTitle(R.string.uncollect);
        } else {
            collectMenuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            collectMenuItem.setTitle(R.string.addcollect);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                mPresenter.shareEventPermissionVerify(new RxPermissions(this));
                break;
            case R.id.item_collect:
                collectEvent();
                break;
            case R.id.item__browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink)));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
            if (collectMenuItem.getTitle().equals(getString(R.string.uncollect))) {
                if (isCollectPage) {
                    mPresenter.cancelCollectFromCollect(articleId);
                } else {
                    mPresenter.cancelCollect(articleId);
                }
            } else {
                mPresenter.addCollect(articleId);

            }
        }

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
            articleBean = (ArticleBean) bundle.getSerializable(Constant.ARG_PARAM1);
            title = bundle.getString(Constant.ARTICLE_TITLE);
            articleLink = bundle.getString(Constant.ARTICLE_LINK);
            articleId = bundle.getInt(Constant.ARTICLE_ID);
            isCollect = bundle.getBoolean(Constant.IS_COLLECT);
            isCollectPage = bundle.getBoolean(Constant.IS_COLLECT_PAGE);
        }

    }

    @Override
    public void showAddCollect() {
        collectMenuItem.setIcon(R.mipmap.ic_toolbar_like_p);
        collectMenuItem.setTitle(R.string.uncollect);
    }

    @Override
    public void showCancelCollect() {
        collectMenuItem.setIcon(R.mipmap.ic_toolbar_like_n);
        collectMenuItem.setTitle(R.string.addcollect);
    }

}
