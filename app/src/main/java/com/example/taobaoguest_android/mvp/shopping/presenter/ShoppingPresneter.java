package com.example.taobaoguest_android.mvp.shopping.presenter;

import android.content.Context;

import com.example.taobaoguest_android.base.BasePresenter;
import com.example.taobaoguest_android.mvp.shopping.view.ShoppingView;

public class ShoppingPresneter extends BasePresenter<ShoppingView> {
    public ShoppingPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
