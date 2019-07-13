package com.example.local_detail;

import com.example.mvp.BaseActivity;
import com.example.user_store.R;

public class LocalDetailActivity extends BaseActivity<LocalDetailView, LocalDetailPresenter> implements LocalDetailView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_detail;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public LocalDetailView createView() {
        return this;
    }

    @Override
    public LocalDetailPresenter createPresenter() {
        return new LocalDetailPresenter(this);
    }
}
