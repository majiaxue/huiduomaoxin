package com.example.register;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.CountDownTimerUtil;
import com.example.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {
    @BindView(R2.id.register_back)
    ImageView registerBack;
    @BindView(R2.id.register_phone)
    EditText registerPhone;
    @BindView(R2.id.register_code)
    EditText registerCode;
    @BindView(R2.id.register_get_code)
    TextView registerGetCode;
    @BindView(R2.id.register_invite_code)
    EditText registerInviteCode;
    @BindView(R2.id.register_password)
    EditText registerPassword;
    @BindView(R2.id.register_btn)
    TextView registerBtn;
    @BindView(R2.id.register_check)
    ImageView registerCheck;
    @BindView(R2.id.register_user_agreement)
    TextView registerUserAgreement;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        registerCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.check();
            }
        });

        registerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        registerGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCodeNum();
            }
        });

        registerUserAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void readed() {
        registerCheck.setImageResource(R.drawable.icon_yiyuedu);
    }

    @Override
    public void noRead() {
        registerCheck.setImageResource(R.drawable.icon_weiyuedu);
    }

    @Override
    public void getCodeSuccess() {
        registerGetCode.setEnabled(false);
        registerGetCode.setBackgroundResource(R.drawable.bg_get_code_clicked);
        CountDownTimerUtil.startTimer(this, registerGetCode);
    }

    @Override
    public RegisterView createView() {
        return this;
    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }
}
