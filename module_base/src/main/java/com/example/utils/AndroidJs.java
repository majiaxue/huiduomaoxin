package com.example.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;


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
        if ("搜索".equals(data)) {
            ARouter.getInstance().build("/module_home/SearchActivity").navigation();
        } else if ("消息".equals(data)) {
            ARouter.getInstance().build("/mine/messagecenter").navigation();
        } else if ("头部轮播1".equals(data)) {

        } else if ("淘宝".equals(data)) {
            ARouter.getInstance().build("/module_home/SecondaryDetailsActivity")
                    .withString("type", 0 + "")
                    .navigation();
        } else if ("拼多多".equals(data)) {
            ARouter.getInstance().build("/module_home/SecondaryDetailsActivity")
                    .withString("type", 2 + "")
                    .navigation();
        } else if ("京东".equals(data)) {
            ARouter.getInstance().build("/module_home/SecondaryDetailsActivity")
                    .withString("type", 4 + "")
                    .navigation();
        } else if ("天猫".equals(data)) {
            ARouter.getInstance().build("/module_home/SecondaryDetailsActivity")
                    .withString("type", 6 + "")
                    .navigation();
        } else if ("聚划算".equals(data)) {
            ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 3).navigation();

        } else if ("淘抢购".equals(data)) {
            ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 1).navigation();

        } else if ("附近小店".equals(data)) {
            ARouter.getInstance().build("/module_local/LocalMainActivity").navigation();

        } else if ("9.9包邮".equals(data)) {
            ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 2).navigation();

        } else if ("产品中心".equals(data)) {
            ARouter.getInstance().build("/module_home/ProductCenterActivity").navigation();

        } else if ("会场".equals(data)) {
            ARouter.getInstance().build("/mine/invite_friends").navigation();

        } else if ("四图1".equals(data)) {
            ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 4).withInt("type", 2).navigation();

        } else if ("四图2".equals(data)) {
            ARouter.getInstance().build("/module_home/ShakeStockActivity").navigation();

        } else if ("四图3".equals(data)) {
            ARouter.getInstance().build("/module_home/PunchSignActivity").navigation();

        } else if ("四图4".equals(data)) {
            ARouter.getInstance().build("/module_home/FreeChargeActivity").navigation();

        }
    }

}
