package com.example.mineorder.staydeliverygoods;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:待收货
 */
public class StayDeliveryGoodsPresenter extends BasePresenter<StayDeliveryGoodsView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();

    public StayDeliveryGoodsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void stayDeliveryGoodsRec(final RecyclerView stayDeliveryGoodsRec) {
        Map map = MapUtil.getInstance().addParms("status", 8).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERSTATUS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(final String result, String msg) {
                LogUtil.e("stayDeliveryGoodsResult" + result);
//                MineOrderBean mineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
//                }.getType());
//                LogUtil.e("MineOrderBean" + mineOrderBean.getOrderList());
                MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                if (mineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(mineOrderBean.getOrderList());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    stayDeliveryGoodsRec.setLayoutManager(linearLayoutManager);
                    MineOrderParentAdapter mineOrderParentAdapter = new MineOrderParentAdapter(mContext, listBeans, R.layout.item_mine_order_parent_rec);
                    stayDeliveryGoodsRec.setAdapter(mineOrderParentAdapter);
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
                            //查看物流
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ARouter.getInstance().build("/module_user_mine/LogisticsInformationActivity").navigation();
                                }
                            });
                            //确认收货
                            view3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    mineOrderParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            mContext.startActivity(new Intent(mContext, LogisticsInformationActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

    }
}
