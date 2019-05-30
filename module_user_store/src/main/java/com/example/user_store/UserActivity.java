package com.example.user_store;


import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragmentActivity;
import com.example.user_classify.ClassifyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

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
        return R.layout.activity_user;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        presenter.loadData(getSupportFragmentManager(), R.id.user_frame);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        if (CommonResource.JUMP_CART.equals(key)) {
            userShoppingCart.setChecked(true);
            presenter.click(R.id.user_shopping_cart);
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean2 eventBusBean2) {
        if (CommonResource.USER_BACK.equals(eventBusBean2.getMsg())) {
            finish();
        } else if (CommonResource.JUMP_CLASSIFY.equals(eventBusBean2.getMsg())) {
            ClassifyFragment.position = eventBusBean2.getPosition() + 1;
            userClassify.setChecked(true);
            presenter.click(R.id.user_classify);
        }
    }

    @Override
    public UserView createView() {
        return this;
    }

    @Override
    public UserPresenter createPresenter() {
        return new UserPresenter(this);
    }
}
