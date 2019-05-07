package com.example.taobaoguest_android.mvp.search.fragment;

import com.example.taobaoguest_android.R;
import com.example.taobaoguest_android.base.BaseFragment;
import com.example.taobaoguest_android.mvp.hairring.presenter.HairringPresenter;
import com.example.taobaoguest_android.mvp.hairring.view.HairringView;
import com.example.taobaoguest_android.mvp.search.presenter.SearchPresenter;
import com.example.taobaoguest_android.mvp.search.view.SearchView;

public class SearchFragment extends BaseFragment<SearchView, SearchPresenter> implements SearchView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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
