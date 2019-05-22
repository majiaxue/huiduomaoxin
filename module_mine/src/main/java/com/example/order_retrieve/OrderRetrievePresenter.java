package com.example.order_retrieve;

import android.content.Context;

import com.example.entity.OrderRetrieveBean;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.order_retrieve.adapter.OrderRetrieveAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderRetrievePresenter extends BasePresenter<OrderRetrieveView> {

    private List<OrderRetrieveBean> dataList;

    public OrderRetrievePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        dataList = new ArrayList();
        dataList.add(new OrderRetrieveBean("1.", "购买人没同步的订单可通过订单查询找回；"));
        dataList.add(new OrderRetrieveBean("2.", "当查找人查找到订单，并且该订单在订单库确实找不到归属时，改订单归属到查找人；"));
        dataList.add(new OrderRetrieveBean("3.", "当有多个用户输入同一订单号，以第一个输入人为准；"));
        dataList.add(new OrderRetrieveBean("4.", "已归属的订单不支持继续查询；"));
        dataList.add(new OrderRetrieveBean("5.", "建议购买人自查，运营商帮助超级会员查询时，不要点击确认找回，可让购买人自查并找回；"));
        dataList.add(new OrderRetrieveBean("6.", "收益将按查找人的当前的用户关系进行归属。"));
        OrderRetrieveAdapter retrieveAdapter = new OrderRetrieveAdapter(mContext, dataList, R.layout.rv_order_retrieve);
        if (getView() != null) {
            getView().loadRv(retrieveAdapter);
        }
    }
}
