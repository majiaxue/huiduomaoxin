package com.example.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.example.bean.CommodityRecommendBean;
import com.example.module_shoppingmall.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class CommodityRecommendRecAdapter extends MyRecyclerAdapter<CommodityRecommendBean> {

    private OnGetViewClickListener onGetViewClickListener;

    public CommodityRecommendRecAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CommodityRecommendBean data, final int position) {
        holder.setImageResource(R.id.commodity_recommend_image, data.getImage());
        holder.setText(R.id.commodity_recommend_name, data.getName());
        holder.setText(R.id.commodity_recommend_reduce_price, data.getReduce_price());
        holder.setText(R.id.commodity_recommend_preferential_price, data.getPreferential_price());
        holder.setText(R.id.commodity_recommend_original_price, data.getOriginal_price());
        holder.setText(R.id.commodity_recommend_number, data.getNumber());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        TextView originalPrice = holder.getView(R.id.commodity_recommend_original_price);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


        holder.getView(R.id.commodity_recommend_immediately_grab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetViewClickListener.onItemClick(position);
            }
        });
    }


    public interface OnGetViewClickListener {
        void onItemClick(int position);
    }

    public void setOnGetViewClickListener(OnGetViewClickListener onGetViewClickListener) {
        this.onGetViewClickListener = onGetViewClickListener;
    }

}
