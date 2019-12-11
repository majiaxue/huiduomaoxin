package com.example.h5home;

import android.webkit.WebView;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;

/**
 * h5首页
 */
public class H5HomeFragment extends BaseFragment<H5HomeView, H5HomePresenter> implements H5HomeView {

    @BindView(R2.id.home_h5_web)
    WebView homeH5Web;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_h5_home;
    }

    @Override
    public void initData() {
        presenter.initWebView(homeH5Web);
    }

    @Override
    public void initClick() {

    }

    @Override
    public H5HomeView createView() {
        return this;
    }

    @Override
    public H5HomePresenter createPresenter() {
        return new H5HomePresenter(getContext());
    }
}
