package com.example.taobaoguest_android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by .
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(this);
        initListener();
        presenter = providePresenter();
        initData();
        initNetWork();
    }

    protected abstract P providePresenter();

    protected abstract int provideLayoutId();

    protected abstract void initNetWork();

    protected abstract void initData();

    protected abstract void initListener();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放activity的引用
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
