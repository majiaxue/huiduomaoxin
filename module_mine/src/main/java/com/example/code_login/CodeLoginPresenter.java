package com.example.code_login;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class CodeLoginPresenter extends BasePresenter<CodeLoginView> {
    public CodeLoginPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
