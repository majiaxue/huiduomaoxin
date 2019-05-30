package com.example.code_login;

import android.content.Context;
import android.widget.Toast;

import com.example.mvp.BasePresenter;

public class CodeLoginPresenter extends BasePresenter<CodeLoginView> {
    private boolean isAgree = true;

    public CodeLoginPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getCode() {
        getView().getCodeSuccess();
    }

    public void submit(String phone, String code) {
        if (phone.length() != 11) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if ("".equals(code) || code == null) {
            Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else if (!isAgree) {
            Toast.makeText(mContext, "未同意用户协议", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    public void checkAgreement() {
        if (isAgree) {
            isAgree = false;
            getView().disagreeAgreement();
        } else {
            isAgree = true;
            getView().agreeAgreement();
        }
    }
}
