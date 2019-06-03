package com.example.order.fragment_all;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface AllOrderView extends IView {

    void loadMineRv(RvListAdapter adapter);

    void loadFansRv(FansOrderRvAdapter adapter);
}
