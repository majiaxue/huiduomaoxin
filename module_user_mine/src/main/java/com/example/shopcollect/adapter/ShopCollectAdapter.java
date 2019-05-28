package com.example.shopcollect.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class ShopCollectAdapter extends MyRecyclerAdapter<BaseRecImageAndTextBean> {
    public ShopCollectAdapter(Context context, List<BaseRecImageAndTextBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BaseRecImageAndTextBean data, int position) {
        holder.setImageResource(R.id.shop_collect_rec_image,data.getImage());
        holder.setText(R.id.shop_collect_rec_name,data.getName());
        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.shop_collect_rec_menu),holder.getView(R.id.shop_collect_rec_un_follow),position);
    }
}
