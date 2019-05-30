package com.example.balance;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.balance.income.IncomeFragment;
import com.example.balance.payout.PayoutFragment;
import com.example.cashout.CashoutActivity;
import com.example.mvp.BasePresenter;
import com.example.order.adapter.OrderVPAdapter;

import java.util.ArrayList;
import java.util.List;

public class BalancePresenter extends BasePresenter<BalanceView> {

    private List<Fragment> fragmentList;

    public BalancePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initVP(FragmentManager fm, String[] titleArr) {
        fragmentList = new ArrayList<>();
        fragmentList.add(new IncomeFragment());
        fragmentList.add(new PayoutFragment());
        OrderVPAdapter vpAdapter = new OrderVPAdapter(fm, fragmentList, titleArr);
        if (getView() != null) {
            getView().updateVP(vpAdapter);
        }
    }

    public void loadData() {

    }

    public void jumpToCashout() {
        mContext.startActivity(new Intent(mContext, CashoutActivity.class));
    }
}
