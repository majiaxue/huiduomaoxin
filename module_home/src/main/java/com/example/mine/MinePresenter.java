package com.example.mine;

import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.mine.adapter.MyToolAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class MinePresenter extends BasePresenter<MineView> {


    private MyToolAdapter myToolAdapter;

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

        myToolAdapter = new MyToolAdapter(mContext, dataList, R.layout.rv_mytool);
        if (getView() != null) {
            getView().loadMyTool(myToolAdapter);
        }

        myToolAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int position) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toolClick(position);
                    }
                });
            }
        });
    }

    private void toolClick(int position) {
        switch (position) {
            case 0:
                ARouter.getInstance().build("/mine/collection").navigation();
                break;
            case 1:

                break;
            case 2:
                ARouter.getInstance().build("/mine/contactus").navigation();
                break;
            case 3:
                ARouter.getInstance().build("/mine/messagecenter").navigation();
                break;
            case 4:
                ARouter.getInstance().build("/mine/helpcenter").navigation();
                break;
            case 5:
                ARouter.getInstance().build("/mine/orderretrieve").navigation();
                break;
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
