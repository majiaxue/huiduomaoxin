package com.example.payment;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.SubmitOrderBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

/**
 * 确认支付
 */
@Route(path = "/module_user_store/PaymentActivity")
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

    @Autowired(name = "submitOrderBean")
    SubmitOrderBean submitOrderBean;

    private boolean isWeChat = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
//        Intent intent = getIntent();
//        submitOrderBean = (SubmitOrderBean) intent.getSerializableExtra("bean");
        paymentMoney.setText("￥" + submitOrderBean.getTotalAmount());
    }

    @Override
    public void initClick() {
        paymentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goBack();
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
                presenter.pay(isWeChat, submitOrderBean);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        presenter.goBack();
        return false;
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
