package com.example.up_pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;

public class UpPayPresenter extends BasePresenter<UpPayView> {
    private final int ALI_CODE = 0x123;
    private String type;
    private String info;

    public UpPayPresenter(Context context) {
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
                    SubmitOrderBean submitOrderBean = new SubmitOrderBean();
                    submitOrderBean.setProductName(type);
                    ARouter.getInstance().build("/module_user_store/pay_success").withSerializable("bean", submitOrderBean).navigation();
                    ((Activity) mContext).finish();
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };

    public void pay(boolean isWeChat, String money, String type) {
        this.type = type;
        if (isWeChat) {
            Toast.makeText(mContext, "开发中...", Toast.LENGTH_SHORT).show();
        } else {
            Map map = MapUtil.getInstance().addParms("userCode", SPUtil.getUserCode()).addParms("totalAmount", money).addParms("levelId", SPUtil.getStringValue(CommonResource.LEVELID)).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHead(CommonResource.UP_PAY, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("付款：" + result);
                    Map parseObject = JSON.parseObject(result, Map.class);
                    info = (String) parseObject.get("body");
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
