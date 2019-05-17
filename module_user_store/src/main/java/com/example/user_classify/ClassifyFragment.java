package com.example.user_classify;

import com.example.user_store.R;
import com.example.mvp.BaseFragment;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ClassifyFragment extends BaseFragment<ClassifyView, ClassifyPresenter> implements ClassifyView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_multi_user_classify;
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
