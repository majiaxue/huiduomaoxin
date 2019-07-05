package com.example.fans_order.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TbFansOrderBean;
import com.example.module_mine.R;
import com.example.utils.MyTimeUtil;

import java.util.List;

public class TbFansAdapter extends MyRecyclerAdapter<TbFansOrderBean> {
    public TbFansAdapter(Context context, List<TbFansOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TbFansOrderBean data, int position) {
        if (data.getImage() != null && data.getImage().startsWith("//")) {
            data.setImage("https:" + data.getImage());
        } else if (data.getImage() != null && data.getImage().startsWith("img")) {
            data.setImage("https://" + data.getImage());
        }

        holder.setText(R.id.fans_order_list_name, data.getItemTitle())
                .setImageUrl(R.id.fans_order_list_img, data.getImage())
                .setText(R.id.fans_order_list_price, "￥" + data.getPrice())
                .setText(R.id.fans_order_list_count, "x" + data.getItemNum())
                .setText(R.id.fans_order_list_total, "共" + data.getItemNum() + "件商品  合计：￥" + data.getAlipayTotalPrice())
                .setText(R.id.fans_order_list_time, "购买时间：" + data.getCreateTime());
    }
}