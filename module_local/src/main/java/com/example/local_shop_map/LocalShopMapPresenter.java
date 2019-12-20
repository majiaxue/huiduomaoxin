package com.example.local_shop_map;


import android.content.Context;

import com.example.mvp.BasePresenter;

public class LocalShopMapPresenter extends BasePresenter<LocalShopMapView> {

    public LocalShopMapPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
