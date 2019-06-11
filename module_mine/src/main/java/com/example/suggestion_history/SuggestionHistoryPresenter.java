package com.example.suggestion_history;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.FeedBackHistoryBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.suggestion_history.adapter.SuggestionHistoryAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class SuggestionHistoryPresenter extends BasePresenter<SuggestionHistoryView> {
    public SuggestionHistoryPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi4(mContext).getHeadWithout(CommonResource.QUERYSUGGESTION, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("反馈历史：" + result);
                List<FeedBackHistoryBean> dataList = JSON.parseArray(result, FeedBackHistoryBean.class);
                SuggestionHistoryAdapter historyAdapter = new SuggestionHistoryAdapter(mContext, dataList, R.layout.rv_suggestion_history);
                if (getView() != null) {
                    getView().loadRv(historyAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
