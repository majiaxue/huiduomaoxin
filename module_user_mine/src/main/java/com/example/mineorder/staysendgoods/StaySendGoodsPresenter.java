package com.example.mineorder.staysendgoods;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BaseRefundBean;
import com.example.common.CommonResource;
import com.example.logisticsinformation.LogisticsInformationActivity;
import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.mineorder.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:待发货
 */
public class StaySendGoodsPresenter extends BasePresenter<StaySendGoodsView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();

    public StaySendGoodsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void staySendGoodsRec(final RecyclerView staySendGoodsRec) {
        Map map = MapUtil.getInstance().addParms("status", 1).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERSTATUS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("StaySendGoodsResult-------->" + result);
//                MineOrderBean MineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
//                }.getType());
                final MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                if (mineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(mineOrderBean.getOrderList());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    staySendGoodsRec.setLayoutManager(linearLayoutManager);
                    MineOrderParentAdapter mineOrderParentAdapter = new MineOrderParentAdapter(mContext, listBeans, R.layout.item_mine_order_parent_rec);
                    staySendGoodsRec.setAdapter(mineOrderParentAdapter);
                    mineOrderParentAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
                        @Override
                        public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                            //去店铺
                            view1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                                }
                            });
                            //申请退款
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    ARouter.getInstance()
                                            .build("/module_user_mine/RefundActivity")
                                            .withSerializable("mineOrderBean", mineOrderBean)
                                            .withString("type", "1")
                                            .withInt("position", position)
                                            .navigation();
                                }
                            });
                            //提醒发货
                            view3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(mContext, "已提醒商家发货!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    mineOrderParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_user_mine/OrderDetailsActivity")
                                    .withString("orderSn", listBeans.get(0).getOrderItems().get(position).getOrderSn())
                                    .navigation();
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("StaySendGoodsErrorMsg-------->" + errorMsg);
            }
        }));
    }
}
