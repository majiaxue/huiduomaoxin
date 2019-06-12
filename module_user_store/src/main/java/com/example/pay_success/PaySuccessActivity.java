package com.example.pay_success;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.SubmitOrderBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

public class PaySuccessActivity extends BaseActivity<PaySuccessView, PaySuccessPresenter> implements PaySuccessView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.pay_success_txt)
    TextView paySuccessTxt;
    @BindView(R2.id.pay_success_order)
    TextView paySuccessOrder;
    @BindView(R2.id.pay_success_home)
    TextView paySuccessHome;
    private SubmitOrderBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_success;
    }

    @Override
    public void initData() {
        includeTitle.setText("支付完成");
        Intent intent = getIntent();
        bean = (SubmitOrderBean) intent.getSerializableExtra("bean");
        paySuccessTxt.setText("支付成功￥" + bean.getTotalAmount());
        presenter.loadData(bean.getMasterNo());
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder(bean.getMasterNo());
            }
        });

        paySuccessOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder(bean.getMasterNo());
            }
        });

        paySuccessHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToHome();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            presenter.jumpToOrder(bean.getMasterNo());
        }
        return true;
    }

    @Override
    public PaySuccessView createView() {
        return this;
    }

    @Override
    public PaySuccessPresenter createPresenter() {
        return new PaySuccessPresenter(this);
    }

}
