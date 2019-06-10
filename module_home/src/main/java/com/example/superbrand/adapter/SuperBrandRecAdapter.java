package com.example.superbrand.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;
import com.example.superbrand.bean.SuperBrandBean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandRecAdapter extends MyRecyclerAdapter<SuperBrandBean> {

    public SuperBrandRecAdapter(Context context, List<SuperBrandBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, SuperBrandBean data, int position) {
        holder.setImageResource(R.id.super_brand_rec_image,data.getImage());
        holder.setText(R.id.super_brand_rec_name,data.getName());
        holder.setText(R.id.super_brand_rec_rebate,data.getRebate());
    }
}
