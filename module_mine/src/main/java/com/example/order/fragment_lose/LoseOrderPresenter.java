package com.example.order.fragment_lose;

import android.content.Context;

import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.order.adapter.RvListAdapter;

import java.util.List;

public class LoseOrderPresenter extends BasePresenter<LoseOrderView> {
    private RvListAdapter adapter;

    public LoseOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(List list) {
        if (adapter == null) {
            adapter = new RvListAdapter(mContext, list, R.layout.rv_order_list);
            getView().loadUI(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
