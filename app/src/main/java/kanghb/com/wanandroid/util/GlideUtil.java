package kanghb.com.wanandroid.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.contract.HomeContract;

/**
 * 创建时间：2018/11/1
 * 编写人：kanghb
 * 功能描述：
 */
public class GlideUtil {
    public static void loadImage(Context context, String url, ImageView view) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.icon_placeholder);
        Glide.with(context).load(url).apply(options).into(view);
    }
}
