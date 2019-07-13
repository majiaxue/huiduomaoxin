package com.example.community.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsCommendAdapter extends MyRecyclerAdapter<String> {
    public GoodsCommendAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img_temp);
        list.add(R.drawable.img_temp);
        list.add(R.drawable.img_temp);

        RecyclerView rv = holder.getView(R.id.rv_goods_commend_img);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);
        GoodsCommendInsideAdapter adapter = new GoodsCommendInsideAdapter(context, list, R.layout.rv_goods_commend_inside);
        rv.setAdapter(adapter);

        adapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {

            }
        });

        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_goods_commend_share), position);
        }
    }
}
