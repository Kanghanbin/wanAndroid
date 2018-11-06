package kanghb.com.wanandroid.ui.adapter;


import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.HtmlCompat;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.util.GlideUtil;

/**
 * 创建时间：2018/10/31
 * 编写人：kanghb
 * 功能描述：
 */
public class ProjectListAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.tv_article_title, HtmlCompat.fromHtml(item.getTitle(), HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setText(R.id.tv_article_date_author, HtmlCompat.fromHtml(item.getNiceDate() + "<font color = '#FFA500'><big>" +  "  By  " + item.getAuthor() + "</big></font>" , HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setTextColor(R.id.tv_article_date_author, ContextCompat.getColor(mContext, R.color.orangered))
                .setText(R.id.tv_article_content, HtmlCompat.fromHtml(item.getDesc(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        GlideUtil.loadImage(mContext, item.getEnvelopePic(), (ImageView) helper.getView(R.id.iv_article_img));

    }
}
