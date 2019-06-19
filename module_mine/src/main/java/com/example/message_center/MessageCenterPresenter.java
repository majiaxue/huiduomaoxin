package com.example.message_center;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.example.bean.MessageCenterBean;
import com.example.common.CommonResource;
import com.example.message_center.adapter.MessageCenterAdapter;
import com.example.message_detail.MessageDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MessageCenterPresenter extends BasePresenter<MessageCenterView> {

    private MessageCenterAdapter centerAdapter;
    private List<MessageCenterBean> dataList = new ArrayList<>();

    public MessageCenterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.MESSAGELIST, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("消息列表：" + result);
                if (page == 1) {
                    dataList.clear();
                }
                dataList.addAll(JSON.parseArray(result, MessageCenterBean.class));
                if (centerAdapter == null) {
                    centerAdapter = new MessageCenterAdapter(mContext, dataList);
                } else {
                    centerAdapter.notifyDataSetChanged();
                }
                if (getView() != null) {
                    getView().loadRv(centerAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void jumpToDetail(int position) {
        mContext.startActivity(new Intent(mContext, MessageDetailActivity.class));
    }
}
