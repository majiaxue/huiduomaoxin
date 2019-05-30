package com.example.user_classify.adapter;

import android.content.Context;
import android.graphics.Color;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.LeftGroupBean;
import com.example.user_store.R;

import java.util.List;

public class UserLeftRvAdapter extends MyRecyclerAdapter<LeftGroupBean> {
    public UserLeftRvAdapter(Context context, List<LeftGroupBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LeftGroupBean data, int position) {
        holder.setText(R.id.rv_left_classify_txt, data.getName())
                .setBackgroundColor(R.id.rv_left_classify_txt, Color.parseColor(data.isSelected() ? "#ffffff" : "#fafafa"))
                .setTextColor(R.id.rv_left_classify_txt, Color.parseColor(data.isSelected() ? "#fd3c15" : "#666666"));
    }
}
