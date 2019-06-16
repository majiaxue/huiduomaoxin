package com.example.mineorder.staydeliverygoods.orderdetails;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/6/16
 * Describe:
 */
public class OrderDetailsPresenter extends BasePresenter<OrderDetailsView> {

    public OrderDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
