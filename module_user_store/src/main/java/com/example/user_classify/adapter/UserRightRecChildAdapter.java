package com.example.user_classify.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.ClassifyBean;
import com.example.entity.RightRecBean;
import com.example.user_store.R;

import java.util.List;

public class UserRightRecChildAdapter extends MyRecyclerAdapter<ClassifyBean.ClassifySecond.ClassifyThird> {
    public UserRightRecChildAdapter(Context context, List<ClassifyBean.ClassifySecond.ClassifyThird> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ClassifyBean.ClassifySecond.ClassifyThird data, int position) {
        holder.setImageUrl(R.id.classify_right_child_simple, data.getIcon())
                .setText(R.id.classify_right_child_name, data.getName());
    }
}
