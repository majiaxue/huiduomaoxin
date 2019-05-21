package com.example.classificationdetails.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.RecBean;
import com.example.module_classify.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class ClassificationRecAdapter extends MyRecyclerAdapter<RecBean> {


    public ClassificationRecAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RecBean data, final int position) {
        holder.setImageResource(R.id.classification_image, data.getImage());
        holder.setText(R.id.classification_name, data.getName());
        holder.setText(R.id.classification_reduce_price, data.getReduce_price());
        holder.setText(R.id.classification_preferential_price, "￥" + data.getPreferential_price());
        holder.setText(R.id.classification_original_price, data.getOriginal_price());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        TextView originalPrice = holder.getView(R.id.classification_original_price);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        TextView immediatelyGrab = holder.getView(R.id.classification_immediately_grab);
        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }

}
