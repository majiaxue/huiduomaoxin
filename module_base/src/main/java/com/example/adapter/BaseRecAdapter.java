package com.example.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.example.bean.TBGoodsRecBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class BaseRecAdapter extends MyRecyclerAdapter<TBGoodsRecBean.DataBean> {

    private String type;

    public BaseRecAdapter(Context context, List<TBGoodsRecBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public BaseRecAdapter(Context context, List<TBGoodsRecBean.DataBean> mList, int mLayoutId, String type) {
        super(context, mList, mLayoutId);
        this.type = type;
    }


    @Override
    public void convert(RecyclerViewHolder holder, TBGoodsRecBean.DataBean data, final int position) {
        try {

            if (type != null && type.equals("0")) {
                //淘宝
                holder.setImageResource(R.id.base_type, R.drawable.taobao);
            } else if (type != null && type.equals("1")) {
                //拼多多
                holder.setImageResource(R.id.base_type, R.drawable.pinduoduo);
            } else if (type != null && type.equals("2")) {
                //京东
                holder.setImageResource(R.id.base_type, R.drawable.jingdong);
            } else {
                //天猫
                holder.setImageResource(R.id.base_type, R.drawable.tianmao);
            }
            double couponPrice = ArithUtil.sub(Double.valueOf(data.getZk_final_price()), Double.valueOf(data.getCoupon_amount()));//商品价格
            double mul = 0;
            if (data.getCommission_rate() != null) {
                mul = ArithUtil.mul(couponPrice, ArithUtil.div(Double.valueOf(data.getCommission_rate()), 10000, 2));//商品收益需要乘个人收益
            }
            holder.setImageFresco(R.id.base_image, data.getPict_url());
            holder.setText(R.id.base_name, data.getTitle());
            holder.setText(R.id.base_reduce_price, "领劵减" + data.getCoupon_amount() + "元");//优惠劵
            holder.setText(R.id.base_preferential_price, "￥" + couponPrice);//优惠价
            holder.setText(R.id.base_original_price, "￥" + data.getZk_final_price());//原价
            holder.setText(R.id.base_number, "已抢" + data.getVolume() + "件");//已抢数量
            LogUtil.e("商品佣金" + data.getCommission_rate());
            if (!TextUtils.isEmpty(SPUtil.getToken())) {
                holder.setText(R.id.base_estimate, "预估赚" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
            } else {
                holder.setText(R.id.base_estimate, "预估赚" + ArithUtil.mul(mul, 0.3));
            }
            holder.setText(R.id.base_upgrade, "升级赚" + ArithUtil.mul(mul, 0.8));
            // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
            holder.setTextLine(R.id.base_original_price);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        if (viewOnClickListener != null) {
//            viewOnClickListener.ViewOnClick(immediatelyGrab, position);
//        }
    }

}
