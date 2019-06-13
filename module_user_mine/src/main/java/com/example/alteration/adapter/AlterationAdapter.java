package com.example.alteration.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.alteration.bean.AlterationBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class AlterationAdapter extends MyRecyclerAdapter<AlterationBean> {

    public AlterationAdapter(Context context, List<AlterationBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AlterationBean data, int position) {
        holder.setText(R.id.alteration_rec_shop_name, data.getSellerName());
        holder.setImageFresco(R.id.alteration_rec_image, data.getProductPic());
        holder.setText(R.id.alteration_rec_name, data.getProductName());
        holder.setText(R.id.alteration_rec_count, "X" + data.getProductCount());
        holder.setText(R.id.alteration_rec_Attr, data.getProductAttr());
//        holder.setText(R.id.alteration_rec_size, data.getSize());
        if (data.getReturnType().equals("0")) {
            //退货退款
            holder.setText(R.id.alteration_rec_type, "退货退款");
        } else if (data.getReturnType().equals("1")) {
            //未收货
            holder.setText(R.id.alteration_rec_type, "未收货");
        } else {
            //只退款
            holder.setText(R.id.alteration_rec_type, "只退款");
        }
        //申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
        if (data.getStatus() == 0) {
            holder.setText(R.id.alteration_rec_status, "等待商家处理");
        } else if (data.getStatus() == 1) {
            holder.setText(R.id.alteration_rec_status, "处理中");
        } else if (data.getStatus() == 2) {
            holder.setText(R.id.alteration_rec_status, "退款成功");
        } else {
            holder.setText(R.id.alteration_rec_status, "商家已拒绝");
        }

        viewOnClickListener.ViewOnClick(holder.getView(R.id.alteration_rec_view_details), position);
    }
}
