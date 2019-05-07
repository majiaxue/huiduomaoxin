package com.example.taobaoguest_android.base;

/**
 * Created by Administrator on 2019/3/7.
 */

public interface IPresenter<V extends IBaseView> {
    /**
     * 注册View层
     *
     * @param view
     */
    void registerView(V view);

    /**
     * 获取View
     *
     * @return
     */
    V getView();

    /**
     * 销毁动作（如Activity、Fragment的卸载）
     */
    void destroy();
}
