package com.example.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.UserInfoBean;
import com.example.code_login.CodeLoginActivity;
import com.example.common.CommonResource;
import com.example.forget.ForgetActivity;
import com.example.login_wechat.LoginWeChatActivity;
import com.example.module_base.ModuleBaseApplication;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.register.RegisterActivity;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

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
        SPUtil.addParm("weixin", "code");
        if (ModuleBaseApplication.wxapi == null) {
            ModuleBaseApplication.wxapi = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);
        }
        if (!ModuleBaseApplication.wxapi.isWXAppInstalled()) {
            Toast.makeText(mContext, "请先安装微信客户端", Toast.LENGTH_SHORT).show();
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        ModuleBaseApplication.wxapi.sendReq(req);
    }

    public void sendCode() {
        String wx_code = SPUtil.getStringValue("wx_code");
        Map map = MapUtil.getInstance().addParms("code", wx_code).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.URL_27_4001).getData(CommonResource.WXLOGIN_CODE, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                UserInfoBean userInfoBean = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                }.getType());
                LogUtil.e("denglu:"+result);
                SPUtil.addParm(CommonResource.USERCODE, userInfoBean.getUserCode());
                if (userInfoBean.getPhone() == null || "".equals(userInfoBean.getPhone())) {
                    Intent intent = new Intent(mContext, LoginWeChatActivity.class);
                    intent.putExtra("bean", userInfoBean);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                } else {
                    SPUtil.addParm(CommonResource.TOKEN, "JWT " + userInfoBean.getToken());
                    ARouter.getInstance().build("/home/main").withString("type", "login").navigation();
                    ((Activity) mContext).finish();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void login(String phone, String password) {
        if (!PhoneNumUtil.isMobileNO(phone)) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if ("".equals(password) || password == null) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            Map map = MapUtil.getInstance().addParms("phone", phone).addParms("password", password).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.URL_27_4001).postData(CommonResource.LOGIN_PHONE, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    UserInfoBean userInfoBean = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                    }.getType());
                    LogUtil.e("登录：" + userInfoBean);
                    SPUtil.addParm(CommonResource.TOKEN, "JWT " + userInfoBean.getToken());
                    SPUtil.addParm(CommonResource.USERCODE, userInfoBean.getUserCode());
                    ARouter.getInstance().build("/home/main").withString("type", "login").navigation();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {

                }
            }));
        }
    }
}
