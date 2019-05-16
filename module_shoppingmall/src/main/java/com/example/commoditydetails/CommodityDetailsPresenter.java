package com.example.commoditydetails;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class CommodityDetailsPresenter extends BasePresenter<CommodityDetailsView> {

    public CommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
