package com.example.balance.payout;

import android.content.Context;

import com.example.balance.adapter.IncomeAdapter;
import com.example.entity.MessageListBean;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class PayoutPresenter extends BasePresenter<PayoutView> {
    public PayoutPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        List<MessageListBean> dataList = new ArrayList<>();
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));
        dataList.add(new MessageListBean("支出", "50.00", "支出时间：2018-11-01"));

        IncomeAdapter incomeAdapter = new IncomeAdapter(mContext, dataList, R.layout.rv_income, 1);
        if (getView() != null) {
            getView().loadRv(incomeAdapter);
        }
    }
}
