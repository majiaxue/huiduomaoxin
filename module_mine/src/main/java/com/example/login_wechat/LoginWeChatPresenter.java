package com.example.login_wechat;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.MapUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LoginWeChatPresenter extends BasePresenter<LoginWeChatView> {
    private boolean isRead = true;

    public LoginWeChatPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void check() {
        if (isRead) {
            getView().noRead();
            isRead = false;
        } else {
            getView().readed();
            isRead = true;
        }
    }

    public void getCodeNum(String string) {
        if (!PhoneNumUtil.isMobileNO(string)) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else {
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi4(mContext).getDataWithout(CommonResource.GETREGISTERCODE + "/" + string);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    getView().getCodeSuccess();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {

                }
            }));

        }
    }

    public void login(UserInfoBean userInfoBean) {
        if (isRead) {
            String jsonString = JSON.toJSONString(userInfoBean);
            Map map = MapUtil.getInstance().addParms("memberStr", jsonString).build();
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi4(mContext).postData(CommonResource.WXLOGIN_PHONE, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    UserInfoBean userInfoBean1 = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                    }.getType());
                    SPUtil.addParm(CommonResource.TOKEN, "JWT " + userInfoBean1.getToken());
                    ARouter.getInstance().build("/home/main").withString("type", "login").navigation();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {

                }
            }));
        } else {
            Toast.makeText(mContext, "请阅读用户协议", Toast.LENGTH_SHORT).show();
        }
    }
}
