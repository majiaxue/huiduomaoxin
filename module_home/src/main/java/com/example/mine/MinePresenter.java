package com.example.mine;

import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.mine.adapter.MyToolAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MinePresenter extends BasePresenter<MineView> {


    private MyToolAdapter myToolAdapter;

    public MinePresenter(Context context) {
        super(context);
    }

    public void loadRec() {
        List<BaseRecImageAndTextBean> dataList = new ArrayList();
        dataList.add(new BaseRecImageAndTextBean("我的余额", R.drawable.icon_yu_e));
        dataList.add(new BaseRecImageAndTextBean("我的收藏", R.drawable.shoucang));
        dataList.add(new BaseRecImageAndTextBean("浏览记录", R.drawable.liulanjilu));
        dataList.add(new BaseRecImageAndTextBean("联系客服", R.drawable.kefu_tianchong));
        dataList.add(new BaseRecImageAndTextBean("帮助中心", R.drawable.bangzhuzhongxin));
        dataList.add(new BaseRecImageAndTextBean("邀请好友", R.drawable.invitefri));
        dataList.add(new BaseRecImageAndTextBean("消息通知", R.drawable.xiaoxi));
        dataList.add(new BaseRecImageAndTextBean("意见反馈", R.drawable.icon_yijian));

        myToolAdapter = new MyToolAdapter(mContext, dataList, R.layout.rv_mytool);
        if (getView() != null) {
            getView().loadMyTool(myToolAdapter);
        }

        myToolAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toolClick(index);
                    }
                });
            }
        });
    }

    private void toolClick(int position) {
        switch (position) {
            case 0:
                ARouter.getInstance().build("/mine/balance").navigation();
                break;
            case 1:
                ARouter.getInstance().build("/mine/collection").navigation();
                break;
            case 2:
                ARouter.getInstance().build("/mine/browserecord").navigation();
                break;
            case 3:
                ARouter.getInstance().build("/mine/contactus").navigation();
                break;
            case 4:
                ARouter.getInstance().build("/mine/helpcenter").navigation();
                break;
            case 5:
                ARouter.getInstance().build("/mine/invite_friends").navigation();
                break;
            case 6:
                ARouter.getInstance().build("/mine/messagecenter").navigation();
                break;
            case 7:
                ARouter.getInstance().build("/mine/suggestion").navigation();
                break;
        }
    }

    public void jumpToLogin() {
        if ("".equals(SPUtil.getToken())) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/setting").navigation();
        }
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(mContext);
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

    public void loadData() {
        LogUtil.e("token--->" + SPUtil.getToken());
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi4(mContext).getHeadWithout(CommonResource.GETUSERINFO, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                UserInfoBean userInfoBean = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                }.getType());
                LogUtil.e("个人信息：" + userInfoBean);
                if (getView() != null) {
                    getView().loginSuccess(userInfoBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "---------" + errorMsg);
                SPUtil.addParm(CommonResource.TOKEN, "");
                if ("2".equals(errorCode)) {
                    if (getView() != null) {
                        getView().onError();
                    }
                }
            }
        }));
    }
}
