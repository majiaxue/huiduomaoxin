package com.example.order.fragment;

import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface OrderListView extends IView {

    void loadUI(RvListAdapter adapter);
}
