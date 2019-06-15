package com.example.commoditydetails.taobao.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.commoditydetails.taobao.bean.TBGoodChoiceBean;
import com.example.utils.ArithUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/13
 * Describe:
 */
public class TBRecommendAdapter extends MyRecyclerAdapter<TBGoodChoiceBean.DataBean> {

    public TBRecommendAdapter(Context context, List<TBGoodChoiceBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TBGoodChoiceBean.DataBean data, int position) {
        // 1表示天猫，0表示淘宝产品
        if (data.getUser_type().equals("0")) {
            //淘宝
            holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.taobao);
        } else {
            //天猫
            holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.tianmao);
        }

        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getPict_url());
        holder.setText(com.example.module_base.R.id.base_name, data.getTitle());
        holder.setText(com.example.module_base.R.id.base_reduce_price, "领劵减" + data.getCoupon_amount() + "元");
        holder.setText(com.example.module_base.R.id.base_preferential_price, "￥" + ArithUtil.sub(Double.valueOf(data.getZk_final_price()), Double.valueOf(data.getCoupon_amount())));
        holder.setText(com.example.module_base.R.id.base_original_price, data.getZk_final_price());
        holder.setText(com.example.module_base.R.id.base_number, "已抢" + data.getVolume() + "件");
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(com.example.module_base.R.id.base_original_price);

        TextView immediatelyGrab = holder.getView(com.example.module_base.R.id.base_immediately_grab);
        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }
}
