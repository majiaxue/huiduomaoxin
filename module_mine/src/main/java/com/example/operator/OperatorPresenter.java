package com.example.operator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.example.utils.MapUtil;
import com.example.utils.OnClearCacheListener;
import com.example.utils.SPUtil;
import com.example.utils.UIHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class OperatorPresenter extends BasePresenter<OperatorView> {

    private YysFactorAdapter factorAdapter;
    private List<OperatorBean> beanList;
    private int sort;
    private int clickPosition = 0;

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

                factorAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
                    @Override
                    public void ViewTwoOnClick(View view1, View view2, final int index) {
                        view1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                upJustNow("0", index);
                            }
                        });

                        view2.setOnClickListener(new View.OnClickListener() {
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

    /**
     * @param flag     点击按钮   0:立即升级   1:支付(前端用)
     * @param position
     */
    private void upJustNow(final String flag, final int position) {
        Map map = MapUtil.getInstance().addParms("levelId", beanList.get(position).getId()).addParms("payType", flag).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.UP_JUSTNOW, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("升级运营商：" + result);
                UIHelper.popUpSuccess(mContext, beanList.get(clickPosition), new OnClearCacheListener() {
                    @Override
                    public void setOnClearCache(final PopupWindow pop, View confirm) {
                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pop.dismiss();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("error:" + errorCode + "------------------" + errorMsg);
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
