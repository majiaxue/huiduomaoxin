package com.example.order.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.order.adapter.RvListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListFragment extends BaseFragment<OrderListView, OrderListPresenter> implements OrderListView {
    @BindView(R2.id.order_list_rv)
    RecyclerView orderListRv;
    @BindView(R2.id.order_list_refresh)
    SmartRefreshLayout orderListRefresh;

    private List dataList = new ArrayList();

    public void setDataList(String data) {
        dataList.clear();
        dataList.add(data);
        presenter.loadData(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderListRv.setLayoutManager(layoutManager);
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadUI(RvListAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public OrderListView createView() {
        return this;
    }

    @Override
    public OrderListPresenter createPresenter() {
        return new OrderListPresenter(getContext());
    }
}
