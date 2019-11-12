package com.example.local_store.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalCartBean;
import com.example.module_local.R;

import java.util.List;

public class PopLocalCartAdapter extends MyRecyclerAdapter<LocalCartBean.InsideCart> {
    public PopLocalCartAdapter(Context context, List<LocalCartBean.InsideCart> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalCartBean.InsideCart data, int position) {
        holder.setText(R.id.rv_shop_right_name, data.getLocalGoodsName())
                .setImageUrl(R.id.rv_shop_right_img, data.getLocalGoodsPic())
                .setText(R.id.rv_shop_right_new_price, data.getPrice() + "")
                .setText(R.id.rv_shop_right_count, data.getNum() + "");

        if (viewTwoOnClickListener != null) {
            viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.rv_shop_right_minus), holder.getView(R.id.rv_shop_add), position);
        }
    }
}
