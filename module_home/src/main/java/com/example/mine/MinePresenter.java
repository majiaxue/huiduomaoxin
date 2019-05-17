package com.example.mine;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mine.adapter.MyToolAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class MinePresenter extends BasePresenter<MineView> {


    public MinePresenter(Context context) {
        super(context);
    }

    public void loadRec() {
        List dataList = new ArrayList();
        dataList.add("我的收藏");
        dataList.add("浏览记录");
        dataList.add("联系客服");
        dataList.add("消息通知");
        dataList.add("帮助中心");
        dataList.add("订单找回");

        MyToolAdapter myToolAdapter = new MyToolAdapter(mContext, dataList, R.layout.rv_mytool);
        if (getView() != null) {
            getView().loadMyTool(myToolAdapter);
        }
    }

    public void jumpToLogin() {
        ARouter.getInstance().build("/mine/login").navigation();
    }

    @Override
    protected void onViewDestroy() {

    }


    public void jumpToSetting() {
        ARouter.getInstance().build("/mine/setting").navigation();
    }

    public void jumpToPredict() {
        ARouter.getInstance().build("/mine/predict").navigation();
    }
}
