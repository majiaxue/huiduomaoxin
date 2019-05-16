package com.example.predict.tb;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class TBPresenter extends BasePresenter<TBView> {
    public TBPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
