package com.example.login_wechat;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class LoginWeChatPresenter extends BasePresenter<LoginWeChatView> {
    private boolean isRead = true;

    public LoginWeChatPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void check() {
        if (isRead) {
            getView().noRead();
            isRead = false;
        } else {
            getView().readed();
            isRead = true;
        }
    }

    public void getCodeNum() {
        getView().getCodeSuccess();
    }
}
