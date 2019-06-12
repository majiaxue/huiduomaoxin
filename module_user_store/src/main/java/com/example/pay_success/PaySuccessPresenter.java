package com.example.pay_success;

import android.content.Context;
import android.content.Intent;

import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.UserActivity;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class PaySuccessPresenter extends BasePresenter<PaySuccessView> {
    public PaySuccessPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String masterNo) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi5(mContext).getHeadWithout(CommonResource.PAYSUCCESS + "/" + masterNo, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("支付成功：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void jumpToOrder(String masterNo) {

    }

    public void jumpToHome() {
        mContext.startActivity(new Intent(mContext, UserActivity.class));
    }
}
