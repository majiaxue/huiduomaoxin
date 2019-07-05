package com.example.order.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TBOrderBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class TBAdapter extends MyRecyclerAdapter<TBOrderBean> {
    public TBAdapter(Context context, List<TBOrderBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TBOrderBean data, int position) {
        if (data.getTotalCommissionFree() == null) {
            data.setTotalCommissionFree(0.0);
        }

        if (data.getImage() != null && data.getImage().startsWith("//")) {
            data.setImage("https:" + data.getImage());
        } else if (data.getImage() != null && data.getImage().startsWith("img")) {
            data.setImage("https://" + data.getImage());
        }
        holder.setText(R.id.order_list_my_name, SPUtil.getStringValue("name"))
                .setText(R.id.order_list_name, data.getItemTitle())
                .setText(R.id.order_list_price, "￥" + data.getPrice())
                .setText(R.id.order_list_count, "x" + data.getItemNum())
                .setImageUrl(R.id.order_list_img, data.getImage())
                .setText(R.id.order_list_total, "共" + data.getItemNum() + "件商品  合计：￥" + data.getAlipayTotalPrice())
                .setText(R.id.order_list_predict, "预计收益" + ArithUtil.mul(SPUtil.getFloatValue("back"), data.getTotalCommissionFree()) + "元");

        ImageView img = holder.getView(R.id.order_list_my_head);
        Glide.with(context).load(SPUtil.getStringValue("head")).placeholder(R.drawable.vhjfg).into(img);
    }
}
