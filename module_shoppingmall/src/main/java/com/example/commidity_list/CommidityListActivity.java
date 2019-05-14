package com.example.commidity_list;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_shoppingmall.R;
import com.example.module_shoppingmall.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/shopping/commidityList")
public class CommidityListActivity extends BaseActivity<CommidityListView, CommidityListPresenter> implements CommidityListView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_commidity_list;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public CommidityListView createView() {
        return this;
    }

    @Override
    public CommidityListPresenter createPresenter() {
        return new CommidityListPresenter(this);
    }
}
