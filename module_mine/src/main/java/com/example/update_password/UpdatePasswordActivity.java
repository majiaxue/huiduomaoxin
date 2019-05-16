package com.example.update_password;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdatePasswordActivity extends BaseActivity<UpdatePasswordView, UpdatePasswordPresenter> implements UpdatePasswordView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.password_new)
    EditText passwordNew;
    @BindView(R2.id.password_new_second)
    EditText passwordNewSecond;
    @BindView(R2.id.password_btn)
    TextView passwordBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    public void initData() {
        includeTitle.setText(getResources().getString(R.string.update_password));
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commit(passwordNew.getText().toString(),passwordNewSecond.getText().toString());
            }
        });
    }

    @Override
    public UpdatePasswordView createView() {
        return this;
    }

    @Override
    public UpdatePasswordPresenter createPresenter() {
        return new UpdatePasswordPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
