package com.example.assess_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.AssessBean;

import java.util.List;

public class InsideAssessAdapter extends MyRecyclerAdapter<AssessBean> {
    public InsideAssessAdapter(Context context, List<AssessBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AssessBean data, int position) {
//        holder.setImageUrlCircular(R.id.rv_inside_assess_img, data.getImgUrl())
//                .setText(R.id.rv_inside_assess_name, data.getName())
//                .setText(R.id.rv_inside_assess_time, data.getTime())
//                .setText(R.id.rv_inside_assess_zan_count, "(" + data.getInsideZanCount() + ")")
//                .setText(R.id.rv_inside_assess_content, data.getContent());
//        TextView zan = holder.getView(R.id.rv_inside_assess_zan);
//        if (data.isInsideIsZan()) {
//            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_dianzan);
//            drawable.setBounds(0, 0, (int) context.getResources().getDimension(R.dimen.dp_15), (int) context.getResources().getDimension(R.dimen.dp_15));
//            zan.setCompoundDrawables(drawable, null, null, null);
//        } else {
//            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_dianzan1);
//            drawable.setBounds(0, 0, (int) context.getResources().getDimension(R.dimen.dp_15), (int) context.getResources().getDimension(R.dimen.dp_15));
//            zan.setCompoundDrawables(drawable, null, null, null);
//        }
//
//        if (viewOnClickListener != null) {
//            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_inside_assess_zan), position);
//        }
    }
}
