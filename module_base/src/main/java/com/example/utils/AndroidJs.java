package com.example.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


public class AndroidJs extends Object {
    private Context mContext;

    public AndroidJs(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void showToast() {
        LogUtil.e("我成功了showToast");
        Toast.makeText(mContext, "成功了showToast", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void jumpPage(String data) {
        LogUtil.e("我成功了jumpPage");
        Toast.makeText(mContext, "成功了jumpPage" + data, Toast.LENGTH_SHORT).show();
    }

}
