package com.example.goods_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.AssessBean;
import com.example.user_store.R;

import java.util.List;

public class GoodsAssessAdapter extends MyRecyclerAdapter<AssessBean> {
    public GoodsAssessAdapter(Context context, List<AssessBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AssessBean data, int position) {
        holder.setText(R.id.rv_goods_assess_name, data.getName())
                .setText(R.id.rv_goods_assess_content, data.getContent())
                .setImageUrl(R.id.rv_goods_assess_img, data.getImgUrl());
    }
}
