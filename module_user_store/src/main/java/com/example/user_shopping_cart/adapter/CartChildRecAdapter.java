package com.example.user_shopping_cart.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_shopping_cart.bean.CartBean;
import com.example.user_store.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartChildRecAdapter extends MyRecyclerAdapter<CartBean.RecordsBean.ItemsBean> {

    public CartChildRecAdapter(Context context, List<CartBean.RecordsBean.ItemsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CartBean.RecordsBean.ItemsBean data, int position) {
        holder.setImageFresco(R.id.cart_child_image, data.getProductPic());
        holder.setText(R.id.cart_child_name, data.getProductName());
        holder.setText(R.id.cart_child_colour, data.getSp1() + "，");
        holder.setText(R.id.cart_child_size, data.getSp2());
        holder.setText(R.id.cart_child_price, "￥" + data.getPrice());

    }
}
