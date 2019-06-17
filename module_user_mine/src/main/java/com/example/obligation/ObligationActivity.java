package com.example.obligation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.view.AddAndSubView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_obligation;
    }

    @Override
    public void initData() {
        presenter.obligationRec(obligationRec);
    }

    @Override
    public void initClick() {
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

}
