package com.example.balance;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.message_list.MessageListActivity;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/mine/balance")
public class BalanceActivity extends BaseActivity<BalanceView, BalancePresenter> implements BalanceView {
    @BindView(R2.id.balance_back)
    ImageView includeBack;
    @BindView(R2.id.balance_title)
    TextView includeTitle;
    @BindView(R2.id.balance_msg)
    ImageView balanceMsg;
    @BindView(R2.id.balance_total_money)
    TextView balanceTotalMoney;
    @BindView(R2.id.balance_edit)
    EditText balanceEdit;
    @BindView(R2.id.balance_zfb_img)
    ImageView balanceZfbImg;
    @BindView(R2.id.balance_zfb)
    LinearLayout balanceZfb;
    @BindView(R2.id.balance_wechat_img)
    ImageView balanceWechatImg;
    @BindView(R2.id.balance_wechat)
    LinearLayout balanceWechat;
    @BindView(R2.id.balance_btn)
    TextView balanceBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_balance;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        balanceMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BalanceActivity.this, MessageListActivity.class));
            }
        });
    }

    @Override
    public BalanceView createView() {
        return this;
    }

    @Override
    public BalancePresenter createPresenter() {
        return new BalancePresenter(this);
    }
}
