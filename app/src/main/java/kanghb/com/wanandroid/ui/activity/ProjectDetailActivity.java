package kanghb.com.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.text.HtmlCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseMvpActivity;
import kanghb.com.wanandroid.base.BaseMvpFragment;
import kanghb.com.wanandroid.contract.ArticleDetailContract;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.presenter.ArticleDetailPresenter;
import kanghb.com.wanandroid.ui.customView.ProgressWebview;
import kanghb.com.wanandroid.util.Constant;
import kanghb.com.wanandroid.util.GlideUtil;

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

    private ArticleBean articleBean;

    @Override
    protected void initPresenter() {
        mPresenter = new ArticleDetailPresenter();
    }


    @Override
    protected void initEventAndData() {
        viewMain.loadUrl(articleBean.getLink());
        if (!TextUtils.isEmpty(articleBean.getEnvelopePic()) || !articleBean.getEnvelopePic().equals("")) {
            GlideUtil.loadImage(mContext, articleBean.getEnvelopePic(), detailBarImage);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_project_detail;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        articleBean = (ArticleBean) getIntent().getSerializableExtra(Constant.ARG_PARAM1);
        if (articleBean != null) {
            setToolBar(viewToolbar, HtmlCompat.fromHtml(articleBean.getTitle(),HtmlCompat.FROM_HTML_MODE_COMPACT).toString() );
        }

    }

    @Override
    public void showAddCollect() {

    }

    @Override
    public void showCancelCollect() {

    }

}
