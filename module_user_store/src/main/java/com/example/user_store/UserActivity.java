package com.example.user_store;


import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvp.BaseFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 多用户商城主界面
 */
@Route(path = "/module_user_store/UserActivity")
public class UserActivity extends BaseFragmentActivity<UserView, UserPresenter> implements UserView {


    @BindView(R2.id.user_frame)
    FrameLayout userFrame;
    @BindView(R2.id.user_home)
    RadioButton userHome;
    @BindView(R2.id.user_classify)
    RadioButton userClassify;
    @BindView(R2.id.user_shopping_cart)
    RadioButton userShoppingCart;
    @BindView(R2.id.user_mine)
    RadioButton userMine;
    @BindView(R2.id.user_group)
    RadioGroup userGroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_multi_user;
    }

    @Override
    public void initData() {
        presenter.loadData(getSupportFragmentManager(), R.id.user_frame);
    }

    @Override
    public void initClick() {
        userGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                presenter.click(checkedId);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //返回淘宝客
            ARouter.getInstance().build("/home/main").navigation();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public UserView createView() {
        return this;
    }

    @Override
    public UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
