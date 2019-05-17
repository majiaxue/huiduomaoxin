package com.example.classify;

import com.example.module_home.R;
import com.example.mvp.BaseFragment;

public class ClassifyFragment extends BaseFragment<ClassifyView, ClassifyPresenter> implements ClassifyView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public ClassifyView createView() {
        return this;
    }

    @Override
    public ClassifyPresenter createPresenter() {
        return new ClassifyPresenter(getContext());
    }
}
