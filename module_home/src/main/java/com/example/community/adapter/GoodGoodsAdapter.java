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

public class GoodGoodsAdapter extends MyRecyclerAdapter<String> {
    public GoodGoodsAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img_temp);
        list.add(R.drawable.img_temp);
        list.add(R.drawable.img_temp);

        RecyclerView rv = holder.getView(R.id.rv_good_goods_img);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);

        GoodGoodsInsideAdapter adapter = new GoodGoodsInsideAdapter(context, list, R.layout.rv_goods_commend_inside);
        rv.setAdapter(adapter);

        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_good_goods_copy), position);
        }
    }
}
