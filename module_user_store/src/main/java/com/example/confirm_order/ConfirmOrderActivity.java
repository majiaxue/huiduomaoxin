package com.example.confirm_order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.confirm_order.adapter.ConfirmOrderAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

/**
 * 确认订单
 */

public class ConfirmOrderActivity extends BaseActivity<ConfirmOrderView, ConfirmOrderPresenter> implements ConfirmOrderView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.confirm_order_name)
    TextView confirmOrderName;
    @BindView(R2.id.confirm_order_phone)
    TextView confirmOrderPhone;
    @BindView(R2.id.confirm_order_choose_address)
    TextView confirmOrderChooseAddress;
    @BindView(R2.id.confirm_order_rela)
    RelativeLayout confirmOrderRela;
    @BindView(R2.id.confirm_order_rv)
    RecyclerView confirmOrderRv;
    @BindView(R2.id.confirm_order_total_price)
    TextView confirmOrderTotalPrice;
    @BindView(R2.id.confirm_order_total_yunfei)
    TextView confirmOrderTotalYunfei;
    @BindView(R2.id.confirm_order_total_coupon)
    TextView confirmOrderTotalCoupon;
    @BindView(R2.id.confirm_order_final_price)
    TextView confirmOrderFinalPrice;
    @BindView(R2.id.confirm_order_submit)
    TextView confirmOrderSubmit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initData() {
        includeTitle.setText("确认订单");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        confirmOrderRv.setLayoutManager(layoutManager);
        if (confirmOrderRv.getItemDecorationCount() < 1) {
            confirmOrderRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_10)));
        }
        presenter.loadData();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmOrderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToPayment();
            }
        });
    }

    @Override
    public void loadRv(ConfirmOrderAdapter adapter) {
        confirmOrderRv.setAdapter(adapter);
    }

    @Override
    public ConfirmOrderView createView() {
        return this;
    }

    @Override
    public ConfirmOrderPresenter createPresenter() {
        return new ConfirmOrderPresenter(this);
    }
}
