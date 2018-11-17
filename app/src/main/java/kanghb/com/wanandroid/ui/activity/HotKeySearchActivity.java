package kanghb.com.wanandroid.ui.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListActivity;
import kanghb.com.wanandroid.contract.HotKeyContract;
import kanghb.com.wanandroid.model.bean.HotKey;
import kanghb.com.wanandroid.model.local.LocalHotKey;
import kanghb.com.wanandroid.presenter.HotKeyPresenter;
import kanghb.com.wanandroid.ui.adapter.HotKeySearchAdapter;
import kanghb.com.wanandroid.util.ColorUtil;
import kanghb.com.wanandroid.util.IntentUtil;

public class HotKeySearchActivity extends BaseListActivity<HotKeyPresenter> implements HotKeyContract.View {


    @BindView(R.id.search_back_ib)
    ImageButton searchBackIb;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R.id.top_search_flow_layout)
    TagFlowLayout topSearchFlowLayout;
    @BindView(R.id.search_history_clear_all_tv)
    TextView searchHistoryClearAllTv;
    @BindView(R.id.search_history_null_tint_tv)
    TextView searchHistoryNullTintTv;
    @BindView(R.id.search_history_rv)
    RecyclerView searchHistoryRv;
    @BindView(R.id.rv_main)
    NestedScrollView rvMain;


    private List<LocalHotKey> localHotKeys = new ArrayList<>();
    private List<HotKey> hotKeys = new ArrayList<>();
    private HotKeySearchAdapter adapter;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initRecycleview();
        mPresenter.getHotKeys();
        mPresenter.getLoaclHotKeys();
    }

    private void initRecycleview() {
        adapter = new HotKeySearchAdapter(R.layout.item_search_history, localHotKeys);
        searchHistoryRv.setLayoutManager(new LinearLayoutManager(mContext));
        searchHistoryRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.addLocalHotKey(localHotKeys.get(position).getData().trim());
                searchEdit.setText(localHotKeys.get(position).getData().trim());
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_hot_key_search;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HotKeyPresenter();
    }

    @Override
    public void showHotKeyResults(final List<HotKey> hotKeyList) {
        topSearchFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                HotKey hotKey = hotKeyList.get(position);
                mPresenter.addLocalHotKey(hotKey.getName().trim());
                searchEdit.setText(hotKey.getName().trim());
                return true;
            }
        });

        hotKeys = hotKeyList;
        topSearchFlowLayout.setAdapter(new TagAdapter<HotKey>(hotKeys) {
            @Override
            public View getView(FlowLayout parent, int position, HotKey hotKey) {

                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tv,
                        parent, false);
                tv.setText(hotKey.getName());
                tv.setBackgroundColor(ColorUtil.randomTagColor());
                return tv;
            }

        });
    }

    @Override
    public void showLocalHotKeys(List<LocalHotKey> localHotKeyList) {
        if (localHotKeyList == null || localHotKeyList.size() <= 0) {
            setVisibilityState(false);
        } else {
            Collections.reverse(localHotKeyList);
            adapter.replaceData(localHotKeyList);
            setVisibilityState(true);
        }
    }

    private void setVisibilityState(boolean b) {
        if (b) {
            searchHistoryClearAllTv.setVisibility(View.VISIBLE);
            searchHistoryNullTintTv.setVisibility(View.GONE);
        } else {
            searchHistoryClearAllTv.setVisibility(View.GONE);
            searchHistoryNullTintTv.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void jumpToSearchListActivity() {
        mPresenter.getLoaclHotKeys();
        KeyboardUtils.hideSoftInput(searchEdit);
        IntentUtil.startSearchListActivity(mContext, searchEdit.getText().toString().trim());
    }

    @Override
    public void showClearResult() {
        adapter.replaceData(new ArrayList<LocalHotKey>());
        setVisibilityState(false);
    }


    @OnClick({R.id.search_back_ib, R.id.search_history_clear_all_tv, R.id.search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back_ib:
                KeyboardUtils.hideSoftInput(searchEdit);
                onBackPressed();
                break;
            case R.id.search_history_clear_all_tv:
                mPresenter.clearLocalHotKey();
                break;
            case R.id.search_tv:
                mPresenter.addLocalHotKey(searchEdit.getText().toString().trim());
                break;
        }
    }
}
