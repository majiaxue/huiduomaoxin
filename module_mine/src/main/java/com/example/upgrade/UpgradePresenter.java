package com.example.upgrade;

import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.OperatorBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.upgrade.adapter.UpgradeAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class UpgradePresenter extends BasePresenter<UpgradeView> {

    private List<OperatorBean> beanList;
    private UpgradeAdapter adapter;

    public UpgradePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.IWANTUP, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("我要升级：" + result);
                beanList = JSON.parseArray(result, OperatorBean.class);
                adapter = new UpgradeAdapter(mContext, beanList, R.layout.rv_upgrade);
                if (getView() != null) {
                    getView().loadUI(adapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));


    }


    public void click() {
        adapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toPay(beanList.get(position).getPrice());
                    }
                });
            }
        });
    }

    private void toPay(String money) {
        ARouter.getInstance().build("/module_mine/up_pay")
                .withString("money", money)
                .withString("type", "upgrade")
                .navigation();
    }
}
