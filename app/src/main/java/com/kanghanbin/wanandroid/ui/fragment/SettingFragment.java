package com.kanghanbin.wanandroid.ui.fragment;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.widgets.TintCheckBox;
import com.kanghanbin.wanandroid.MyApplication;
import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.BaseMvpFragment;
import com.kanghanbin.wanandroid.base.RxBus;
import com.kanghanbin.wanandroid.contract.SettingContract;
import com.kanghanbin.wanandroid.eventtype.EventNightMode;
import com.kanghanbin.wanandroid.presenter.SettingPresenter;
import com.kanghanbin.wanandroid.ui.customView.CardPickerDialog;
import com.kanghanbin.wanandroid.util.Constant;
import com.kanghanbin.wanandroid.util.ThemeHelper;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SettingFragment extends BaseMvpFragment<SettingPresenter> implements SettingContract.View, CompoundButton.OnCheckedChangeListener
,CardPickerDialog.ClickListener{


    @BindView(R.id.cb_setting_night)
    TintCheckBox cbSettingNight;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @BindView(R.id.setting_other_group)
    CardView settingOtherGroup;
    @BindView(R.id.cb_setting_cache)
    TintCheckBox cbSettingCache;
    @BindView(R.id.cb_setting_image)
    TintCheckBox cbSettingImage;
    @BindView(R.id.tv_skin)
    TextView tvSkin;


    private File cache;

    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(Constant.ARG_PARAM1, param1);
        args.putString(Constant.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initPresenter() {
        mPresenter = new SettingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        cache = new File(Constant.PATH_CACHE);
        tvSettingClear.setText(mPresenter.getCacheSize4Format(cache));
        cbSettingNight.setChecked(mPresenter.getNigthModeState());
        cbSettingCache.setChecked(mPresenter.getAutoCacheState());
        cbSettingImage.setChecked(mPresenter.getNoImageModeState());
        cbSettingNight.setOnCheckedChangeListener(this);
        cbSettingCache.setOnCheckedChangeListener(this);
        cbSettingImage.setOnCheckedChangeListener(this);
    }


    @OnClick({R.id.ll_setting_clear,R.id.ll_skin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_clear:
                mPresenter.clearCache(cache);
                tvSettingClear.setText(mPresenter.getCacheSize4Format(cache));
                break;
            case R.id.ll_skin:
                CardPickerDialog dialog = new CardPickerDialog();
                dialog.setClickListener(this);
                dialog.show(getFragmentManager(), CardPickerDialog.TAG);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_night:
                mPresenter.setNightModeState(isChecked);
                RxBus.getDefault().post(new EventNightMode(isChecked));
                break;
            case R.id.cb_setting_cache:
                mPresenter.setAutoCacheModeState(isChecked);
                break;
            case R.id.cb_setting_image:
                mPresenter.setNoImageModeState(isChecked);
                break;
            default:
                break;
        }
    }


    @Override
    public void onConfirm(int currentTheme) {
        if (ThemeHelper.getTheme(mContext) != currentTheme) {
            ThemeHelper.setTheme(mContext, currentTheme);

            tvSkin.setText(ThemeHelper.getName(currentTheme));
            ThemeUtils.refreshUI(mContext);
//            ThemeUtils.refreshUI(mContext, new ThemeUtils.ExtraRefreshable() {
//                        @Override
//                        public void refreshGlobal(Activity activity) {
//                            //for global setting, just do once
//                            if (Build.VERSION.SDK_INT >= 21) {
//                                ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription( null,null, ThemeUtils.getThemeAttrColor(mContext, android.R.attr.colorPrimary));
//                                mActivity.setTaskDescription(taskDescription);
//                                mActivity.getWindow().setStatusBarColor(ThemeUtils.getColorById(mContext, R.color.theme_color_primary_dark));
//                            }
//                        }
//
//                        @Override
//                        public void refreshSpecificView(View view) {
//                            //TODO: will do this for each traversal
//                        }
//                    }
//            );

        }
    }
}
