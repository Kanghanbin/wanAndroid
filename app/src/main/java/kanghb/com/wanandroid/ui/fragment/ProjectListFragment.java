package kanghb.com.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.IntentUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListFragment;
import kanghb.com.wanandroid.contract.ProjectContract;
import kanghb.com.wanandroid.contract.ProjectListContract;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.ProjectBean;
import kanghb.com.wanandroid.presenter.ProjectListPresenter;
import kanghb.com.wanandroid.ui.activity.ArticleDetailActivity;
import kanghb.com.wanandroid.ui.activity.ProjectDetailActivity;
import kanghb.com.wanandroid.ui.adapter.ProjectListAdapter;
import kanghb.com.wanandroid.util.IntentUtil;

import static kanghb.com.wanandroid.util.Constant.ARG_PARAM1;
import static kanghb.com.wanandroid.util.Constant.ARG_PARAM2;

/**
 * 创建时间：2018/11/5
 * 编写人：kanghb
 * 功能描述：
 */
public class ProjectListFragment extends BaseListFragment<ProjectListPresenter> implements ProjectListContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private int cid;
    private List<ArticleBean> articleBeanList;
    private ProjectListAdapter adapter;
    private int currentPage = 1;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Bundle bundle = getArguments();
        cid = bundle.getInt(ARG_PARAM1);
        articleBeanList = new ArrayList<>();
        adapter = new ProjectListAdapter(R.layout.item_project, articleBeanList);
        adapter.setOnItemClickListener(this);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(adapter);
        setAuto();

    }


    public static ProjectListFragment newInstance(int param1, String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ProjectListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_project;
    }


    private void setAuto() {
        smartRefreshLayout.autoRefresh();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPage = 1;
                mPresenter.getRefreshProjectArticleList(1, cid);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPage++;
                mPresenter.getMoreProjectArticleList(currentPage, cid);
            }
        });
    }

    @Override
    public void showRefreshProjectArticleResult(ArticleListBean articleListBean) {
        smartRefreshLayout.finishRefresh();
        articleBeanList.clear();
        articleBeanList.addAll(articleListBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreProjectArticleResult(ArticleListBean articleListBean) {
        if (articleListBean.getDatas().size() == 0) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
            currentPage--;
        }else {
            smartRefreshLayout.finishLoadMore();
        }
        articleBeanList.addAll(articleListBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleBean articleBean = articleBeanList.get(position);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity,view,getString(R.string.shareView));
        IntentUtil.startProjectDetailActivity(mContext,activityOptionsCompat,articleBean.getId(),articleBean.getTitle(),articleBean.getLink(),articleBean.getEnvelopePic(),articleBean.isCollect(),false,true);

    }
}
