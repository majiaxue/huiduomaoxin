package com.example.fans_order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_mine.R;

import java.util.List;

public class FansOrderRvAdapter extends MyRecyclerAdapter {
    public FansOrderRvAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object data, int position) {
        holder.setText(R.id.fans_order_list_shop, (String) data);
    }
}
