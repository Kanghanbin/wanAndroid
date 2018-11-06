package kanghb.com.wanandroid.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.model.bean.NavigationBean;

/**
 * 创建时间：2018/11/2
 * 编写人：kanghb
 * 功能描述：
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder> {
    LinearLayout.LayoutParams layoutParams;

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {
        helper.setText(R.id.tv_nav_name, item.getName());
        LinearLayout linearLayout = helper.getView(R.id.ll_nav_content);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.removeAllViews();
        layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();

        for (int i = 0; i < item.getArticles().size(); i++) {
            TextView textView = new TextView(mContext);
            textView.setText(item.getArticles().get(i).getTitle());
            textView.setTextSize(16);
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            layoutParams.setMargins(0, SizeUtils.dp2px(10), 0, 0);
            textView.setLayoutParams(layoutParams);
            linearLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
