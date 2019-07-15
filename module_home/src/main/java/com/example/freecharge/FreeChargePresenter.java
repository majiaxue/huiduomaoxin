package com.example.freecharge;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class FreeChargePresenter extends BasePresenter<FreeChargeView> {

    public FreeChargePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
