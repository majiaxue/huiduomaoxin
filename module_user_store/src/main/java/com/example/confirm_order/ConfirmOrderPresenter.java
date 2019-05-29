package com.example.confirm_order;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.confirm_order.adapter.ConfirmOrderAdapter;
import com.example.mvp.BasePresenter;
import com.example.payment.PaymentActivity;
import com.example.user_store.R;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderPresenter extends BasePresenter<ConfirmOrderView> {

    private List<List<Integer>> dataList;
    private ConfirmOrderAdapter orderAdapter;

    public ConfirmOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        List<Integer> insideList = new ArrayList<>();

        dataList = new ArrayList<>();
        insideList.add(1);
        dataList.add(insideList);

        insideList.add(1);
        dataList.add(insideList);
        insideList.add(1);
        insideList.add(1);
        dataList.add(insideList);

        orderAdapter = new ConfirmOrderAdapter(mContext, dataList, R.layout.rv_confirm_order);

        if (getView() != null) {
            getView().loadRv(orderAdapter);
        }

        orderAdapter.setOnTwoViewClickListener(new MyRecyclerAdapter.OnTwoViewClickListener() {
            @Override
            public void twoViewClick(View minus, View add, final int outside, final int inside) {
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dataList.get(outside).get(inside) <= 1) {

                        } else {
                            dataList.get(outside).set(inside, dataList.get(outside).get(inside) - 1);
                            orderAdapter.notifyDataSetChanged();
                        }
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataList.get(outside).set(inside, dataList.get(outside).get(inside) + 1);
                        orderAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }

    public void jumpToPayment() {
        mContext.startActivity(new Intent(mContext, PaymentActivity.class));
    }
}
