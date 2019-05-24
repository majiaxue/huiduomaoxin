package com.example.user_shopping_cart.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_shopping_cart.bean.CartChildBean;
import com.example.user_shopping_cart.bean.CartParentBean;
import com.example.user_store.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartParentRecAdapter extends MyRecyclerAdapter<CartParentBean> {
    public CartParentRecAdapter(Context context, List<CartParentBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CartParentBean data, int position) {
        holder.setText(R.id.cart_parent_name, data.getName());
        RecyclerView cartParentRec = holder.getView(R.id.cart_parent_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        cartParentRec.setLayoutManager(linearLayoutManager);
        List<CartChildBean> o_list = new ArrayList<>();
        o_list.add(new CartChildBean(false, R.drawable.img_108, "原宿潮牌短袖T恤男夏季新款休闲欧美bf风青少年韩版宽松大码T恤", "白色，", "XL", "￥59"));
        o_list.add(new CartChildBean(false, R.drawable.img_109, "原宿潮牌短袖T恤男夏季新款休闲欧美bf风青少年韩版宽松大码T恤", "白色，", "L", "￥49"));
        o_list.add(new CartChildBean(false, R.drawable.img_109, "原宿潮牌短袖T恤男夏季新款休闲欧美bf风青少年韩版宽松大码T恤", "白色，", "L", "￥49"));
        CartChildRecAdapter cartChildRecAdapter = new CartChildRecAdapter(context, o_list, R.layout.item_cart_child);
        cartParentRec.setAdapter(cartChildRecAdapter);
    }
}
