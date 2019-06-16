package com.example.payment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.bean.AliPayBean;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.pay_success.PaySuccessActivity;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;

public class PaymentPresenter extends BasePresenter<PaymentView> {
    private String info = "";
    private final int ALI_CODE = 0x123;
    private final String WX_APPID = "wxf08fd2965ac9ac30";
    private SubmitOrderBean submitOrderBean;

    public PaymentPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == ALI_CODE) {
                Map<String, String> map = (Map<String, String>) msg.obj;
                String resultStatus = map.get("resultStatus");
                String result = map.get("result");
                String memo = map.get("memo");
                if ("9000".equals(resultStatus)) {
                    Intent intent = new Intent(mContext, PaySuccessActivity.class);
                    intent.putExtra("bean", submitOrderBean);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };

    public void pay(boolean isWeChat, SubmitOrderBean submitOrderBean) {
        this.submitOrderBean = submitOrderBean;
        Map map = MapUtil.getInstance().addParms("totalAmount", "0.01").addParms("masterNo", submitOrderBean.getMasterNo()).addParms("productName", "枫林淘客").build();
        if (isWeChat) {
            Toast.makeText(mContext, "开发中...", Toast.LENGTH_SHORT).show();
        } else {
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postHead(CommonResource.TOPAY, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("支付宝：" + result);
                    AliPayBean aliPayBean = JSON.parseObject(result, AliPayBean.class);
                    info = aliPayBean.getBody();
                    Thread thread = new Thread(payRunnable);
                    thread.start();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {

                }
            }));

        }
    }

    private Runnable payRunnable = new Runnable() {

        @Override
        public void run() {
            PayTask alipay = new PayTask((Activity) mContext);
            Map<String, String> result = alipay.payV2(info, true);

            Message msg = new Message();
            msg.what = ALI_CODE;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    };
}
