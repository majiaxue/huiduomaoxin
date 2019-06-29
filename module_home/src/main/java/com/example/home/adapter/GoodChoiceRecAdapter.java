package com.example.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.GoodChoiceBean;
import com.example.module_home.R;
import com.example.utils.ArithUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class GoodChoiceRecAdapter extends MyRecyclerAdapter<GoodChoiceBean.DataBean> {

    public GoodChoiceRecAdapter(Context context, List<GoodChoiceBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodChoiceBean.DataBean data, int position) {
        holder.setImageFresco(R.id.good_choice_image, data.getPict_url());
        holder.setText(R.id.good_choice_name, data.getTitle());
        holder.setText(R.id.good_choice_preferential_price, "￥" + ArithUtil.sub(Double.valueOf(data.getZk_final_price()),Double.valueOf(data.getCoupon_amount())));
        holder.setText(R.id.good_choice_original_price, data.getZk_final_price());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        TextView originalPrice = holder.getView(R.id.good_choice_original_price);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
}
