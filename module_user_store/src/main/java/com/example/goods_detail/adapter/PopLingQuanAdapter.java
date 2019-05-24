package com.example.goods_detail.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.CouponBean;
import com.example.user_store.R;

import java.util.List;

public class PopLingQuanAdapter extends MyRecyclerAdapter<CouponBean> {
    public PopLingQuanAdapter(Context context, List<CouponBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CouponBean data, int position) {
        holder.setText(R.id.rv_pop_lingquan_money, data.getMoney())
                .setText(R.id.rv_pop_lingquan_demand, data.getDemand())
                .setText(R.id.rv_pop_lingquan_time, "有效期至  " + data.getTime());
        if (data.isHas()) {
            holder.getView(R.id.rv_pop_lingquan_img).setVisibility(View.VISIBLE);
            holder.getView(R.id.rv_pop_lingquan_parent).setEnabled(false);
        } else {
            holder.getView(R.id.rv_pop_lingquan_img).setVisibility(View.GONE);
            holder.getView(R.id.rv_pop_lingquan_parent).setEnabled(true);
        }
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_pop_lingquan_parent), position);
        }
    }
}
