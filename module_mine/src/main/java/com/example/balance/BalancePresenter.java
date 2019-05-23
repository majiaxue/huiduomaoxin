package com.example.balance;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class BalancePresenter extends BasePresenter<BalanceView> {
    public BalancePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
