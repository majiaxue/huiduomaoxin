package com.example.local_home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;

import java.util.List;

public class LocalHomeCommendAdapter extends MyRecyclerAdapter<String> {
    public LocalHomeCommendAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {

    }
}
