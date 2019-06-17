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
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
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
 * Describe:全部订单
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
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.ORDERALL, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {

            private MineOrderParentAdapter mineOrderParentAdapter;

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("OrderAllPresenterResult-------->" + result);
//                MineOrderBean mineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
//                }.getType());
                MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                LogUtil.e("MineOrderBean1" + mineOrderBean);
                if (mineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(mineOrderBean.getOrderList());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    orderAllRec.setLayoutManager(linearLayoutManager);
                    mineOrderParentAdapter = new MineOrderParentAdapter(mContext, listBeans, R.layout.item_mine_order_parent_rec);
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
                            int status = listBeans.get(position).getStatus();
                            if (status == 1) {
                                //1待发货
                                //申请退款
                                view2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(mContext, "申请退款", Toast.LENGTH_SHORT).show();
                                        ARouter.getInstance().build("/module_user_mine/RefundActivity").navigation();
                                    }
                                });
                                //提醒发货
                                view3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(mContext, "已提醒商家发货!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (status == 3) {
                                //3待评论
                                //再次购买
                                view2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(mContext, "再次购买", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                //立即评价
                                view3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(mContext, "立即评价", Toast.LENGTH_SHORT).show();
                                        ARouter.getInstance().build("/module_user_mine/OrderAssessActivity").navigation();
                                    }
                                });
                            } else if (status == 6) {
                                //6待付款
                                //删除订单
                                view2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Map build = MapUtil.getInstance().addParms("orderId", listBeans.get(position).getOrderId()).build();
                                        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERREMOVE, build, SPUtil.getToken());
                                        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
                                            @Override
                                            public void onSuccess(String result, String msg) {
                                                if ("true".equals(result)) {
                                                    listBeans.remove(position);
                                                    mineOrderParentAdapter.notifyDataSetChanged();
                                                }
                                            }

                                            @Override
                                            public void onError(String errorCode, String errorMsg) {
                                                LogUtil.e("删除errorMsg---------->" + errorMsg);
                                            }
                                        }));
                                    }
                                });
                                //付款
                                view3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        SubmitOrderBean submitOrderBean = new SubmitOrderBean();
                                        submitOrderBean.setTotalAmount(listBeans.get(position).getTotalAmount());
                                        submitOrderBean.setMasterNo(listBeans.get(position).getOrderItems().get(0).getOrderSn());
                                        ARouter.getInstance().build("/module_user_store/PaymentActivity")
                                                .withSerializable("submitOrderBean", submitOrderBean)
                                                .navigation();
                                    }
                                });
                            } else if (status == 8) {
                                //8待收货
                                //查看物流
                                view2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(mContext, "查看物流", Toast.LENGTH_SHORT).show();
                                        ARouter.getInstance().build("/module_user_mine/LogisticsInformationActivity").navigation();
                                    }
                                });
                                //确认收货
                                view3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(mContext, "确认收货", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("OrderAllPresenterError-------->" + errorMsg);
            }
        }));

    }
}
