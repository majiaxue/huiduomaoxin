package com.example.taobaoguest_android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.common.CommonResource;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, CommonResource.WXAPPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                String wxpay = SPUtil.getStringValue("wxpay");
                if ("1".equals(wxpay)) {
                    SPUtil.addParm("wxpay", "0");

                } else if ("2".equals(wxpay)) {
                    SPUtil.addParm("wxpay", "3");
                }

                finish();
            } else {
                Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
