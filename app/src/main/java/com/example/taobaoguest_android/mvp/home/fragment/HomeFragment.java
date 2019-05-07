package com.example.taobaoguest_android.mvp.home.fragment;

import com.example.taobaoguest_android.R;
import com.example.taobaoguest_android.base.BaseFragment;
import com.example.taobaoguest_android.mvp.hairring.presenter.HairringPresenter;
import com.example.taobaoguest_android.mvp.hairring.view.HairringView;
import com.example.taobaoguest_android.mvp.home.presenter.HomePresenter;
import com.example.taobaoguest_android.mvp.home.view.HomeView;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public HomeView createView() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getContext());
    }
}
