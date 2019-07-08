package com.example.home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.GoodsRecommendBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/13
 * Describe:
 */
public class GoodsRecommendAdapter extends MyRecyclerAdapter<GoodsRecommendBean.DataBean> {

    public GoodsRecommendAdapter(Context context, List<GoodsRecommendBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodsRecommendBean.DataBean data, int position) {
        // 1表示天猫，0表示淘宝产品
        if (data.getUser_type().equals("0")) {
            //淘宝
            holder.setImageResource(R.id.base_type, R.drawable.taobao);
        } else {
            //天猫
            holder.setImageResource(R.id.base_type, R.drawable.tianmao);
        }
        double couponPrice = ArithUtil.sub(Double.valueOf(data.getZk_final_price()), Double.valueOf(data.getCoupon_amount()));//商品价格
        double mul = ArithUtil.mul(couponPrice, ArithUtil.div(Double.valueOf(data.getCommission_rate()), 1000, 2));//商品收益需要乘个人收益

        holder.setImageFresco(R.id.base_image, data.getPict_url());
        holder.setText(R.id.base_name, data.getTitle());
        holder.setText(R.id.base_reduce_price, "领劵减" + data.getCoupon_amount() + "元");//优惠劵
        holder.setText(R.id.base_preferential_price, "￥" + couponPrice);//优惠价
        holder.setText(R.id.base_original_price, "￥" + data.getZk_final_price());//原价
        holder.setText(R.id.base_number, "已抢" + data.getVolume() + "件");//已抢数量

        double div = ArithUtil.div(Double.valueOf(SPUtil.getStringValue(CommonResource.BACKBL)), 100, 2);

        holder.setText(R.id.base_estimate, "预估赚"+ArithUtil.mul(mul,div));
        holder.setText(R.id.base_upgrade, "预估赚"+ArithUtil.mul(mul,0.8));
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);

//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }
}
