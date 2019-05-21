package com.example.order.fragment_settle;

import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface SettleOrderView extends IView {
    void loadUI(RvListAdapter adapter);
}
