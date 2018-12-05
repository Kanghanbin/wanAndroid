package com.kanghanbin.wanandroid.http;

import com.kanghanbin.wanandroid.db.SharePreferencesHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 创建时间：2018/11/13
 * 编写人：kanghb
 * 功能描述：
 */
public class SaveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SharePreferencesHelper.getInstance().setCookie(cookies);
        }

        return originalResponse;
    }
}
