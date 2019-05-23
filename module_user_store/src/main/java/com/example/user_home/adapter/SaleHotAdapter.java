package com.example.user_home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.SaleHotBean;
import com.example.user_store.R;

import java.util.List;

public class SaleHotAdapter extends MyRecyclerAdapter<SaleHotBean> {
    public SaleHotAdapter(Context context, List<SaleHotBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, SaleHotBean data, int position) {
        holder.setImageResource(R.id.rv_hot_image, data.getImgUrl())
                .setText(R.id.rv_hot_name, data.getName())
                .setText(R.id.rv_hot_price_new, "￥" + data.getNewPrice())
                .setText(R.id.rv_hot_price_old, data.getOldPrice())
                .setTextLine(R.id.rv_hot_price_old);
    }
}
