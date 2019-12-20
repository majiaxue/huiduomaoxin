package com.example.local_order_confirm.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.RedPackageBean;

import java.util.List;

public class LocalOrderCouponAdapter extends MyRecyclerAdapter<RedPackageBean> {
    public LocalOrderCouponAdapter(Context context, List<RedPackageBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RedPackageBean data, int position) {
        holder.setText(com.example.module_base.R.id.rv_coupon_wallet_money, data.getMoney())
                .setText(com.example.module_base.R.id.rv_coupon_wallet_count, TextUtils.isEmpty(data.getCount()) ? "1" : data.getCount());
    }
}
