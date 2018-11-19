package kanghb.com.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListActivity;
import kanghb.com.wanandroid.contract.WechatSearchContract;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.WxarticleBean;
import kanghb.com.wanandroid.presenter.WechatSearchPresenter;
import kanghb.com.wanandroid.ui.adapter.WechatListAdapter;
import kanghb.com.wanandroid.util.Constant;
import kanghb.com.wanandroid.util.IntentUtil;

public class WechatSearchActivity extends BaseListActivity<WechatSearchPresenter> implements WechatSearchContract.View, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.search_back_ib)
    ImageButton searchBackIb;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;

    private WxarticleBean wxarticleBean;
    private int id;
    private List<ArticleBean> articleBeanList;
    private WechatListAdapter adapter;
    private int currentPage = 1;
    private String key;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        articleBeanList = new ArrayList<>();
        adapter = new WechatListAdapter(R.layout.item_wechat_list, articleBeanList);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(adapter);
        setAuto();

    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            wxarticleBean = (WxarticleBean) bundle.getSerializable(Constant.WXARTICLEBEAN);
            id = wxarticleBean.getId();
            searchEdit.setHint("在公众号" + wxarticleBean.getName() + "中搜索");
        }

    }

    private void setAuto() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPage = 1;
                mPresenter.getSearchListtWechat(id, currentPage, key);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPage++;
                mPresenter.getMoreSearchListtWechat(id, currentPage, key);
            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter = new WechatSearchPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wechat_search;
    }

    @Override
    public void showSearchListWechat(ArticleListBean articleListBean) {
        smartRefreshLayout.finishRefresh();
        articleBeanList.clear();
        adapter.replaceData(articleListBean.getDatas());
    }

    @Override
    public void showMoreSearchListWechat(ArticleListBean articleListBean) {
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.shareView));
        ArticleBean articleBean = articleBeanList.get(position);
        IntentUtil.startArticleDetailActivity(mContext, activityOptionsCompat,
                articleBean.getId(), articleBean.getTitle(), articleBean.getLink(), articleBean.isCollect(), false);
    }


    @OnClick({R.id.search_back_ib, R.id.search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back_ib:
                KeyboardUtils.hideSoftInput(searchEdit);
                onBackPressed();
                break;
            case R.id.search_tv:
                KeyboardUtils.hideSoftInput(searchEdit);
                key = searchEdit.getText().toString().trim();
                currentPage = 1;
                mPresenter.getSearchListtWechat(id, currentPage, key);
                break;
        }
    }
}
