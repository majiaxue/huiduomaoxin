package com.example.predict.pdd;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class PddPresenter extends BasePresenter<PddView> {
    public PddPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
