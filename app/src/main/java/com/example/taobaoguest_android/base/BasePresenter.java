package com.example.taobaoguest_android.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2019/3/7.
 */

public abstract class BasePresenter<V extends IBaseView> implements IPresenter<V> {
    /**
     * 使用弱引用来防止内存泄漏
     */
    private WeakReference<V> wrf;
    protected Context mContext;

    public BasePresenter(Context context) {
        mContext = context;
    }

    @Override
    public void registerView(V view) {
        wrf = new WeakReference<V>(view);
    }

    @Override
    public V getView() {
        return wrf == null ? null : wrf.get();
    }

    /**
     * 在Activity或Fragment卸载时调用View结束的方法
     */
    @Override
    public void destroy() {
        if (wrf != null) {
            wrf.clear();
            wrf = null;
        }
        onViewDestroy();
    }

    protected abstract void onViewDestroy();
}
