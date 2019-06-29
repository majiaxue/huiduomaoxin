package com.example.superbrand.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;
import com.example.bean.SuperBrandBean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandRecAdapter extends MyRecyclerAdapter<SuperBrandBean.DataBean.ListsBean> {

    public SuperBrandRecAdapter(Context context, List<SuperBrandBean.DataBean.ListsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, SuperBrandBean.DataBean.ListsBean data, int position) {
        holder.setImageFresco(R.id.super_brand_rec_image,data.getPict_url());
        holder.setText(R.id.super_brand_rec_name,data.getShop_title());
//        holder.setText(R.id.super_brand_rec_rebate,data.getRebate());
    }
}
