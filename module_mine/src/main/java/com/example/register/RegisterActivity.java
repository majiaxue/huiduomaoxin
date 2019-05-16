package com.example.register;

import com.example.module_mine.R;
import com.example.mvp.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterView,RegisterPresenter> implements RegisterView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public RegisterView createView() {
        return null;
    }

    @Override
    public RegisterPresenter createPresenter() {
        return null;
    }
}
