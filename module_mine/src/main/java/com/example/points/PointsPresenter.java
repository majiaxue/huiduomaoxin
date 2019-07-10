package com.example.points;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.bean.MyPointsBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class PointsPresenter extends BasePresenter<PointsView> {

    private MyPointsBean pointsBean;

    public PointsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getData() {
        ProcessDialogUtil.showProcessDialog(mContext);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GETPOINTS, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("积分：" + result);
                try {
                    pointsBean = JSON.parseObject(result, MyPointsBean.class);
                    if (getView() != null) {
                        getView().loadData(pointsBean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-------------" + errorMsg);
            }
        }));
    }

    public void commit(MyPointsBean bean, String string) {
        if (bean == null || bean.getMember().getAliAccount() == null) {
            Toast.makeText(mContext, "请添加支付宝账号", Toast.LENGTH_SHORT).show();
        } else if (string == null || "".equals(string)) {
            Toast.makeText(mContext, "请输入要提现的积分数量", Toast.LENGTH_SHORT).show();
        } else {
            Map map = MapUtil.getInstance().addParms("account", bean.getMember().getAliAccount()).addParms("integration", string).addParms("name", bean.getMember().getRealName()).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.POINTS_CRASH, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("积分提现：" + result);
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "------------" + errorMsg);
                }
            }));
        }
    }
}
