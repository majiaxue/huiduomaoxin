package com.example.cashout;

import android.content.Context;

import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class CashoutPresenter extends BasePresenter<CashoutView> {
    public CashoutPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GETBALANCE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("余额：" + result);
                if (getView() != null) {
                    if (result == null || "".equals(result)) {
                        getView().loadBalance("0");
                    } else {
                        getView().loadBalance(result);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void tixian(String money) {

    }
}
