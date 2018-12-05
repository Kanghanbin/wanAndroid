package com.kanghanbin.wanandroid.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kanghanbin.wanandroid.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.kanghanbin.wanandroid.base.BaseListActivity;
import com.kanghanbin.wanandroid.contract.SearchListContract;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;
import com.kanghanbin.wanandroid.presenter.SearchListPresenter;
import com.kanghanbin.wanandroid.ui.adapter.ArticleAdapter;
import com.kanghanbin.wanandroid.util.Constant;
import com.kanghanbin.wanandroid.util.IntentUtil;

public class SearchListActivity extends BaseListActivity<SearchListPresenter> implements SearchListContract.View, BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemChildClickListener {

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
        adapter.setOnItemChildClickListener(this);
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
    public void showAddCollectArticle(int position, ArticleBean articleBean) {
        adapter.setData(position,articleBean);
    }

    @Override
    public void showCancelCollectArticle(int position, ArticleBean articleBean) {
        adapter.setData(position,articleBean);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.shareView));
        ArticleBean articleBean = articleBeanList.get(position);
        IntentUtil.startArticleDetailActivity(mContext, activityOptionsCompat,
                articleBean.getId(), articleBean.getTitle(), articleBean.getLink(), articleBean.isCollect(),false);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_article_collect:
                if (mPresenter.getLoginStatus()) {
                    if (adapter.getData().size() <= 0 || position >= adapter.getData().size()) {
                        return;
                    }
                    ArticleBean articleBean = articleBeanList.get(position);
                    if (articleBean.isCollect()) {
                        mPresenter.cancelCollectArticle(position, articleBean);
                    } else {
                        mPresenter.addCollectArticle(position, articleBean);
                    }

                } else {
                    startLoginActivity();
                }
                break;
        }

    }
}
