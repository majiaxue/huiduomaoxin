package com.example.taobaoguest_android.base;

/**
 * Created by Administrator on 2019/3/7.
 */

public interface BaseMVP<V extends IBaseView, P extends BasePresenter> {
    V createView();

    P createPresenter();
}
