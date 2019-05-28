package com.example.confirm_order;

import com.example.confirm_order.adapter.ConfirmOrderAdapter;
import com.example.mvp.IView;

public interface ConfirmOrderView extends IView {
    void loadRv(ConfirmOrderAdapter adapter);
}
