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
import com.example.classify.ClassifyFragment;
import com.example.superbrand.SuperBrandFragment;
import com.example.utils.AppManager;

public class MainPresenter extends BasePresenter<MainView> {
    //触碰标识
    private long exitTime = 0;

    private FragmentManager fragmentManager;
    private HairRingFragment hairRingFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private ClassifyFragment classifyFragment;
    private SuperBrandFragment superBrandFragment;

    public MainPresenter(Context context) {
        super(context);
    }

    public void loadData(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        hairRingFragment = new HairRingFragment();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        classifyFragment = new ClassifyFragment();
        superBrandFragment = new SuperBrandFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resId, hairRingFragment)
                .add(resId, homeFragment)
                .add(resId, mineFragment)
                .add(resId, classifyFragment)
                .add(resId, superBrandFragment);
        transaction.show(homeFragment)
                .hide(hairRingFragment)
                .hide(mineFragment)
                .hide(classifyFragment)
                .hide(superBrandFragment)
                .commit();

    }

    public void click(int resId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int position = 0;
        if (resId == R.id.main_home) {
            transaction.show(homeFragment)
                    .hide(hairRingFragment)
                    .hide(classifyFragment)
                    .hide(mineFragment)
                    .hide(superBrandFragment)
                    .commit();
            position = 0;
        } else if (resId == R.id.main_classify) {
            transaction.show(superBrandFragment)
                    .hide(classifyFragment)
                    .hide(hairRingFragment)
                    .hide(homeFragment)
                    .hide(mineFragment)
                    .commit();
            position = 1;
        } else if (resId == R.id.main_hairring) {
            transaction.show(hairRingFragment)
                    .hide(homeFragment)
                    .hide(superBrandFragment)
                    .hide(classifyFragment)
                    .hide(mineFragment)
                    .commit();
            position = 3;
        } else if (resId == R.id.main_mine) {
            transaction.show(mineFragment)
                    .hide(hairRingFragment)
                    .hide(superBrandFragment)
                    .hide(classifyFragment)
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
