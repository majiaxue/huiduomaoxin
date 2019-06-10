package com.example.update_password;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;

public class UpdatePasswordPresenter extends BasePresenter<UpdatePasswordView> {
    public UpdatePasswordPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(String old, String first, String second, UserInfoBean bean) {
        if ("".equals(first) || "".equals(second) || "".equals(old)) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.password_cannot_empty), Toast.LENGTH_SHORT).show();
        } else if (!first.equals(second)) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.password_no_same), Toast.LENGTH_SHORT).show();
        } else {
            bean.setOldPassword(old);
            bean.setPassword(first);
            bean.setNewPassword(second);
            String s = JSON.toJSONString(bean);
            Map map = MapUtil.getInstance().addParms("memberStr", s).build();
            Observable observable = RetrofitUtil.getInstance().getApi4(mContext).postHead(CommonResource.REVISEPASSWORD, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("修改密码：" + result);
                    Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "------" + errorMsg);
                }
            }));
        }
    }
}
