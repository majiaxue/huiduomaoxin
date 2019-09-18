package com.example.local_order_confirm;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.ShippingAddressBean;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class LocalOrderConfirmActivity extends BaseActivity<LocalOrderConfirmView, LocalOrderConfirmPresenter> implements LocalOrderConfirmView {
    @BindView(R2.id.include_back2)
    ImageView includeBack2;
    @BindView(R2.id.include_title2)
    TextView includeTitle2;
    @BindView(R2.id.local_order_confirm_add)
    TextView localOrderConfirmAdd;
    @BindView(R2.id.local_order_confirm_time)
    TextView localOrderConfirmTime;
    @BindView(R2.id.local_order_confirm_type)
    TextView localOrderConfirmType;
    @BindView(R2.id.local_order_confirm_choose_pay)
    LinearLayout localOrderConfirmChoosePay;
    @BindView(R2.id.local_order_confirm_shop)
    TextView localOrderConfirmShop;
    @BindView(R2.id.local_order_confirm_img)
    ImageView localOrderConfirmImg;
    @BindView(R2.id.local_order_confirm_title)
    TextView localOrderConfirmTitle;
    @BindView(R2.id.local_order_confirm_count)
    TextView localOrderConfirmCount;
    @BindView(R2.id.local_order_confirm_money)
    TextView localOrderConfirmMoney;
    @BindView(R2.id.local_order_confirm_peisong)
    TextView localOrderConfirmPeisong;
    @BindView(R2.id.local_order_confirm_peisongfei)
    TextView localOrderConfirmPeisongfei;
    @BindView(R2.id.local_order_confirm_reduce_money)
    TextView localOrderConfirmReduceMoney;
    @BindView(R2.id.local_order_confirm_total_money)
    TextView localOrderConfirmTotalMoney;
    @BindView(R2.id.local_order_confirm_final_money)
    TextView localOrderConfirmFinalMoney;
    @BindView(R2.id.local_order_confirm_btn)
    TextView localOrderConfirmBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_order_confirm;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        localOrderConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.chooseAddress();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            ShippingAddressBean addressBean = (ShippingAddressBean) data.getSerializableExtra("address");

        }
    }

    @Override
    public LocalOrderConfirmView createView() {
        return this;
    }

    @Override
    public LocalOrderConfirmPresenter createPresenter() {
        return new LocalOrderConfirmPresenter(this);
    }
}
