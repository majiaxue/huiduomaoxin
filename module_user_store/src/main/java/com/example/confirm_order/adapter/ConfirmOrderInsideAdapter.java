package com.example.confirm_order.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_store.R;

import java.util.List;

public class ConfirmOrderInsideAdapter extends MyRecyclerAdapter<Integer> {
    public ConfirmOrderInsideAdapter(Context context, List<Integer> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Integer data, int position) {
        holder.setText(R.id.confirm_order_count, data + "");
        if (viewTwoOnClickListener != null) {
            viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.confirm_order_minus), holder.getView(R.id.confirm_order_add), position);
        }
    }
}
