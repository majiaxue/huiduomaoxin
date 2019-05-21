package com.example.order.fragment_all;

import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface AllOrderView extends IView {

    void loadUI(RvListAdapter adapter);
}
