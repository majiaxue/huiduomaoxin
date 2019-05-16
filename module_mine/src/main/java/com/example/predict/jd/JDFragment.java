package com.example.predict.jd;

import com.example.module_mine.R;
import com.example.mvp.BaseFragment;

public class JDFragment extends BaseFragment<JDView, JDPresenter> implements JDView {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_predict;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public JDView createView() {
        return this;
    }

    @Override
    public JDPresenter createPresenter() {
        return new JDPresenter(getContext());
    }
}
