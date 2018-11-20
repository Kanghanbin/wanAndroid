package kanghb.com.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseMvpFragment;
import kanghb.com.wanandroid.base.RxBus;
import kanghb.com.wanandroid.contract.SettingContract;
import kanghb.com.wanandroid.eventtype.EventNightMode;
import kanghb.com.wanandroid.presenter.SettingPresenter;
import kanghb.com.wanandroid.util.Constant;


public class SettingFragment extends BaseMvpFragment<SettingPresenter> implements SettingContract.View, CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox cbSettingNight;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @BindView(R.id.setting_other_group)
    CardView settingOtherGroup;
    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox cbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox cbSettingImage;


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


    @OnClick({R.id.ll_setting_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_clear:
                mPresenter.clearCache(cache);
                tvSettingClear.setText(mPresenter.getCacheSize4Format(cache));
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

        }
    }

}
