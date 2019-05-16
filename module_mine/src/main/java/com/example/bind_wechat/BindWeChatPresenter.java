package com.example.bind_wechat;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class BindWeChatPresenter extends BasePresenter<BindWeChatView> {
    public BindWeChatPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
