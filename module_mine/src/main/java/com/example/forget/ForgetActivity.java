package com.example.forget;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.CountDownTimerUtil;

import butterknife.BindView;

/**
 * 忘记密码
 */
public class ForgetActivity extends BaseActivity<ForgetView, ForgetPresneter> implements ForgetView {
    @BindView(R2.id.forget_back)
    ImageView forgetBack;
    @BindView(R2.id.forget_phone)
    EditText forgetPhone;
    @BindView(R2.id.forget_code)
    EditText forgetCode;
    @BindView(R2.id.forget_get_code)
    TextView forgetGetCode;
    @BindView(R2.id.forget_paddword)
    EditText forgetPaddword;
    @BindView(R2.id.forget_confirm_password)
    EditText forgetConfirmPassword;
    @BindView(R2.id.forget_btn)
    TextView forgetBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        forgetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commit(forgetPhone.getText().toString(), forgetCode.getText().toString(), forgetPaddword.getText().toString(), forgetConfirmPassword.getText().toString());
            }
        });
        //获取验证码
        forgetGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCodeNum();
            }
        });
    }

    //获取后修改ui
    @Override
    public void getCodeSuccess() {
        forgetGetCode.setEnabled(false);
        forgetGetCode.setBackgroundResource(R.drawable.bg_get_code_clicked);
        CountDownTimerUtil.startTimer(this, forgetGetCode);
    }

    @Override
    public ForgetView createView() {
        return this;
    }

    @Override
    public ForgetPresneter createPresenter() {
        return new ForgetPresneter(this);
    }
}
