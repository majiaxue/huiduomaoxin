package com.example.balance;

import com.example.mvp.IView;
import com.example.order.adapter.OrderVPAdapter;

public interface BalanceView extends IView {

    void loadBalance(String balance);
}
