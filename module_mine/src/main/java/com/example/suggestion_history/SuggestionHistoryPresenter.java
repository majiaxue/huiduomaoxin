package com.example.suggestion_history;

import android.content.Context;

import com.example.entity.SuggestionBean;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.suggestion_history.adapter.SuggestionHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SuggestionHistoryPresenter extends BasePresenter<SuggestionHistoryView> {
    public SuggestionHistoryPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        List<SuggestionBean> dataList = new ArrayList<>();
        dataList.add(new SuggestionBean("无法提现！！", "7月03日 10:35", "系统繁忙，请稍后重试", "7月03日 10:35"));
        dataList.add(new SuggestionBean("无法提现！！", "7月03日 10:35", "系统繁忙，请稍后重试", "7月03日 10:35"));
        dataList.add(new SuggestionBean("无法提现！！", "7月03日 10:35", "系统繁忙，请稍后重试", "7月03日 10:35"));
        dataList.add(new SuggestionBean("无法提现！！", "7月03日 10:35", "系统繁忙，请稍后重试", "7月03日 10:35"));
        dataList.add(new SuggestionBean("无法提现！！", "7月03日 10:35", "系统繁忙，请稍后重试", "7月03日 10:35"));
        dataList.add(new SuggestionBean("无法提现！！", "7月03日 10:35", "系统繁忙，请稍后重试", "7月03日 10:35"));
        SuggestionHistoryAdapter historyAdapter = new SuggestionHistoryAdapter(mContext, dataList, R.layout.rv_suggestion_history);
        if (getView() != null) {
            getView().loadRv(historyAdapter);
        }
    }
}
