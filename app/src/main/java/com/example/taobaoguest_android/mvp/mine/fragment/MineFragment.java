package com.example.taobaoguest_android.mvp.mine.fragment;

import com.example.taobaoguest_android.R;
import com.example.taobaoguest_android.base.BaseFragment;
import com.example.taobaoguest_android.mvp.hairring.presenter.HairringPresenter;
import com.example.taobaoguest_android.mvp.hairring.view.HairringView;
import com.example.taobaoguest_android.mvp.mine.presenter.MinePresenter;
import com.example.taobaoguest_android.mvp.mine.view.MineView;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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
