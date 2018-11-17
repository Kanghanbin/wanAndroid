package kanghb.com.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListActivity;
import kanghb.com.wanandroid.contract.SearchListContract;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.presenter.SearchListPresenter;
import kanghb.com.wanandroid.ui.adapter.ArticleAdapter;
import kanghb.com.wanandroid.util.Constant;
import kanghb.com.wanandroid.util.IntentUtil;

public class SearchListActivity extends BaseListActivity<SearchListPresenter> implements SearchListContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private ArticleAdapter adapter;
    private List<ArticleBean> articleBeanList;
    private String key;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        articleBeanList = new ArrayList<>();
        adapter = new ArticleAdapter(R.layout.item_article, articleBeanList);
        adapter.setOnItemClickListener(this);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(adapter);
        setRefresh();
    }

    private void setRefresh() {
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getSearchListArticles(key);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getMoreSearchListArticles(key);
            }
        });
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        key = getIntent().getStringExtra(Constant.ARG_PARAM1);
        setToolBar(toolBar, key);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search_list;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new SearchListPresenter();
    }

    @Override
    public void showSearchListArticles(ArticleListBean articleListBean) {
        smartRefreshLayout.finishRefresh();
        articleBeanList = articleListBean.getDatas();
        adapter.replaceData(articleBeanList);
    }

    @Override
    public void showMoreSearchListArticles(ArticleListBean articleListBean) {
        if (articleListBean == null || articleListBean.getDatas() == null || articleListBean.getDatas().size() == 0) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            smartRefreshLayout.finishLoadMore();
            articleBeanList.addAll(articleListBean.getDatas());
            adapter.replaceData(articleBeanList);
        }

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.shareView));
        ArticleBean articleBean = articleBeanList.get(position);
        IntentUtil.startArticleDetailActivity(mContext, activityOptionsCompat,
                articleBean.getId(), articleBean.getTitle(), articleBean.getLink(), articleBean.isCollect(),false);
    }

}
