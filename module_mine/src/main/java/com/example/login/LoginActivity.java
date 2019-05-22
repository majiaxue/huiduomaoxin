package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登录
 */
@Route(path = "/mine/login")
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R2.id.login_register)
    TextView loginRegister;
    @BindView(R2.id.login_name)
    EditText loginName;
    @BindView(R2.id.login_password)
    EditText loginPassword;
    @BindView(R2.id.login_forget)
    TextView loginForget;
    @BindView(R2.id.login_confirmLogin)
    TextView loginConfirmLogin;
    @BindView(R2.id.login_btn_login)
    TextView loginBtnLogin;
    @BindView(R2.id.login_jiantou)
    ImageView loginJiantou;
    @BindView(R2.id.login_other_type)
    TextView loginOtherType;
    @BindView(R2.id.login_weixin)
    ImageView weiXin;
    @BindView(R2.id.login_cancel_left)
    View loginCancelLeft;
    @BindView(R2.id.login_cancel_right)
    View loginCancelRight;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        //点击登录
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.inputIsEmpty(loginName.getText().toString(), loginPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
        //打开微信登陆
        loginJiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showWeiXin();
            }
        });
        //打开微信登陆
        loginOtherType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showWeiXin();
            }
        });
        //注册
        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toRegister();
            }
        });
        //忘记密码
        loginForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toForget();
            }
        });
        //手机验证登录
        loginConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toCodeLogin();
            }
        });

        loginCancelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.hideWeiXin();
            }
        });

        loginCancelRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.hideWeiXin();
            }
        });
    }

    //显示微信登录
    @Override
    public void showWeiXin() {
        weiXin.setVisibility(View.VISIBLE);
        loginOtherType.setVisibility(View.GONE);
        loginJiantou.setVisibility(View.GONE);
    }

    //隐藏微信登录
    @Override
    public void hideWeiXin() {
        loginOtherType.setVisibility(View.VISIBLE);
        weiXin.setVisibility(View.GONE);
        loginJiantou.setVisibility(View.VISIBLE);
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
