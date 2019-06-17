package com.example.obligation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.routes.ARouter$$Group$$module_user_mine;
import com.example.bean.OrderDetailBean;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;
import com.example.view.AddAndSubView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * 等待付款
 */
@Route(path = "/module_user_mine/ObligationActivity")
public class ObligationActivity extends BaseActivity<ObligationView, ObligationPresenter> implements ObligationView {


    @BindView(R2.id.obligation_image_back)
    ImageView obligationImageBack;
    @BindView(R2.id.obligation_name)
    TextView obligationName;
    @BindView(R2.id.obligation_phone)
    TextView obligationPhone;
    @BindView(R2.id.obligation_address)
    TextView obligationAddress;
    @BindView(R2.id.obligation_relative)
    RelativeLayout obligationRelative;
    @BindView(R2.id.obligation_goods_money)
    TextView obligationGoodsMoney;
    @BindView(R2.id.obligation_freight)
    TextView obligationFreight;
    @BindView(R2.id.obligation_coupon)
    TextView obligationCoupon;
    @BindView(R2.id.obligation_money)
    TextView obligationMoney;
    @BindView(R2.id.obligation_time_remaining)
    TextView obligationTimeRemaining;
    @BindView(R2.id.obligation_cancellation_order)
    TextView obligationCancellationOrder;
    @BindView(R2.id.obligation_payment)
    TextView obligationPayment;
    @BindView(R2.id.obligation_rec)
    RecyclerView obligationRec;
    @BindView(R2.id.obligation_goods_rec)
    RecyclerView obligationGoodsRec;

    @Autowired(name = "orderSn")
    String orderSn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_obligation;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        presenter.initView(orderSn);
        presenter.obligationRec(obligationRec);
    }

    @Override
    public void initClick() {

        obligationImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        obligationCancellationOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupCancellationOrder();
            }
        });
    }

    @Override
    public ObligationView createView() {
        return this;
    }

    @Override
    public ObligationPresenter createPresenter() {
        return new ObligationPresenter(this);
    }

    @Override
    public void loadData(final OrderDetailBean orderDetailBean) {
        obligationName.setText(orderDetailBean.getReceiverName());
        obligationPhone.setText(orderDetailBean.getReceiverPhone());
        obligationAddress.setText(orderDetailBean.getReceiverRegion() + orderDetailBean.getReceiverCity() + orderDetailBean.getReceiverProvince() + orderDetailBean.getOrderAddress());
        obligationName.setText(orderDetailBean.getReceiverName());
        obligationGoodsMoney.setText("￥" + orderDetailBean.getTotalAmount());
        obligationFreight.setText("￥" + orderDetailBean.getFreightAmount());
        obligationCoupon.setText("￥" + orderDetailBean.getCouponAmount());
        obligationMoney.setText("￥" + orderDetailBean.getPayAmount());

        List<OrderDetailBean.ItemsBean> items = orderDetailBean.getItems();

        presenter.items(items, obligationGoodsRec);

//        //取消订单
//        obligationCancellationOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map build = MapUtil.getInstance().addParms("orderId", orderDetailBean.getItems().get(0).getOrderId()).build();
//                Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERREMOVE, build, SPUtil.getToken());
//                RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
//                    @Override
//                    public void onSuccess(String result, String msg) {
//                        LogUtil.e("删除---------->" + result);
//                        if ("true".equals(result)) {
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onError(String errorCode, String errorMsg) {
//                        LogUtil.e("删除---------->" + errorMsg);
//                    }
//                }));
//            }
//        });
        //付款
        obligationPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitOrderBean submitOrderBean = new SubmitOrderBean();
                submitOrderBean.setTotalAmount(orderDetailBean.getTotalAmount());
                submitOrderBean.setMasterNo(orderDetailBean.getMasterSn());
                ARouter.getInstance().build("/module_user_store/PaymentActivity")
                        .withSerializable("submitOrderBean", submitOrderBean)
                        .navigation();
            }
        });


    }

    @Override
    public void isDelete(boolean isDelete) {
        if (isDelete){
            finish();
        }
    }
}
