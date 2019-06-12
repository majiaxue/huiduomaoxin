package com.example.commoditydetails.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.commoditydetails.bean.CommodityDetailsPddRecBean;
import com.example.module_classify.R;
import com.example.utils.ArithUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class CommodityDetailsPddRecAdapter extends MyRecyclerAdapter<CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean> {

    public CommodityDetailsPddRecAdapter(Context context, List<CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean data, int position) {
        //拼多多
        holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.pinduoduo);

        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getGoods_thumbnail_url());
        holder.setText(com.example.module_base.R.id.base_name, data.getGoods_name());
        holder.setText(R.id.base_reduce_price, "领劵减" + ArithUtil.div(Double.valueOf(data.getCoupon_discount()), 100, 1) + "元");
        holder.setText(R.id.base_preferential_price, "￥" + ArithUtil.div(Double.valueOf(data.getMin_group_price()) - Double.valueOf(data.getCoupon_discount()), 100, 1));
        holder.setText(R.id.base_original_price, "" + ArithUtil.div(Double.valueOf(data.getMin_group_price()),100,1));
        holder.setText(R.id.base_number, "已抢" + data.getSold_quantity());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);

        TextView immediatelyGrab = holder.getView(com.example.module_base.R.id.base_immediately_grab);
        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }
}
