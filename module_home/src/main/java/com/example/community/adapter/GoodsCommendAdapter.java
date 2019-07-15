package com.example.community.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.CommunityLocalBean;
import com.example.module_home.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsCommendAdapter extends MyRecyclerAdapter<CommunityLocalBean> {
    public GoodsCommendAdapter(Context context, List<CommunityLocalBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CommunityLocalBean data, int position) {
        holder.setText(R.id.rv_goods_commend_name, data.getItemtitle())
                .setText(R.id.rv_goods_commend_content, data.getContent())
                .setText(R.id.rv_goods_commend_profit, data.getCouponmoney());


        List<String> pics = data.getPics();
        RecyclerView rv = holder.getView(R.id.rv_goods_commend_img);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);
        GoodsCommendInsideAdapter adapter = new GoodsCommendInsideAdapter(context, pics, R.layout.rv_goods_commend_inside);
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
