package com.example.user_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.HotSaleBean;
import com.example.entity.CommendBean;
import com.example.user_store.R;

import java.util.List;

public class CommendAdapter extends MyRecyclerAdapter<HotSaleBean.DataBean> {
    public CommendAdapter(Context context, List<HotSaleBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, HotSaleBean.DataBean data, int position) {
        holder.setText(R.id.rv_commend_name, data.getName())
                .setText(R.id.rv_commend_price, "￥" + data.getPrice())
                .setText(R.id.rv_commend_count, data.getSale() + "人付款")
                .setText(R.id.rv_commend_shop, data.getSellerName())
                .setImageUrl(R.id.rv_commend_img, data.getPic());
        if (position == 0) {
            ImageView img = holder.getView(R.id.rv_commend_img);
            ViewGroup.LayoutParams params = img.getLayoutParams();
            params.height = (int) context.getResources().getDimension(R.dimen.dp_142);
            img.setLayoutParams(params);
        }
        if (viewTwoOnClickListener != null) {
            viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.rv_commend_parent), holder.getView(R.id.rv_commend_btn), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
