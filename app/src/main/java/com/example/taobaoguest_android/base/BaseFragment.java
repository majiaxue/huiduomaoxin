package com.example.taobaoguest_android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by cuihaohao on 2019/5/7
 * Describe:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(provideLayoutId(), null);
        initListener();
        presenter = providePresenter();
        initData(view);
        initNetWork();
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract P providePresenter();

    protected abstract int provideLayoutId();

    protected abstract void initNetWork();

    protected abstract void initData(View view);

    protected abstract void initListener();


    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放activity的引用
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

}
