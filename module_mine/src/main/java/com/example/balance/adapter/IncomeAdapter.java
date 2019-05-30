package com.example.balance.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.MessageListBean;
import com.example.module_mine.R;

import java.util.List;

public class IncomeAdapter extends MyRecyclerAdapter<MessageListBean> {
    private int flag;

    public IncomeAdapter(Context context, List<MessageListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public IncomeAdapter(Context context, List<MessageListBean> mList, int mLayoutId, int flag) {
        super(context, mList, mLayoutId);
        this.flag = flag;
    }

    @Override
    public void convert(RecyclerViewHolder holder, MessageListBean data, int position) {
        holder.setText(R.id.rv_income_title, data.getTitle())
                .setText(R.id.rv_income_time, data.getTime())
                .setText(R.id.rv_income_money, flag == 0 ? "+" + data.getContent() : "-" + data.getContent());
    }
}
