package com.example.suggestion_history.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.SuggestionBean;
import com.example.module_mine.R;

import java.util.List;

public class SuggestionHistoryAdapter extends MyRecyclerAdapter<SuggestionBean> {
    public SuggestionHistoryAdapter(Context context, List<SuggestionBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, SuggestionBean data, int position) {
        holder.setText(R.id.rv_suggestion_history_feedback_content, data.getFeedBackContent())
                .setText(R.id.rv_suggestion_history_feedback_time, data.getFeedBackTime())
                .setText(R.id.rv_suggestion_history_reply_content, data.getReplyContent())
                .setText(R.id.rv_suggestion_history_reply_time, data.getReplyTime());
    }
}
