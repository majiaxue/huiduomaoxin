package com.example.user_store;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.local_shop.LocalShopFragment;
import com.example.user_classify.ClassifyFragment;
import com.example.user_home.HomeFragment;
import com.example.user_mine.MineFragment;
import com.example.user_shopping_cart.ShoppingCartFragment;
import com.example.mvp.BasePresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class UserPresenter extends BasePresenter<UserView> {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private LocalShopFragment localShopFragment;
    private MineFragment mineFragment;

    private boolean isHomeShow = true;

    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(mContext);
    }

    public void loadData(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        mineFragment = new MineFragment();
        localShopFragment = new LocalShopFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resId, homeFragment)
                .add(resId, classifyFragment)
                .add(resId, shoppingCartFragment)
                .add(resId, mineFragment)
                .add(resId, localShopFragment);
        transaction.show(homeFragment)
                .hide(classifyFragment)
                .hide(shoppingCartFragment)
                .hide(mineFragment)
                .hide(localShopFragment)
                .commit();
    }

    public void click(int resId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (resId == R.id.user_home) {
            transaction.show(homeFragment)
                    .hide(classifyFragment)
                    .hide(shoppingCartFragment)
                    .hide(mineFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = true;
        } else if (resId == R.id.user_classify) {
            transaction.show(classifyFragment)
                    .hide(homeFragment)
                    .hide(shoppingCartFragment)
                    .hide(mineFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = false;
        } else if (resId == R.id.user_shopping_cart) {
            transaction.show(shoppingCartFragment)
                    .hide(homeFragment)
                    .hide(classifyFragment)
                    .hide(mineFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = false;
        } else if (resId == R.id.user_mine) {
            transaction.show(mineFragment)
                    .hide(homeFragment)
                    .hide(classifyFragment)
                    .hide(shoppingCartFragment)
                    .hide(localShopFragment)
                    .commit();
            isHomeShow = false;
        } else if (resId == R.id.user_local_shop) {
            transaction.show(localShopFragment)
                    .hide(homeFragment)
                    .hide(classifyFragment)
                    .hide(shoppingCartFragment)
                    .hide(mineFragment)
                    .commit();
            isHomeShow = false;
        }
    }

    public void setBack() {
        if (isHomeShow) {
            ((Activity) mContext).finish();
        } else {
            click(R.id.user_home);
            getView().toHome();
        }
    }
}
