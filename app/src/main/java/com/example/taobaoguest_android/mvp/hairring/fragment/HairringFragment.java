package com.example.taobaoguest_android.mvp.hairring.fragment;

import com.example.taobaoguest_android.R;
import com.example.taobaoguest_android.base.BaseFragment;
import com.example.taobaoguest_android.mvp.hairring.presenter.HairringPresenter;
import com.example.taobaoguest_android.mvp.hairring.view.HairringView;

public class HairringFragment extends BaseFragment<HairringView, HairringPresenter> implements HairringView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_hairring;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public HairringView createView() {
        return this;
    }

    @Override
    public HairringPresenter createPresenter() {
        return new HairringPresenter(getContext());
    }
}
