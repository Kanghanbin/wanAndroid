package kanghb.com.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListFragment;
import kanghb.com.wanandroid.contract.HomeContract;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.BannerBean;
import kanghb.com.wanandroid.presenter.HomePresenter;
import kanghb.com.wanandroid.ui.adapter.ArticleAdapter;
import kanghb.com.wanandroid.util.GlideImageLoader;

import static kanghb.com.wanandroid.util.Constant.ARG_PARAM1;
import static kanghb.com.wanandroid.util.Constant.ARG_PARAM2;


public class HomeFragment extends BaseListFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private Banner banner;
    private String mParam1;
    private String mParam2;
    private ArticleAdapter adapter;
    private List<ArticleBean> articleBeanList;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        LinearLayout llBanner = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_header_banner, null);
        banner = llBanner.findViewById(R.id.banner);
        adapter = new ArticleAdapter(R.layout.item_article, articleBeanList);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        //banner 跟随rvmain一起滑动，所以不放在xml里面
        adapter.addHeaderView(llBanner);
        rvMain.setAdapter(adapter);
        setRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter();
    }


    @Override
    public void showArticleList(ArticleListBean articleListBean) {
        smartRefreshLayout.finishRefresh();
        articleBeanList.clear();
        //这里之前是这么写的 articleBeanList = articleListBean.getDatas());数据始终不显示..记录下
        articleBeanList.addAll(articleListBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showArticleListMore(ArticleListBean articleListBean) {
        smartRefreshLayout.finishLoadMore();
        articleBeanList.addAll(articleListBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }

    @Override
    public void showBanner(List<BannerBean> bannerBeanList) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerBean bannerData : bannerBeanList) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(bannerImageList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(bannerBeanList.size() * 400);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void setRefresh() {
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.autoRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.loadMore();
            }
        });
    }


}
