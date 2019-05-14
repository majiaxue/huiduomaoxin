package com.example.hairring;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class HairringPresenter extends BasePresenter<HairringView> {

    public HairringPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
