package com.example.fans_order;

import com.example.mvp.IView;
import com.example.order.adapter.OrderVPAdapter;

public interface FansOrderView extends IView {

    void updateVP(OrderVPAdapter adapter);

    void typeChanged(int position);
}
