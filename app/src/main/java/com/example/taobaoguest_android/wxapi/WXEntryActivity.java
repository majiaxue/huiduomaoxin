package com.example.taobaoguest_android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.module_base.ModuleBaseApplication;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean handleIntent = ModuleBaseApplication.wxapi.handleIntent(getIntent(), this);
        //下面代码是判断微信分享后返回WXEnteryActivity的，如果handleIntent==false,说明没有调用IWXAPIEventHandler，则需要在这里销毁这个透明的Activity;
        if (!handleIntent) {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        ModuleBaseApplication.wxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtil.e("onReq...");
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp != null) {
            CommonResource.WX_CODE = ((SendAuth.Resp) baseResp).code; //即为所需的code
        }
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                LogUtil.e("onResp: 成功" + CommonResource.WX_CODE);
                EventBus.getDefault().post(new EventBusBean("WXCODE"));
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                LogUtil.e("onResp: 用户取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                LogUtil.e("onResp: 发送请求被拒绝");
                finish();
                break;
        }
    }

}
