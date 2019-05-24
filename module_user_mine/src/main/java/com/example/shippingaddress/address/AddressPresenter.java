package com.example.shippingaddress.address;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class AddressPresenter extends BasePresenter<AddressView>{

    public AddressPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
