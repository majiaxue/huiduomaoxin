package com.example.register;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    private boolean isRead = true;

    public RegisterPresenter(Context context) {
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
}
