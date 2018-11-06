package kanghb.com.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListFragment;
import kanghb.com.wanandroid.contract.NavigationContract;
import kanghb.com.wanandroid.model.bean.NavigationBean;
import kanghb.com.wanandroid.presenter.NavigationPresenter;
import kanghb.com.wanandroid.ui.adapter.NavigationAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

import static kanghb.com.wanandroid.util.Constant.ARG_PARAM1;
import static kanghb.com.wanandroid.util.Constant.ARG_PARAM2;


public class NavigationFragment extends BaseListFragment<NavigationPresenter> implements NavigationContract.View {


    @BindView(R.id.vtl_nav)
    VerticalTabLayout vtlNav;
    @BindView(R.id.rv_main)
    RecyclerView rvNav;

    private boolean needScroll;
    private int postion;
    private NavigationAdapter adapter;
    private List<NavigationBean> list;
    private LinearLayoutManager linearLayoutManager;
    private boolean checked;

    public static NavigationFragment newInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        list = new ArrayList<>();
        adapter = new NavigationAdapter(R.layout.item_navigation, list);
        linearLayoutManager = new LinearLayoutManager(mContext);
        rvNav.setLayoutManager(linearLayoutManager);
        rvNav.setAdapter(adapter);
        mPresenter.getNavigation();
        vtlNav.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                smoothScrollRecycleviewtoPositionTop(position);

            }

            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });
        rvNav.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll == true && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecycleview();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (checked) {
                        checked = false;
                        return;
                    }
                    int index = linearLayoutManager.findFirstVisibleItemPosition();
                    if (postion != index) {
                        vtlNav.setTabSelected(index);
                        postion = index;

                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void scrollRecycleview() {
        needScroll = false;
        int diff = postion - linearLayoutManager.findFirstVisibleItemPosition();
        if (diff >= 0 && diff < rvNav.getChildCount()) {
            rvNav.smoothScrollBy(0, rvNav.getChildAt(diff).getTop());
        }
    }

    private void smoothScrollRecycleviewtoPositionTop(int position) {
        checked = true;
        postion = position;
        int firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (position < firstItemPosition) {
            rvNav.smoothScrollToPosition(position);
        } else if (position <= lastItemPosition) {
            rvNav.smoothScrollBy(0, rvNav.getChildAt(position - firstItemPosition).getTop());
        } else {
            rvNav.smoothScrollToPosition(position);
            needScroll = true;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }


    @Override
    protected void initPresenter() {
        mPresenter = new NavigationPresenter();
    }


    @Override
    public void showNavigationList(final List<NavigationBean> navigationBeanList) {
        vtlNav.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationBeanList == null ? 0 : navigationBeanList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(navigationBeanList.get(position).getName())
                        .setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary), ContextCompat.getColor(mContext, R.color.gray))
                        .setTextSize(16)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        list.clear();
        list.addAll(navigationBeanList);
        adapter.notifyDataSetChanged();
    }

}
