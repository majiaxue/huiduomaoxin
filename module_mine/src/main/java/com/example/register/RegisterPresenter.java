package com.example.register;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    public RegisterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
