package com.example.suggestion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.suggestion_history.SuggestionHistoryActivity;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;

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

    public void submit(String content) {
        Map map = MapUtil.getInstance().addParms("message", content).build();
        Observable observable = RetrofitUtil.getInstance().getApi4(mContext).postHead(CommonResource.SUGGESSTION, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("提交意见：" + result);
                Toast.makeText(mContext, "反馈成功", Toast.LENGTH_SHORT).show();
                ((Activity) mContext).finish();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
