package com.example.login;

import android.content.Context;
import android.content.Intent;

import com.example.code_login.CodeLoginActivity;
import com.example.forget.ForgetActivity;
import com.example.mvp.BasePresenter;
import com.example.register.RegisterActivity;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public boolean inputIsEmpty(String name, String password) {
        if (null == name || null == password || "".equals(name) || "".equals(password)) {
            return true;
        } else {
            return false;
        }

    }

    public void showWeiXin() {
        getView().showWeiXin();
    }

    public void hideWeiXin() {
        getView().hideWeiXin();
    }

    public void toRegister() {
        mContext.startActivity(new Intent(mContext, RegisterActivity.class));
    }

    public void toForget() {
        mContext.startActivity(new Intent(mContext, ForgetActivity.class));
    }

    public void toCodeLogin() {
        mContext.startActivity(new Intent(mContext, CodeLoginActivity.class));
    }
}
