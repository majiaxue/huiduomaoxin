package com.example.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HomePredictBean;
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
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class MinePresenter extends BasePresenter<MineView> {

    private int temp = 0;
    private MyToolAdapter myToolAdapter;

    public MinePresenter(Context context) {
        super(context);
    }

    public void loadRec() {
        List<BaseRecImageAndTextBean> dataList = new ArrayList();
        dataList.add(new BaseRecImageAndTextBean("邀请好友", R.drawable.invitefri));
        dataList.add(new BaseRecImageAndTextBean("浏览记录", R.drawable.liulanjilu));
        dataList.add(new BaseRecImageAndTextBean("我的收藏", R.drawable.shoucang));
        dataList.add(new BaseRecImageAndTextBean("联系客服", R.drawable.kefu_tianchong));
        dataList.add(new BaseRecImageAndTextBean("帮助中心", R.drawable.bangzhuzhongxin));
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
                if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
                    ARouter.getInstance().build("/mine/login").navigation();
                } else {
                    ARouter.getInstance().build("/mine/invite_friends").navigation();
                }
                break;
            case 1:
                if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
                    ARouter.getInstance().build("/mine/login").navigation();
                } else {
                    ARouter.getInstance().build("/mine/browserecord").navigation();
                }

                break;
            case 2:
                if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
                    ARouter.getInstance().build("/mine/login").navigation();
                } else {
                    ARouter.getInstance().build("/mine/collection").navigation();
                }
                break;
            case 3:
                ARouter.getInstance().build("/mine/contactus").navigation();
                break;
            case 4:
                ARouter.getInstance().build("/mine/helpcenter").navigation();
                break;
            case 5:
                if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
                    ARouter.getInstance().build("/mine/login").navigation();
                } else {
                    ARouter.getInstance().build("/mine/messagecenter").navigation();
                }
                break;
            case 6:
                if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
                    ARouter.getInstance().build("/mine/login").navigation();
                } else {
                    ARouter.getInstance().build("/mine/suggestion").navigation();
                }
                break;
            default:
                break;
        }
    }

    public void jumpToBalance() {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/balance").navigation();
        }
    }

    public void jumpToLogin() {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
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
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/setting").navigation();
        }
    }

    public void jumpToPredict() {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/predict").navigation();
        }
    }

    public void jumpToOrder(int type) {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/order").withInt("type", type).navigation();
        }
    }

    public void jumpToUpgrade() {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/upgrade").navigation();
        }
    }

    public void jumpToFansOrder() {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/fansorder").navigation();
        }
    }

    public void jumpToGroupFans() {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/groupfans").navigation();
        }
    }

    public void jumpToupYYS() {
        if ("".equals(SPUtil.getToken()) || SPUtil.getToken() == null) {
            ARouter.getInstance().build("/mine/login").navigation();
        } else {
            ARouter.getInstance().build("/mine/operator").navigation();
        }
    }

    public void loadData() {
        LogUtil.e("token--->" + SPUtil.getToken());
        ProcessDialogUtil.showProcessDialog(mContext);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GETUSERINFO, SPUtil.getToken());//"http://192.168.1.9:4001"
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                ProcessDialogUtil.dismissDialog();
                UserInfoBean userInfoBean = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                }.getType());
                SPUtil.addParm("head", userInfoBean.getIcon());
                SPUtil.addParm("name", userInfoBean.getNickname());
                SPUtil.addParm(CommonResource.LEVELID, userInfoBean.getLevelId());
                if (temp == 0) {
                    getBackBili();
                }
                getPredict();
                temp += 1;
                LogUtil.e("个人信息：" + result);
                if (getView() != null) {
                    getView().loginSuccess(userInfoBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("个人信息" + errorCode + "---------" + errorMsg);
                SPUtil.addParm(CommonResource.TOKEN, "");
//                if ("2".equals(errorCode)) {
                if (getView() != null) {
                    getView().onError();
                }
//                }
            }
        }));
    }

    private void getBackBili() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.QUERY_BILI + "/" + SPUtil.getUserCode());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("返佣比例：" + result);
                Float valueOf = Float.valueOf(result) / 100;
                SPUtil.addParm(CommonResource.BACKBL, valueOf);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void setClipboard(String content) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
    }

    public void getPredict() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.HOME_PREDICT, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("预估：" + result);
                HomePredictBean homePredictBean = JSON.parseObject(result, HomePredictBean.class);
                if (getView() != null) {
                    getView().loadPredict(homePredictBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("~~~~~" + errorMsg);
            }
        }));
    }

    public void jumpToPoints() {
        if (SPUtil.getToken() != null && !"".equals(SPUtil.getToken())) {
            ARouter.getInstance().build("/mine/points").navigation();
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }
}
