package com.example.order.fragment_pay;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface PayOrderView extends IView {
    void loadMineRv(RvListAdapter adapter);
}
