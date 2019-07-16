package com.example.freecharge.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.FreeChargeBean;
import com.example.module_home.R;

import java.util.List;

public class FreeChargeLookAdapter extends MyRecyclerAdapter<FreeChargeBean> {

    public FreeChargeLookAdapter(Context context, List<FreeChargeBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, FreeChargeBean data, int position) {
        if ("0".equals(data.getPlatform())) {
            //拼多多
            holder.setImageResource(R.id.free_charge_rec_look_back_type, R.drawable.pinduoduo);
        } else if ("1".equals(data.getPlatform())) {
            //京东
            holder.setImageResource(R.id.free_charge_rec_look_back_type, R.drawable.jingdong);
        } else if ("2".equals(data.getPlatform())) {
            //淘宝
            holder.setImageResource(R.id.free_charge_rec_look_back_type, R.drawable.taobao);
        }
        holder.setImageFresco(R.id.free_charge_rec_look_back_image, data.getGoodsPic());
        holder.setText(R.id.free_charge_rec_look_back_title, data.getGoodsName());
        holder.setText(R.id.free_charge_rec_look_back_preferential_price, "￥" + data.getGoodsPrice());
        holder.setText(R.id.free_charge_rec_look_back_original_price,"￥"+data.getGoodsOriginalPrice());
        holder.setText(R.id.free_charge_rec_look_back_subsidy, "付款" + data.getGoodsPrice() + "元，补贴" + data.getGoodsBackPrice() + "元");
    }
}
