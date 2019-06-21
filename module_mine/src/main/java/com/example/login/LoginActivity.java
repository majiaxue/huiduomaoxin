package com.example.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.entity.EventBusBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

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
    @BindView(R2.id.login_weixin)
    ImageView weiXin;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initClick() {
        //点击登录
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(loginName.getText().toString(), loginPassword.getText().toString());
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

        weiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.WeChatLogin();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if ("WXCODE".equals(eventBusBean.getMsg())) {
            presenter.sendCode();
        }
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
}
