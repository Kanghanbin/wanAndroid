package kanghb.com.wanandroid.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseListActivity;
import kanghb.com.wanandroid.base.BaseMvpActivity;
import kanghb.com.wanandroid.contract.UseContract;
import kanghb.com.wanandroid.model.bean.FriendBean;
import kanghb.com.wanandroid.presenter.UsePresenter;
import kanghb.com.wanandroid.util.ColorUtil;
import kanghb.com.wanandroid.util.IntentUtil;


public class UseActivity extends BaseListActivity<UsePresenter> implements UseContract.View {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tabflow)
    TagFlowLayout flowlayout;

    private List<FriendBean> friendBeans = new ArrayList<>();
    private Activity mActivity;

    @Override
    protected void initPresenter() {
        mPresenter = new UsePresenter();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_use;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setToolBar(toolBar, mContext.getString(R.string.useWebsite));
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mActivity = this;
        showLoading();
        mPresenter.getUseFriend();
        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                FriendBean friendBean = friendBeans.get(position);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, view, mContext.getString(R.string.shareView));
                IntentUtil.startArticleDetailActivity(mContext, activityOptionsCompat,
                        friendBean.getId(), friendBean.getName().trim(),
                        friendBean.getLink().trim(), false,false);
                return true;
            }
        });
    }

    @Override
    public void showUseFriend(List<FriendBean> friendBeanList) {
        closeLoading();
        showSuccess();
        friendBeans = friendBeanList;
        flowlayout.setAdapter(new TagAdapter<FriendBean>(friendBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, FriendBean friendBean) {

                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tv,
                        parent, false);
                tv.setText(friendBean.getName());
                tv.setBackgroundColor(ColorUtil.randomTagColor());
                return tv;
            }

        });
    }

}
