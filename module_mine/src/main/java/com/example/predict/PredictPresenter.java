package com.example.predict;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.predict.jd.JDFragment;
import com.example.predict.pdd.PddFragment;
import com.example.predict.sc.SCFragment;
import com.example.predict.tb.TBFragment;

public class PredictPresenter extends BasePresenter<PredictView> {
    private JDFragment jdFragment;
    private PddFragment pddFragment;
    private SCFragment scFragment;
    private TBFragment tbFragment;
    private FragmentManager manager;

    public PredictPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void change(int resId) {
        int type = 0;
        FragmentTransaction transaction = manager.beginTransaction();
        if (resId == R.id.predict_tb) {
            transaction.show(tbFragment)
                    .hide(jdFragment)
                    .hide(pddFragment)
                    .hide(scFragment).commit();
            type = 0;
        } else if (resId == R.id.predict_jd) {
            transaction.show(jdFragment)
                    .hide(pddFragment)
                    .hide(scFragment)
                    .hide(tbFragment).commit();
            type = 2;
        } else if (resId == R.id.predict_pdd) {
            transaction.show(pddFragment)
                    .hide(jdFragment)
                    .hide(scFragment)
                    .hide(tbFragment).commit();
            type = 1;
        } else if (resId == R.id.predict_sc) {
            transaction.show(scFragment)
                    .hide(jdFragment)
                    .hide(pddFragment)
                    .hide(tbFragment).commit();
            type = 3;
        }
        getView().changeType(type);
    }

    public void loadData(FragmentManager manager, int resId) {
        this.manager = manager;
        jdFragment = new JDFragment();
        pddFragment = new PddFragment();
        scFragment = new SCFragment();
        tbFragment = new TBFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(resId, jdFragment)
                .add(resId, pddFragment)
                .add(resId, scFragment)
                .add(resId, tbFragment)
                .show(jdFragment)
                .hide(pddFragment)
                .hide(scFragment)
                .hide(tbFragment)
                .commit();
    }
}
