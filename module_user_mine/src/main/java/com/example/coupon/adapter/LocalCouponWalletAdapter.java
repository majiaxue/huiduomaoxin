package com.example.coupon.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.RedPackageBean;
import com.example.module_user_mine.R;

import java.util.List;

public class LocalCouponWalletAdapter extends MyRecyclerAdapter<RedPackageBean> {
    public LocalCouponWalletAdapter(Context context, List<RedPackageBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RedPackageBean data, int position) {
        holder.setText(R.id.rv_coupon_wallet2_money, data.getMoney())
                .setText(R.id.rv_coupon_wallet2_time, data.getPayTime().split(" ")[0] + " 至 " + data.getEndTime().split(" ")[0]);
    }
}
