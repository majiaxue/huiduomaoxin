package com.example.secondarydetails.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.bean.TBGoodsRecBean;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;


/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryTBRecAdapter extends MyRecyclerAdapter<TBGoodsRecBean.DataBean> {


    public SecondaryTBRecAdapter(Context context, List<TBGoodsRecBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TBGoodsRecBean.DataBean data, int position) {
        // 1表示天猫，0表示淘宝产品
        if (data.getUser_type().equals("0")) {
            //淘宝
            holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.taobao);
        } else {
            //天猫
            holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.tianmao);
        }
        double sub = ArithUtil.sub(Double.valueOf(data.getZk_final_price()), Double.valueOf(data.getCoupon_amount()));
        double div = ArithUtil.div(Double.valueOf(data.getCommission_rate()), 10000, 2);
        double mul = ArithUtil.mul(sub, div);//商品收益需要乘个人收益

        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getPict_url());
        holder.setText(com.example.module_base.R.id.base_name, data.getTitle());
        holder.setText(com.example.module_base.R.id.base_reduce_price, "领劵减" + data.getCoupon_amount() + "元");
        holder.setText(com.example.module_base.R.id.base_preferential_price, "￥" + sub);
        holder.setText(com.example.module_base.R.id.base_original_price, "￥"+data.getZk_final_price());
        holder.setText(com.example.module_base.R.id.base_number, "已抢" + data.getVolume() + "件");
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);

        holder.setText(com.example.module_base.R.id.base_estimate, "预估赚"+ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        holder.setText(com.example.module_base.R.id.base_upgrade, "升级赚"+ArithUtil.mul(mul,0.8));
//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }
}
