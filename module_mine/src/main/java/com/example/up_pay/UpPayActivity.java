package com.example.up_pay;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/module_mine/up_pay")
public class UpPayActivity extends BaseActivity<UpPayView, UpPayPresenter> implements UpPayView {
    @BindView(R2.id.uppay_back)
    ImageView uppayBack;
    @BindView(R2.id.uppay_money)
    TextView uppayMoney;
    @BindView(R2.id.uppay_weixin_img)
    ImageView uppayWeixinImg;
    @BindView(R2.id.uppay_weixin)
    LinearLayout uppayWeixin;
    @BindView(R2.id.uppay_zfb_img)
    ImageView uppayZfbImg;
    @BindView(R2.id.uppay_zfb)
    LinearLayout uppayZfb;
    @BindView(R2.id.uppay_btn)
    TextView uppayBtn;

    @Autowired(name = "money")
    String money;
    @Autowired(name = "type")
    String type;

    private boolean isWeChat = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_up_pay;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        uppayMoney.setText("￥" + money);
    }

    @Override
    public void initClick() {
        uppayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        uppayWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = true;
                changePayType();
            }
        });

        uppayZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = false;
                changePayType();
            }
        });

        uppayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.pay(isWeChat, money, type);
            }
        });
    }

    private void changePayType() {
        uppayWeixinImg.setImageResource(isWeChat ? R.drawable.icon_xuanzhong : R.drawable.icon_weixuanzhong);
        uppayZfbImg.setImageResource(isWeChat ? R.drawable.icon_weixuanzhong : R.drawable.icon_xuanzhong);
    }

    @Override
    public UpPayView createView() {
        return this;
    }

    @Override
    public UpPayPresenter createPresenter() {
        return new UpPayPresenter(this);
    }
}
