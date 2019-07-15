package com.example.Universallist.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.UniversalListBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class UniversalListRecAdapter extends MyRecyclerAdapter<UniversalListBean.DataBean> {

    public UniversalListRecAdapter(Context context, List<UniversalListBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UniversalListBean.DataBean data, int position) {
        double commissionRate = ArithUtil.div(data.getCommission_rate(), 100, 2);
        double mul = ArithUtil.mul(commissionRate, data.getQuanhoujia());
        holder.setImageFresco(R.id.universal_list_rec_image, data.getPict_url());
        holder.setText(R.id.universal_list_rec_name, data.getTitle());
        holder.setText(R.id.universal_list_rec_price, "￥" + data.getZk_final_price());
        holder.setText(R.id.universal_list_rec_payment_amount, "领劵减" + data.getYouhuiquan() + "元");
        holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        holder.setText(R.id.universal_list_rec_shengjizhuan, "升级赚" + ArithUtil.mul(mul, 0.8));
    }
}
