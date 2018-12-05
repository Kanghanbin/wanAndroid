package com.kanghanbin.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.kanghanbin.wanandroid.base.BaseListFragment;
import com.kanghanbin.wanandroid.contract.WechatListContract;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;
import com.kanghanbin.wanandroid.presenter.WechatListPresenter;
import com.kanghanbin.wanandroid.ui.adapter.WechatListAdapter;
import com.kanghanbin.wanandroid.util.IntentUtil;

import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM1;
import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM2;

/**
 * 创建时间：2018/11/5
 * 编写人：kanghb
 * 功能描述：
 */
public class WechatListFragment extends BaseListFragment<WechatListPresenter> implements WechatListContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private int id;
    private List<ArticleBean> articleBeanList;
    private WechatListAdapter adapter;
    private int currentPage = 1;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Bundle bundle = getArguments();
        id = bundle.getInt(ARG_PARAM1);
        articleBeanList = new ArrayList<>();
        adapter = new WechatListAdapter(R.layout.item_wechat_list, articleBeanList);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(adapter);
        setAuto();

    }


    public static WechatListFragment newInstance(int param1, String param2) {
        WechatListFragment fragment = new WechatListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new WechatListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_wechat;
    }


    private void setAuto() {
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPage = 1;
                mPresenter.getRefreshWechatArticleList(id, currentPage);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPage++;
                mPresenter.getMoreWechatArticleList(id, currentPage);
            }
        });

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, view, getString(R.string.shareView));
        ArticleBean articleBean = articleBeanList.get(position);
        IntentUtil.startArticleDetailActivity(mContext, activityOptionsCompat,
                articleBean.getId(), articleBean.getTitle(), articleBean.getLink(), articleBean.isCollect(), false);
    }

    @Override
    public void showRefreshWechatArticleList(ArticleListBean articleListBean) {
        smartRefreshLayout.finishRefresh();
        articleBeanList.clear();
        adapter.replaceData(articleListBean.getDatas());
    }

    @Override
    public void showMoreWechatArticleList(ArticleListBean articleListBean) {
        if (articleListBean.getDatas().size() == 0) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
            currentPage--;
        } else {
            smartRefreshLayout.finishLoadMore();
            adapter.addData(articleListBean.getDatas());
        }

    }

    @Override
    public void showAddCollectArticle(int position, ArticleBean articleBean) {
        adapter.setData(position, articleBean);
    }

    @Override
    public void showCancelCollectArticle(int position, ArticleBean articleBean) {
        adapter.setData(position, articleBean);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_wechat_collect:
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
