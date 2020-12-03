package com.kanghanbin.wanandroid.ui.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.OnClick;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.BaseMvpActivity;
import com.kanghanbin.wanandroid.contract.LoginContract;
import com.kanghanbin.wanandroid.presenter.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.et_login_admin)
    EditText etLoginAdmin;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.sv_login)
    ScrollView svLogin;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.second_password)
    EditText secondPassword;
    @BindView(R.id.sv_register)
    ScrollView svRegister;
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.rl_pic_anim)
    RelativeLayout rlPicAnim;
    @BindView(R.id.btn_login)
    Button btnLogin;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }



    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter();
    }



    @Override
    public void gotoMain() {

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(btnLogin,btnLogin.getWidth()/2,btnLogin.getHeight()/2,0,0);
        ActivityCompat.startActivity(mContext,new Intent(this, MainActivity.class),compat.toBundle());
        onBackPressedSupport();
    }

    @Override
    public void gotoRegist() {
        svRegister.setVisibility(View.VISIBLE);
        svLogin.setVisibility(View.GONE);
    }

    @Override
    public void gotoLogin() {
        svLogin.setVisibility(View.VISIBLE);
        svRegister.setVisibility(View.GONE);
    }


    @OnClick({R.id.btn_login, R.id.btn_jumpto_register, R.id.btn_jumpto_login, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mPresenter.login(etLoginAdmin.getText().toString().trim(), etLoginPwd.getText().toString().trim());
                break;
            case R.id.btn_jumpto_register:
                gotoRegist();
                break;
            case R.id.btn_jumpto_login:
                gotoLogin();
                break;
            case R.id.btn_regist:
                mPresenter.regist(registerPhone.getText().toString().trim(), registerPassword.getText().toString().trim(), secondPassword.getText().toString().trim());
                break;
        }
    }

}
