package com.example.order_retrieve;

import com.example.mvp.IView;
import com.example.order_retrieve.adapter.OrderRetrieveAdapter;

public interface OrderRetrieveView extends IView {
    void loadRv(OrderRetrieveAdapter adapter);
}
