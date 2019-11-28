package com.example.web_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class WebDetailPresenter extends BasePresenter<WebDetailView> {
    public WebDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
