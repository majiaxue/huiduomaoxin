package com.example.coupon.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.coupon.bean.CouponBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class CouponAdapter extends MyRecyclerAdapter<CouponBean> {

    public CouponAdapter(Context context, List<CouponBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CouponBean data, int position) {
        holder.setText(R.id.coupon_rec_shop_name, data.getShopName());
        holder.setImageResource(R.id.coupon_rec_image, data.getImage());
        holder.setText(R.id.coupon_rec_qian, data.getQian());
        holder.setText(R.id.coupon_rec_total_usage_amount, data.getTotalUsageAmount());
        holder.setText(R.id.coupon_rec_valid_time, data.getValidTime());
        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.coupon_rec_go_shop), holder.getView(R.id.coupon_rec_immediate_use), position);
    }
}
