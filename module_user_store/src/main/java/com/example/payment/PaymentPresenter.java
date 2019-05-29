package com.example.payment;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class PaymentPresenter extends BasePresenter<PaymentView> {
    public PaymentPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
