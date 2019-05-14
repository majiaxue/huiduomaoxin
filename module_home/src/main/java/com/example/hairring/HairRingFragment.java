package com.example.hairring;

import com.example.module_home.R;
import com.example.mvp.BaseFragment;

public class HairRingFragment extends BaseFragment<HairringView, HairringPresenter> implements HairringView {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hairring;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

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
