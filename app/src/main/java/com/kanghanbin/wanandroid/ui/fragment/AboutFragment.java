package com.kanghanbin.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.text.HtmlCompat;
import android.widget.TextView;

import butterknife.BindView;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.BaseFragment;

import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM1;
import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM2;

/**
 * 创建时间：2018/11/19
 * 编写人：kanghb
 * 功能描述：
 */
public class AboutFragment extends BaseFragment {
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        tvContent.setText(HtmlCompat.fromHtml(getString(R.string.about_content), HtmlCompat.FROM_HTML_MODE_COMPACT));
    }
}