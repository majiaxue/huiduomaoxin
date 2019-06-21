package com.example.upgrade.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.UpgradeBean;
import com.example.module_mine.R;

import java.util.ArrayList;
import java.util.List;

public class UpgradeAdapter extends MyRecyclerAdapter<UpgradeBean> {
    public UpgradeAdapter(Context context, List<UpgradeBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UpgradeBean data, int position) {
        String txt1 = "收费金额达到：<font color='#e20707'>" + data.getShoufei() + "</font> 元";
        String txt2 = "自购订单达到：<font color='#e20707'>" + data.getZigou() + "</font> 单";
        String txt3 = "邀请粉丝达到：<font color='#e20707'>" + data.getYaoqing() + "</font> 人";
        String txt4 = "预估佣金达到：<font color='#e20707'>" + data.getYugu() + "</font> 元";
        List list = new ArrayList();
        list.add(txt1);
        list.add(txt2);
        list.add(txt3);
        list.add(txt4);
        holder.setText(R.id.rv_upgrade_title, data.getTitle())
                .setText(R.id.rv_upgrade_description, data.getDescription());
        RecyclerView rv = holder.getView(R.id.rv_upgrade_rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new UpgradeInsideAdapter(context, list, R.layout.rv_upgrade_inside_rv));

        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_upgrade_btn), position);
        }
    }
}
