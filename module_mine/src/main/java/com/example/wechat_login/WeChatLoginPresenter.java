package com.example.wechat_login;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class WeChatLoginPresenter extends BasePresenter<WeChatLoginView> {
    private boolean isRead = true;

    public WeChatLoginPresenter(Context context) {
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
