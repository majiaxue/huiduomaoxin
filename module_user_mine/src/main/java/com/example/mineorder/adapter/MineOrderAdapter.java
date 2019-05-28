package com.example.mineorder.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.mineorder.bean.MineOrderBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class MineOrderAdapter extends MyRecyclerAdapter<MineOrderBean> {

    public MineOrderAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MineOrderBean data, int position) {
        holder.setText(R.id.order_rec_shop, data.getShop());
        holder.setImageResource(R.id.order_rec_img, data.getImage());
        holder.setText(R.id.order_rec_status, data.getStatus());
        holder.setText(R.id.order_rec_name, data.getName());
        holder.setText(R.id.order_rec_message, data.getMessage());
        holder.setText(R.id.order_rec_price, data.getPrice());
        holder.setText(R.id.order_rec_count, data.getCount());
        holder.setText(R.id.order_rec_total, data.getTotal());
        viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.order_rec_shop), holder.getView(R.id.order_rec_btn_left), holder.getView(R.id.order_rec_btn_right), position);

    }

}
