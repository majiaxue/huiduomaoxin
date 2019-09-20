package com.example.payment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.bean.AliPayBean;
import com.example.bean.SubmitOrderBean;
import com.example.bean.WeChatPayBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PopUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;
import com.kongzue.dialog.v3.WaitDialog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import io.reactivex.Observable;

public class PaymentPresenter extends BasePresenter<PaymentView> {
    private String info = "";
    private final int ALI_CODE = 0x123;
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
                    ARouter.getInstance().build("/module_user_store/pay_success")
                            .withSerializable("bean", submitOrderBean)
                            .navigation();
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
        if (isWeChat) {
            final IWXAPI api = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);

            Map map = MapUtil.getInstance().addParms("totalAmout", submitOrderBean.getTotalAmount()).addParms("orderSn", submitOrderBean.getMasterNo()).addParms("productName", "枫林淘客").build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postData(CommonResource.WXPAY, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("微信支付-------------->" + result);
                    try {

                        WeChatPayBean payBean = JSON.parseObject(result, WeChatPayBean.class);

                        PayReq request = new PayReq();
                        request.appId = payBean.getAppid();
                        request.partnerId = payBean.getPartnerid();
                        request.prepayId = payBean.getPrepayid();
                        request.packageValue = "Sign=WXPay";
                        request.nonceStr = payBean.getNoncestr();
                        request.timeStamp = payBean.getTimestamp();
                        request.sign = payBean.getSign();

                        api.sendReq(request);
                        SPUtil.addParm("wxpay", "1");
                        getView().callBack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "------------" + errorMsg);
                }
            }));
        } else {
            Map map = MapUtil.getInstance().addParms("totalAmount", submitOrderBean.getTotalAmount()).addParms("masterNo", submitOrderBean.getMasterNo()).addParms("productName", "枫林淘客").addParms("userCode", SPUtil.getUserCode()).build();
//            ProcessDialogUtil.showProcessDialog(mContext);
            WaitDialog.show((AppCompatActivity)mContext,null);

            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHead(CommonResource.TOPAY, map, SPUtil.getToken());
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

    public void goBack() {
        final SelfDialog dialog = new SelfDialog(mContext);
        dialog.setTitle("提示");
        dialog.setMessage("确定要离开吗？");
        dialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });

        dialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                dialog.dismiss();
                ((Activity) mContext).finish();
            }
        });

        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PopUtil.setTransparency(mContext, 1.0f);
            }
        });
        PopUtil.setTransparency(mContext, 0.3f);
    }

    public void paySuccess() {
        ARouter.getInstance().build("/module_user_store/pay_success")
                .withSerializable("bean", submitOrderBean)
                .navigation();
        ((Activity) mContext).finish();
    }
}
