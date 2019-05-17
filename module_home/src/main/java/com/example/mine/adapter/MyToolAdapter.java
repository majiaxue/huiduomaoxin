package com.example.mine.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;
import com.example.utils.LogUtil;

import java.util.List;

public class MyToolAdapter extends MyRecyclerAdapter<String> {
    public MyToolAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setText(R.id.rv_mytool_txt, data);
    }
}
