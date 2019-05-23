package com.example.goods_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    public GoodsDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
