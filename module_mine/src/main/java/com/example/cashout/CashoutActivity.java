package com.example.cashout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class CashoutActivity extends BaseActivity<CashoutView, CashoutPresenter> implements CashoutView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.cashout_edit)
    EditText cashoutEdit;
    @BindView(R2.id.cashout_balance)
    TextView cashoutBalance;
    @BindView(R2.id.cashout_all)
    TextView cashoutAll;
    @BindView(R2.id.cashout_btn)
    TextView cashoutBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cashout;
    }

    @Override
    public void initData() {
        includeTitle.setText("提现");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cashoutAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cashoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cashoutEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public CashoutView createView() {
        return this;
    }

    @Override
    public CashoutPresenter createPresenter() {
        return new CashoutPresenter(this);
    }
}
