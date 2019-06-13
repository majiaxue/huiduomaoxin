package com.example.mineorder.orderall;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.common.CommonResource;
import com.example.mineorder.adapter.MineOrderChildAdapter;
import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.mineorder.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class OrderAllPresenter extends BasePresenter<OrderAllView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();

    public OrderAllPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void orderAllRec(final RecyclerView orderAllRec) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi4(mContext).getHeadWithout(CommonResource.ORDERALL, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("OrderAllPresenterResult-------->" + result);
                MineOrderBean MineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
                }.getType());
                LogUtil.e("OrderAllPresenterMineOrderBean-------->" + MineOrderBean.getOrderList());
                listBeans.clear();
                listBeans.addAll(MineOrderBean.getOrderList());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                orderAllRec.setLayoutManager(linearLayoutManager);
                MineOrderParentAdapter mineOrderParentAdapter = new MineOrderParentAdapter(mContext, listBeans, R.layout.item_mine_order_parent_rec);
                orderAllRec.setAdapter(mineOrderParentAdapter);

                mineOrderParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });

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
                                ARouter.getInstance().build("/module_user_mine/RefundActivity").navigation();
                            }
                        });
                        //发货
                        view3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("OrderAllPresenterError-------->" + errorMsg);
            }
        }));

    }
}
