package com.example.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.bean.TBGoodsBean;
import com.example.bean.TBGoodsRecBean;
import com.example.entity.BaseRecBean;
import com.example.module_base.R;

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

        holder.setImageFresco(R.id.base_image, data.getPict_url());
        holder.setText(R.id.base_name, data.getTitle());
        holder.setText(R.id.base_reduce_price, "领券减" + data.getCoupon_amount() + "元");//领券减
        holder.setText(R.id.base_preferential_price, "￥" + data.getZk_final_price());
        holder.setText(R.id.base_original_price, data.getReserve_price());
        holder.setText(R.id.base_number, data.getTk_total_sales());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);

        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(immediatelyGrab, position);
        }
    }

}
