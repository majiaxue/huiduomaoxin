package com.example.taobaoguest_android.base;

/**
 * Created by .
 */

public abstract class BasePresenter<V extends IBaseView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();

    //释放activity的引用
    public void onDestroy() {
        view = null;
    }

}
