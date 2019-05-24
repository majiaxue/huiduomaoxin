package com.example.user_shopping_cart.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_shopping_cart.bean.CartChildBean;
import com.example.user_store.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartChildRecAdapter extends MyRecyclerAdapter<CartChildBean> {

    public CartChildRecAdapter(Context context, List<CartChildBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CartChildBean data, int position) {
        holder.setImageResource(R.id.cart_child_image,data.getImage());
        holder.setText(R.id.cart_child_name,data.getName());
        holder.setText(R.id.cart_child_colour,data.getColour());
        holder.setText(R.id.cart_child_size,data.getSize());
        holder.setText(R.id.cart_child_price,data.getPrice());

    }
}
