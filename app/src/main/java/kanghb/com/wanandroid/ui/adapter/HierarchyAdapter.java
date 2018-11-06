package kanghb.com.wanandroid.ui.adapter;


import android.support.annotation.Nullable;
import android.support.v4.text.HtmlCompat;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.HierarchyBean;
import kanghb.com.wanandroid.util.GlideUtil;

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
