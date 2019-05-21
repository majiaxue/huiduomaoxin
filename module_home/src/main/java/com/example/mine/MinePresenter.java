package com.example.mine;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.entity.BaseRecImageAndTextBean;
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
        List<BaseRecImageAndTextBean> dataList = new ArrayList();
        dataList.add(new BaseRecImageAndTextBean("我的收藏", R.drawable.shoucang));
        dataList.add(new BaseRecImageAndTextBean("浏览记录", R.drawable.liulanjilu));
        dataList.add(new BaseRecImageAndTextBean("联系客服", R.drawable.kefu_tianchong));
        dataList.add(new BaseRecImageAndTextBean("消息通知", R.drawable.xiaoxi));
        dataList.add(new BaseRecImageAndTextBean("帮助中心", R.drawable.bangzhuzhongxin));
        dataList.add(new BaseRecImageAndTextBean("订单找回", R.drawable.tianchongxing));

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

    public void jumpToOrder(int type) {
        ARouter.getInstance().build("/mine/order").withInt("type", type).navigation();
    }

    public void jumpToUpgrade() {
        ARouter.getInstance().build("/mine/upgrade").navigation();
    }

    public void jumpToFansOrder() {
        ARouter.getInstance().build("/mine/fansorder").navigation();
    }

    public void jumpToGroupFans() {
        ARouter.getInstance().build("/mine/groupfans").navigation();
    }

    public void jumpToupYYS() {
        ARouter.getInstance().build("/mine/operator").navigation();
    }
}
