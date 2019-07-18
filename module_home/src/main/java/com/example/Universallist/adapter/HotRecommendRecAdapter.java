package com.example.Universallist.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.HotRecommendBean;
import com.example.bean.UniversalListBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class HotRecommendRecAdapter extends MyRecyclerAdapter<HotRecommendBean.DataBean> {

    public HotRecommendRecAdapter(Context context, List<HotRecommendBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, HotRecommendBean.DataBean data, int position) {
        double commissionRate = ArithUtil.div(Double.valueOf(data.getTkrates()), 100, 2);
        double mul = ArithUtil.mul(commissionRate, Double.valueOf(data.getItemendprice()));
        holder.setImageFresco(R.id.universal_list_rec_image, data.getItempic());
        holder.setText(R.id.universal_list_rec_name, data.getItemtitle());
        holder.setText(R.id.universal_list_rec_price, "￥" + data.getItemprice());
        holder.setText(R.id.universal_list_rec_payment_amount, "领劵减" + data.getCouponmoney() + "元");
        holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        holder.setText(R.id.universal_list_rec_shengjizhuan, "升级赚" + ArithUtil.mul(mul, 0.8));
    }
}
