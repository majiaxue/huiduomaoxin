package com.example.mineorder.staydeliverygoods.orderdetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsView, OrderDetailsPresenter> implements OrderDetailsView {


    @BindView(R2.id.order_details_image_back)
    ImageView orderDetailsImageBack;
    @BindView(R2.id.order_details_status)
    TextView orderDetailsStatus;
    @BindView(R2.id.order_details_subhead)
    TextView orderDetailsSubhead;
    @BindView(R2.id.order_details_name)
    TextView orderDetailsName;
    @BindView(R2.id.order_details_phone)
    TextView orderDetailsPhone;
    @BindView(R2.id.order_details_address)
    TextView orderDetailsAddress;
    @BindView(R2.id.order_details_goods_rec)
    RecyclerView orderDetailsGoodsRec;
    @BindView(R2.id.order_details_goods_price)
    TextView orderDetailsGoodsPrice;
    @BindView(R2.id.order_details_freight)
    TextView orderDetailsFreight;
    @BindView(R2.id.order_details_coupon)
    TextView orderDetailsCoupon;
    @BindView(R2.id.order_details_actual_payment)
    TextView orderDetailsActualPayment;
    @BindView(R2.id.order_details_contact_seller)
    LinearLayout orderDetailsContactSeller;
    @BindView(R2.id.order_details_dial)
    LinearLayout orderDetailsDial;
    @BindView(R2.id.order_details_consult_customer_service)
    LinearLayout orderDetailsConsultCustomerService;
    @BindView(R2.id.order_details_recommend_rec)
    RecyclerView orderDetailsRecommendRec;
    @BindView(R2.id.order_details_left)
    TextView orderDetailsLeft;
    @BindView(R2.id.order_details_right)
    TextView orderDetailsRight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        orderDetailsLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("18818814558");
            }
        });

        orderDetailsRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("40083312345");
            }
        });
    }

    @Override
    public OrderDetailsView createView() {
        return this;
    }

    @Override
    public OrderDetailsPresenter createPresenter() {
        return new OrderDetailsPresenter(this);
    }

    //调起电话
    private void call(String call) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开启系统拨号器
        startActivity(intent);

    }
}
