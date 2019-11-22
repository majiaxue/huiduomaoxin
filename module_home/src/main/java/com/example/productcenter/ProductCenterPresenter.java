package com.example.productcenter;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class ProductCenterPresenter extends BasePresenter<ProductCenterView> {

    public ProductCenterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
