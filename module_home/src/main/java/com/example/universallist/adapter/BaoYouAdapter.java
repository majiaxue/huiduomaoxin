package com.example.universallist.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TBGoodsRecBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class BaoYouAdapter extends MyRecyclerAdapter<TBGoodsRecBean.DataBean> {

    public BaoYouAdapter(Context context, List<TBGoodsRecBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TBGoodsRecBean.DataBean data, int position) {
        double commissionRate = Double.valueOf(data.getCommission_rate()) / 10000;
        double mul = commissionRate * (Double.valueOf(data.getZk_final_price()) - Double.valueOf(data.getCoupon_amount())) * 0.9;
        holder.setImageFresco(R.id.universal_list_rec_image, data.getPict_url());
        holder.setText(R.id.universal_list_rec_name, data.getTitle());
        holder.setText(R.id.universal_list_rec_price, "￥" + data.getZk_final_price());
        holder.setText(R.id.universal_list_rec_payment_amount, "领劵减" + data.getCoupon_amount() + "元");
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        } else {
            holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, 0.3));
        }

        holder.setText(R.id.universal_list_rec_shengjizhuan, "升级赚" + ArithUtil.mulRound(mul, 0.8));
    }
}
