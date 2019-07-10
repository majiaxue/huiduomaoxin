package com.example.local_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class LocalDetailPresenter extends BasePresenter<LocalDetailView> {
    public LocalDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
