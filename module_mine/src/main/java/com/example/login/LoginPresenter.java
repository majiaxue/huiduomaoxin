package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.code_login.CodeLoginActivity;
import com.example.common.CommonResource;
import com.example.forget.ForgetActivity;
import com.example.module_base.ModuleBaseApplication;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.register.RegisterActivity;
import com.example.utils.LogUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(mContext);
    }

    public boolean inputIsEmpty(String name, String password) {
        if (null == name || null == password || "".equals(name) || "".equals(password)) {
            return true;
        } else {
            return false;
        }

    }

    public void showWeiXin() {
        getView().showWeiXin();
    }

    public void hideWeiXin() {
        getView().hideWeiXin();
    }

    public void toRegister() {
        mContext.startActivity(new Intent(mContext, RegisterActivity.class));
    }

    public void toForget() {
        mContext.startActivity(new Intent(mContext, ForgetActivity.class));
    }

    public void toCodeLogin() {
        mContext.startActivity(new Intent(mContext, CodeLoginActivity.class));
    }

    public void WeChatLogin() {
        if (ModuleBaseApplication.wxapi == null) {
            ModuleBaseApplication.wxapi = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);
        }
        if (!ModuleBaseApplication.wxapi.isWXAppInstalled()){
            Toast.makeText(mContext, "请先安装微信客户端", Toast.LENGTH_SHORT).show();
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        ModuleBaseApplication.wxapi.sendReq(req);
    }

    public void sendCode() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi4(mContext).sendCode(CommonResource.WXLOGIN_CODE, CommonResource.WX_CODE);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("weixin:" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("code:" + errorCode + "msg:" + errorMsg);
            }
        }));
    }
}
