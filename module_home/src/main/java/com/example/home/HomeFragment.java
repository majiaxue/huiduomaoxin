package com.example.home;

import com.example.module_home.R;
import com.example.mvp.BaseFragment;

import butterknife.Unbinder;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    Unbinder unbinder;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
