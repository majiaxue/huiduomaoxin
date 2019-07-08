package com.example.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.bean.JDGoodsRecBean;

import com.example.module_base.R;
import com.example.utils.ArithUtil;

import java.util.List;


/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryJDRecAdapter extends MyRecyclerAdapter<JDGoodsRecBean.DataBean.ListsBean> {


    public SecondaryJDRecAdapter(Context context, List<JDGoodsRecBean.DataBean.ListsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, JDGoodsRecBean.DataBean.ListsBean data, int position) {
        holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.jingdong);

        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getImageInfo().getImageList().get(0).getUrl());
        holder.setText(com.example.module_base.R.id.base_name, data.getSkuName());
        holder.setText(com.example.module_base.R.id.base_reduce_price, "领劵减" + Double.valueOf(data.getCouponInfo().getCouponList().get(0).getDiscount()) + "元");
        holder.setText(com.example.module_base.R.id.base_preferential_price, "￥" + ArithUtil.sub(Double.valueOf(data.getPriceInfo().getPrice()), Double.valueOf(data.getCouponInfo().getCouponList().get(0).getDiscount())));
        holder.setText(com.example.module_base.R.id.base_original_price, Double.valueOf(data.getPriceInfo().getPrice()) + "");
        holder.setText(com.example.module_base.R.id.base_number, "已抢" + data.getInOrderCount30Days() + "件");
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);

//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        if (viewOnClickListener != null) {
//            viewOnClickListener.ViewOnClick(immediatelyGrab, position);
//        }
    }
}
