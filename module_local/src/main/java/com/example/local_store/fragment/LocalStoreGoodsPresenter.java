package com.example.local_store.fragment;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class LocalStoreGoodsPresenter extends BasePresenter<LocalStoreGoodsView> {
    public LocalStoreGoodsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
