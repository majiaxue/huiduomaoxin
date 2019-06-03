package com.example.order.fragment_settle;

import android.content.Context;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.order.adapter.RvListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SettleOrderPresenter extends BasePresenter<SettleOrderView> {
    private List mineList = new ArrayList();
    private List fansList = new ArrayList();
    private RvListAdapter mineAdapter;
    private FansOrderRvAdapter fansAdapter;

    public SettleOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String flag) {
        mineList.add("");
        mineList.add("");
        mineList.add("");
        mineList.add("");
        mineList.add("");
        mineList.add("");
        if ("mine".equals(flag)) {
            getMineOrder();
        } else if ("fans".equals(flag)) {
            getFansOrder();
        }

    }

    private void getMineOrder() {
        if (mineAdapter == null) {
            mineAdapter = new RvListAdapter(mContext, mineList, R.layout.rv_order_list);
            getView().loadMineRv(mineAdapter);
        } else {
            mineAdapter.notifyDataSetChanged();
        }
    }

    private void getFansOrder() {
        fansList.add("");
        fansList.add("");
        fansList.add("");
        fansList.add("");
        fansList.add("");
        fansList.add("");
        if (fansAdapter == null) {
            fansAdapter = new FansOrderRvAdapter(mContext, fansList, R.layout.rv_fans_order_list);
            getView().loadFansRv(fansAdapter);
        } else {
            fansAdapter.notifyDataSetChanged();
        }
    }
}
