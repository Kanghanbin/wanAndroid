package kanghb.com.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import kanghb.com.wanandroid.contract.HierarchyContract;
import kanghb.com.wanandroid.model.bean.HierarchyBean;
import kanghb.com.wanandroid.presenter.HierarchyPresenter;
import kanghb.com.wanandroid.ui.activity.HierarchyDetailActivity;
import kanghb.com.wanandroid.ui.adapter.ArticleAdapter;
import kanghb.com.wanandroid.ui.adapter.HierarchyAdapter;
import kanghb.com.wanandroid.util.Constant;

import static kanghb.com.wanandroid.util.Constant.ARG_PARAM1;
import static kanghb.com.wanandroid.util.Constant.ARG_PARAM2;


public class HierarchyFragment extends BaseListFragment<HierarchyPresenter> implements HierarchyContract.View,BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private List<HierarchyBean> hierarchyBeans;
    private HierarchyAdapter adapter;


    public static HierarchyFragment newInstance(String param1, String param2) {
        HierarchyFragment fragment = new HierarchyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        hierarchyBeans = new ArrayList<>();
        adapter = new HierarchyAdapter(R.layout.item_hierarchy, hierarchyBeans);
        adapter.setOnItemClickListener(this);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(adapter);
        setRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy;
    }


    @Override
    protected void initPresenter() {
        mPresenter = new HierarchyPresenter();
    }


    @Override
    public void shoHierarchyTree(List<HierarchyBean> hierarchyBeanList) {
        smartRefreshLayout.finishRefresh();
        hierarchyBeans.clear();
        hierarchyBeans.addAll(hierarchyBeanList);
        adapter.notifyDataSetChanged();
    }

    private void setRefresh() {
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHierarchyTree();
            }
        });

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity,view,getString(R.string.shareView));
        Intent intent = new Intent(mActivity, HierarchyDetailActivity.class);
        intent.putExtra(Constant.ARG_PARAM1,  hierarchyBeans.get(position));
        startActivity(intent,optionsCompat.toBundle());
    }
}
