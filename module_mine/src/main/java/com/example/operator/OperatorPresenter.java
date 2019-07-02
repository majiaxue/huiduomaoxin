package com.example.operator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.OperatorBean;
import com.example.common.CommonResource;
import com.example.invite_friends.InviteFriendsActivity;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.operator.adapter.YysFactorAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class OperatorPresenter extends BasePresenter<OperatorView> {

    private YysFactorAdapter factorAdapter;
    private List<OperatorBean> beanList;
    private int sort;

    public OperatorPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.GETOPER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("运营商：" + result);
                beanList = JSON.parseArray(result, OperatorBean.class);
                factorAdapter = new YysFactorAdapter(mContext, beanList, R.layout.rv_yys_factor);
                factorAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                judge();
                                if (sort >= beanList.get(index).getSort()) {
                                    Toast.makeText(mContext, "请勿重复升级", Toast.LENGTH_SHORT).show();
                                } else {
                                    ARouter.getInstance().build("/module_mine/up_pay")
                                            .withString("money", beanList.get(index).getPrice())
                                            .withString("type", "operator")
                                            .navigation();
                                }
                            }
                        });
                    }
                });

                if (getView() != null) {
                    getView().loadFactor(factorAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void inviteFans() {
        mContext.startActivity(new Intent(mContext, InviteFriendsActivity.class));
    }

    private void judge() {
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).getId().equals(SPUtil.getStringValue(CommonResource.LEVELID))) {
                sort = beanList.get(i).getSort();
                break;
            }
        }
    }

}
