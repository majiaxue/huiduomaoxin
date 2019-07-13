package com.example.community.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;

import java.util.List;

public class GoodGoodsInsideAdapter extends MyRecyclerAdapter<Integer> {
    public GoodGoodsInsideAdapter(Context context, List<Integer> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Integer data, int position) {
        holder.setImageResource(R.id.rv_goods_commend_inside_img, data);
    }
}
