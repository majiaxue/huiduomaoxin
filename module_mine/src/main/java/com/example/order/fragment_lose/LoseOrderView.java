package com.example.order.fragment_lose;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface LoseOrderView extends IView {
    void loadMineRv(RvListAdapter adapter);

    void loadFansRv(FansOrderRvAdapter adapter);
}
