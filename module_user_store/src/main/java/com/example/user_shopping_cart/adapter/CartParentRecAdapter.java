package com.example.user_shopping_cart.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_shopping_cart.bean.CartBean;
import com.example.user_store.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartParentRecAdapter extends MyRecyclerAdapter<CartBean.RecordsBean> {
    public CartParentRecAdapter(Context context, List<CartBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CartBean.RecordsBean data, int position) {
        holder.setText(R.id.cart_parent_name, data.getSellerName());
        RecyclerView cartParentRec = holder.getView(R.id.cart_parent_rec);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        cartParentRec.setLayoutManager(linearLayoutManager);
        CartChildRecAdapter cartChildRecAdapter = new CartChildRecAdapter(context, data.getItems(), R.layout.item_cart_child);
        cartParentRec.setAdapter(cartChildRecAdapter);
    }
}
