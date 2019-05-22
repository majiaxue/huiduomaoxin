package com.example.order_retrieve.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.OrderRetrieveBean;
import com.example.module_mine.R;

import java.util.List;

public class OrderRetrieveAdapter extends MyRecyclerAdapter<OrderRetrieveBean> {
    public OrderRetrieveAdapter(Context context, List<OrderRetrieveBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, OrderRetrieveBean data, int position) {
        holder.setText(R.id.rv_order_retrieve_txt, data.getCentent())
                .setText(R.id.rv_order_retrieve_num, data.getNum());
    }
}
