package com.example.secondarydetails.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.secondarydetails.bean.SecondaryPddRecBean;

import java.util.List;


/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryPddRecAdapter extends MyRecyclerAdapter<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> {

    private String type;

    public SecondaryPddRecAdapter(Context context, List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> mList, int mLayoutId, String type) {
        super(context, mList, mLayoutId);
        this.type = type;
    }

    @Override
    public void convert(RecyclerViewHolder holder, SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean data, int position) {

        if (type != null && type.equals("1")) {
            //拼多多
            holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.pinduoduo);
        }

        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getGoods_thumbnail_url());
        holder.setText(com.example.module_base.R.id.base_name, data.getGoods_name());
        holder.setText(com.example.module_base.R.id.base_reduce_price, "领劵减50元");
        holder.setText(com.example.module_base.R.id.base_preferential_price, "￥" + Double.valueOf(data.getMin_group_price()) / 100);
        holder.setText(com.example.module_base.R.id.base_original_price, "" + Double.valueOf(data.getMin_normal_price()) / 100);
        holder.setText(com.example.module_base.R.id.base_number, "已抢" + data.getSold_quantity());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(com.example.module_base.R.id.base_original_price);

        TextView immediatelyGrab = holder.getView(com.example.module_base.R.id.base_immediately_grab);
        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }
}
