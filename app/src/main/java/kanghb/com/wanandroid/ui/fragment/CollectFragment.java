package kanghb.com.wanandroid.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SnackbarUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListFragment;
import kanghb.com.wanandroid.contract.CollectContract;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.presenter.CollectPresenter;
import kanghb.com.wanandroid.ui.adapter.ArticleAdapter;
import kanghb.com.wanandroid.util.IntentUtil;

import static kanghb.com.wanandroid.util.Constant.ARG_PARAM1;
import static kanghb.com.wanandroid.util.Constant.ARG_PARAM2;


public class CollectFragment extends BaseListFragment<CollectPresenter> implements CollectContract.View, BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private ArticleAdapter adapter;
    private List<ArticleBean> articleBeanList;

    public static CollectFragment newInstance(String param1, String param2) {
        CollectFragment fragment = new CollectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        articleBeanList = new ArrayList<>();
        adapter = new ArticleAdapter(R.layout.item_article, articleBeanList);
        adapter.setCollectPage(true);
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
                mPresenter.getCollectListArticles();
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getMoreCollectListArticles();
                smartRefreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }


    @Override
    protected void initPresenter() {
        mPresenter = new CollectPresenter();
    }


    @Override
    public void showCollectListArticles(ArticleListBean articleListBean) {
        articleBeanList = articleListBean.getDatas();
        adapter.replaceData(articleBeanList);
    }

    @Override
    public void showMoreCollectListArticles(ArticleListBean articleListBean) {
        if (articleListBean == null || articleListBean.getDatas() == null || articleListBean.getDatas().size() == 0) {

        } else {
            articleBeanList.addAll(articleListBean.getDatas());
            adapter.addData(articleBeanList);
        }
    }

    @Override
    public void showUnCollectFromCollectPage(int pos, ArticleBean articleBean) {
        articleBeanList.remove(pos);
        adapter.remove(pos);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter.getData().size() <= 0 || adapter.getData().size() <= position) {
            return;
        }
        ArticleBean articleBean = (ArticleBean) adapter.getData().get(position);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.shareView));
        IntentUtil.startArticleDetailActivity(mContext,options,articleBean.getId(),articleBean.getTitle(),articleBean.getLink(),true,true);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.iv_article_collect:
                if (adapter.getData().size() <= 0 || adapter.getData().size() <= position) {
                    return;
                }
                ArticleBean articleBean = (ArticleBean) adapter.getData().get(position);
                mPresenter.unCollectFromCollectPage(position,articleBean);
                break;
        }

    }
}
