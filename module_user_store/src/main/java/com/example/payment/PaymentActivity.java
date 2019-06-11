package com.example.payment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.SubmitOrderBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

public class PaymentActivity extends BaseActivity<PaymentView, PaymentPresenter> implements PaymentView {
    @BindView(R2.id.payment_back)
    ImageView paymentBack;
    @BindView(R2.id.payment_money)
    TextView paymentMoney;
    @BindView(R2.id.payment_weixin_img)
    ImageView paymentWeixinImg;
    @BindView(R2.id.payment_weixin)
    LinearLayout paymentWeixin;
    @BindView(R2.id.payment_zfb_img)
    ImageView paymentZfbImg;
    @BindView(R2.id.payment_zfb)
    LinearLayout paymentZfb;
    @BindView(R2.id.payment_btn)
    TextView paymentBtn;

    private boolean isWeChat = true;
    private SubmitOrderBean submitOrderBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        submitOrderBean = (SubmitOrderBean) intent.getSerializableExtra("bean");
        paymentMoney.setText("￥" + submitOrderBean.getTotalAmount());
    }

    @Override
    public void initClick() {
        paymentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        paymentWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = true;
                changePayType();
            }
        });

        paymentZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = false;
                changePayType();
            }
        });

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.pay(isWeChat,submitOrderBean);
            }
        });
    }

    private void changePayType() {
        paymentWeixinImg.setImageResource(isWeChat ? R.drawable.icon_xuanzhong : R.drawable.icon_weixuanzhong);
        paymentZfbImg.setImageResource(isWeChat ? R.drawable.icon_weixuanzhong : R.drawable.icon_xuanzhong);
    }

    @Override
    public PaymentView createView() {
        return this;
    }

    @Override
    public PaymentPresenter createPresenter() {
        return new PaymentPresenter(this);
    }
}
