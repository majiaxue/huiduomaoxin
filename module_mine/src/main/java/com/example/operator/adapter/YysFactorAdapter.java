package com.example.operator.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.YysFactorBean;
import com.example.module_mine.R;

import java.util.List;

public class YysFactorAdapter extends MyRecyclerAdapter<YysFactorBean> {
    public YysFactorAdapter(Context context, List<YysFactorBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, YysFactorBean data, int position) {
        holder.setText(R.id.rv_factor_type, data.getType())
                .setText(R.id.rv_factor_price, data.getPrice());
        viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_factor_pay), position);
    }
}
