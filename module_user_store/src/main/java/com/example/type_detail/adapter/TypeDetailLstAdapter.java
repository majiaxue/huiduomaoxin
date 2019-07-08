package com.example.type_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.HotSaleBean;
import com.example.entity.BabyRecBean;
import com.example.user_store.R;

import java.util.List;

public class TypeDetailLstAdapter extends MyRecyclerAdapter<HotSaleBean.DataBean> {
    public TypeDetailLstAdapter(Context context, List<HotSaleBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, HotSaleBean.DataBean data, int position) {
        holder.setImageFresco(R.id.baby_image, data.getPic())
                .setText(R.id.baby_name, data.getName())
                .setText(R.id.baby_price, "￥" + data.getPrice())
                .setText(R.id.baby_payment_amount, data.getSale() + "人付款")
                .setText(R.id.baby_good_reputation, "尚无评论".equals(data.getGoodReputation()) ? "尚无评论" : data.getGoodReputation() + "好评")
                .setText(R.id.baby_shop, data.getSellerName());
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.type_detail_lst_btn), position);
        }
    }
}
