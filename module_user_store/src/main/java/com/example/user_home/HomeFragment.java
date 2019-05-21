package com.example.user_home;

import com.example.user_store.R;
import com.example.mvp.BaseFragment;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:商城首页
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_multi_user_home;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

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
