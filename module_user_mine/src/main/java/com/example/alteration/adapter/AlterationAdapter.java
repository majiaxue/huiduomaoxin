package com.example.alteration.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.alteration.bean.AlterationBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class AlterationAdapter extends MyRecyclerAdapter<AlterationBean> {

    public AlterationAdapter(Context context, List<AlterationBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AlterationBean data, int position) {
        holder.setText(R.id.alteration_rec_shop_name, data.getShopName());
        holder.setImageResource(R.id.alteration_rec_image, data.getImage());
        holder.setText(R.id.alteration_rec_name, data.getName());
        holder.setText(R.id.alteration_rec_count, data.getCount());
        holder.setText(R.id.alteration_rec_colour, data.getColour());
        holder.setText(R.id.alteration_rec_size, data.getSize());
        holder.setText(R.id.alteration_rec_type, data.getAlterationType());
        holder.setText(R.id.alteration_rec_status, data.getAlterationStatus());
        viewOnClickListener.ViewOnClick(holder.getView(R.id.alteration_rec_view_details), position);
    }
}
