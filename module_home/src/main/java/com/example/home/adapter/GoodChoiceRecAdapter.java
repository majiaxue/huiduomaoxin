package com.example.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.home.bean.GoodChoiceBean;
import com.example.module_home.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class GoodChoiceRecAdapter extends MyRecyclerAdapter<GoodChoiceBean> {

    public GoodChoiceRecAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodChoiceBean data, int position) {
        holder.setImageResource(R.id.good_choice_image, data.getImage());
        holder.setText(R.id.good_choice_name, data.getName());
        holder.setText(R.id.good_choice_preferential_price, "￥" + data.getPreferential_price());
        holder.setText(R.id.good_choice_original_price, data.getOriginal_price());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        TextView originalPrice = holder.getView(R.id.good_choice_original_price);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
}
