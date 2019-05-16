package com.example.predict.sc;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class SCPresenter extends BasePresenter<SCView> {
    public SCPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
