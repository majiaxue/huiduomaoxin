package com.example.fans_order.fragment_pay;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface FansPayOrderView extends IView {
    void loadMineRv(RvListAdapter adapter);

    void loadFansRv(FansOrderRvAdapter adapter);

    void loadSuccess();
}