package kanghb.com.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.v4.text.HtmlCompat;
import android.support.v7.widget.Toolbar;

import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseMvpActivity;
import kanghb.com.wanandroid.contract.ArticleDetailContract;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.presenter.ArticleDetailPresenter;
import kanghb.com.wanandroid.ui.customView.ProgressWebview;
import kanghb.com.wanandroid.util.Constant;

public class ArticleDetailActivity extends BaseMvpActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.article_detail_web_view)
    ProgressWebview articleDetailWebView;
    private ArticleBean articleBean;

    @Override
    protected void initPresenter() {
        mPresenter = new ArticleDetailPresenter();
    }


    @Override
    protected void initEventAndData() {
        articleDetailWebView.loadUrl(articleBean.getLink());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        articleBean = (ArticleBean) getIntent().getSerializableExtra(Constant.ARG_PARAM1);
        if (articleBean != null) {
            setToolBar(toolBar, HtmlCompat.fromHtml(articleBean.getTitle(),HtmlCompat.FROM_HTML_MODE_COMPACT).toString());
        }

    }

    @Override
    public void showAddCollect() {

    }

    @Override
    public void showCancelCollect() {

    }

}
