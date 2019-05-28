package com.example.type_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BabyRecBean;
import com.example.user_store.R;

import java.util.List;

public class TypeDetailLstAdapter extends MyRecyclerAdapter<BabyRecBean> {
    public TypeDetailLstAdapter(Context context, List<BabyRecBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BabyRecBean data, int position) {
        holder.setImageResource(R.id.type_detail_lst_image, data.getImage())
                .setText(R.id.type_detail_lst_name, data.getName())
                .setText(R.id.type_detail_lst_price, data.getPrice())
                .setText(R.id.type_detail_lst_payment_amount, data.getPayment_amount() + "人付款")
                .setText(R.id.type_detail_lst_good_reputation, data.getGood_reputation() + "好评")
                .setText(R.id.type_detail_lst_shop_name, data.getShop());
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.type_detail_lst_btn), position);
        }
    }
}
