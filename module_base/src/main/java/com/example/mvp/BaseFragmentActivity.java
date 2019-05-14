package com.example.mvp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.utils.AppManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


public abstract class BaseFragmentActivity<V extends IView, P extends BasePresenter> extends FragmentActivity implements BaseMVP<V, P> {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        //创建presenter
        presenter = createPresenter();
        if (presenter != null) {
            //将View层注册到Presenter中
            presenter.registerView(createView());
        }
        initData();
        initClick();
    }

    public abstract int getLayoutId();

    public abstract void initData();

    public abstract void initClick();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            AppManager.getInstance().finishActivity(this);
            //Activity销毁时的调用，让具体实现BasePresenter中onViewDestroy()方法做出决定
            presenter.destroy();
        }
    }
}
