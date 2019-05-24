package com.example.suggestion;

import android.content.Context;
import android.content.Intent;

import com.example.mvp.BasePresenter;
import com.example.suggestion_history.SuggestionHistoryActivity;

public class SuggestionPresenter extends BasePresenter<SuggestionView> {
    public SuggestionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void jumpToHistory() {
        mContext.startActivity(new Intent(mContext, SuggestionHistoryActivity.class));
    }
}
