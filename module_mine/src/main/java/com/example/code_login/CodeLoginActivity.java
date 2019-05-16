package com.example.code_login;

import com.example.module_mine.R;
import com.example.mvp.BaseActivity;

public class CodeLoginActivity extends BaseActivity<CodeLoginView, CodeLoginPresenter> implements CodeLoginView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_code_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public CodeLoginView createView() {
        return this;
    }

    @Override
    public CodeLoginPresenter createPresenter() {
        return new CodeLoginPresenter(this);
    }
}
