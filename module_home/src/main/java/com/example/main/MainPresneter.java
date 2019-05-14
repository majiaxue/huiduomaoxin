package com.example.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.hairring.HairRingFragment;
import com.example.home.HomeFragment;
import com.example.mine.MineFragment;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.search.SearchFragment;
import com.example.shopping.ShoppingFragment;
import com.example.utils.AppManager;

public class MainPresneter extends BasePresenter<MainView> {
    //触碰标识
    private long exitTime = 0;

    private FragmentManager fragmentManager;
    private HairRingFragment hairRingFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private SearchFragment searchFragment;
    private ShoppingFragment shoppingFragment;

    public MainPresneter(Context context) {
        super(context);
    }

    public void loadData(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        hairRingFragment = new HairRingFragment();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        searchFragment = new SearchFragment();
        shoppingFragment = new ShoppingFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resId, hairRingFragment)
                .add(resId, homeFragment)
                .add(resId, mineFragment)
                .add(resId, searchFragment)
                .add(resId, shoppingFragment);
        transaction.show(homeFragment)
                .hide(hairRingFragment)
                .hide(mineFragment)
                .hide(searchFragment)
                .hide(shoppingFragment)
                .commit();

    }

    public void click(int resId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int position = 0;
        if (resId == R.id.main_home) {
            transaction.show(homeFragment)
                    .hide(hairRingFragment)
                    .hide(searchFragment)
                    .hide(shoppingFragment)
                    .hide(mineFragment)
                    .commit();
            position = 0;
        } else if (resId == R.id.main_shopping) {
            transaction.show(shoppingFragment)
                    .hide(hairRingFragment)
                    .hide(searchFragment)
                    .hide(homeFragment)
                    .hide(mineFragment)
                    .commit();
            position = 1;
        } else if (resId == R.id.main_search) {
            transaction.show(searchFragment)
                    .hide(hairRingFragment)
                    .hide(homeFragment)
                    .hide(shoppingFragment)
                    .hide(mineFragment)
                    .commit();
            position = 2;
        } else if (resId == R.id.main_hairring) {
            transaction.show(hairRingFragment)
                    .hide(homeFragment)
                    .hide(searchFragment)
                    .hide(shoppingFragment)
                    .hide(mineFragment)
                    .commit();
            position = 3;
        } else if (resId == R.id.main_mine) {
            transaction.show(mineFragment)
                    .hide(hairRingFragment)
                    .hide(searchFragment)
                    .hide(shoppingFragment)
                    .hide(homeFragment)
                    .commit();
            position = 4;
        }
        if (getView() != null) {
            getView().clickBottom(position);
        }
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().AppExit();
            System.exit(0);
        }
    }

    @Override
    protected void onViewDestroy() {

    }
}
