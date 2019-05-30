package com.example.cashout;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class CashoutPresenter extends BasePresenter<CashoutView> {
    public CashoutPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
