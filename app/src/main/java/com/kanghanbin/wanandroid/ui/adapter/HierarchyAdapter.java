package com.kanghanbin.wanandroid.ui.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.model.bean.HierarchyBean;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyAdapter extends BaseQuickAdapter<HierarchyBean, BaseViewHolder> {
    private StringBuilder content;


    public HierarchyAdapter(int layoutResId, @Nullable List<HierarchyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HierarchyBean item) {
        content = new StringBuilder();
        for (HierarchyBean.ChildrenBean children : item.getChildren()) {
            content.append(children.getName() + "  ");
        }
        helper.setText(R.id.tv_title, item.getName())
                .setText(R.id.tv_content, content);
    }
}
