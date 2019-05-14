package com.example.search;

import com.example.module_home.R;
import com.example.mvp.BaseFragment;

public class SearchFragment extends BaseFragment<SearchView, SearchPresenter> implements SearchView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public SearchView createView() {
        return this;
    }

    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter(getContext());
    }
}
