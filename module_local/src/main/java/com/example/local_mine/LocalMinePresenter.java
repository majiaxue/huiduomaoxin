package com.example.local_mine;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.RedPackageBean;
import com.example.common.CommonResource;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LocalMinePresenter extends BasePresenter<LocalMineView> {

    private List<RedPackageBean> redPackageBeans;

    public LocalMinePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getRedPackage() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_GET_HONGBAO);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("红包：" + result);
                try {
                    redPackageBeans = JSON.parseArray(result, RedPackageBean.class);
                    if (redPackageBeans != null && redPackageBeans.size() > 0) {
                        for (int i = 0; i < redPackageBeans.size(); i++) {
                            if (i % 2 == 1) {
                                redPackageBeans.get(i).setBackground(R.drawable.redpackage_bg_lan);
                            } else {
                                redPackageBeans.get(i).setBackground(R.drawable.redpackage_bg_zi);
                            }
                        }

                        if (getView() != null) {
                            getView().loadBanner(redPackageBeans);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    public void jumpToWallet() {
        ARouter.getInstance().build("/module_local/CouponWalletActivity").navigation();
    }
}
