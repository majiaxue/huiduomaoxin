package com.example.mine;

import com.example.module_home.R;
import com.example.mvp.BaseFragment;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public MineView createView() {
        return this;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getContext());
    }
}
