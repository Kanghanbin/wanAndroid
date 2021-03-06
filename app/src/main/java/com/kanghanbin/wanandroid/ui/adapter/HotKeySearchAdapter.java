package com.kanghanbin.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.model.local.LocalHotKey;
import com.kanghanbin.wanandroid.util.ColorUtil;

/**
 * @author quchao
 * @date 2018/3/23
 */

public class HotKeySearchAdapter extends BaseQuickAdapter<LocalHotKey, BaseViewHolder> {


    public HotKeySearchAdapter(int layoutResId, @Nullable List<LocalHotKey> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalHotKey item) {
        helper.setText(R.id.item_search_history_tv,item.getData());
        helper.setTextColor(R.id.item_search_history_tv, ColorUtil.randomTagColor());
    }

}
