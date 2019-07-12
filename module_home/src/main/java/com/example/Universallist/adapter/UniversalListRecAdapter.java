package com.example.Universallist.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;

import java.util.List;

public class UniversalListRecAdapter extends MyRecyclerAdapter<String> {

    public UniversalListRecAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {

    }
}
