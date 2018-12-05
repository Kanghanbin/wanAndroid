package com.kanghanbin.wanandroid.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kanghanbin.wanandroid.R;


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
