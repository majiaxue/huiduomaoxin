package com.example.login;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginView> {
    private boolean isShow = false;

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
        if (isShow) {
            getView().showWeiXin();
            isShow = false;
        } else {
            getView().hideWeiXin();
            isShow = true;
        }
    }
}
