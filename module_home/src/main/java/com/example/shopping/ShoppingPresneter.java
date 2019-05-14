package com.example.shopping;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class ShoppingPresneter extends BasePresenter<ShoppingView> {
    public ShoppingPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
