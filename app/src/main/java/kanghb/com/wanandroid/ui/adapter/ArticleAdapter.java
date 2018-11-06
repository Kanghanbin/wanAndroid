package kanghb.com.wanandroid.ui.adapter;


import android.support.annotation.Nullable;

import android.support.v4.text.HtmlCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
public class ArticleAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    public ArticleAdapter(int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.tv_article_title, HtmlCompat.fromHtml(item.getTitle(), HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setText(R.id.tv_article_author, item.getAuthor())
                .setText(R.id.tv_article_date, item.getNiceDate())
                .setText(R.id.tv_article_superChapter, HtmlCompat.fromHtml(item.getSuperChapterName(), HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setText(R.id.tv_article_chapter, HtmlCompat.fromHtml(item.getChapterName(), HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setVisible(R.id.tv_article_fresh, item.isFresh() ? true : false);
        GlideUtil.loadImage(mContext, item.getEnvelopePic(), (ImageView) helper.getView(R.id.iv_article_img));

    }
}
