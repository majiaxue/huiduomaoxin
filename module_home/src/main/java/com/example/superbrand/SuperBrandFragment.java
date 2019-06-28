package com.example.superbrand;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;

public class SuperBrandFragment extends BaseFragment<SuperBrandView, SuperBrandPresenter> implements SuperBrandView {


    @BindView(R2.id.super_brand_tab)
    TabLayout superBrandTab;
    @BindView(R2.id.super_brand_rec)
    RecyclerView superBrandRec;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_super_brand;
    }

    @Override
    public void initData() {
        presenter.initView(superBrandTab,superBrandRec);
        //recycler
//        presenter.setSuperBrandRec(superBrandRec);
    }

    @Override
    public void initClick() {

    }

    @Override
    public SuperBrandView createView() {
        return this;
    }

    @Override
    public SuperBrandPresenter createPresenter() {
        return new SuperBrandPresenter(getContext());
    }

    @Override
    public void noBrand(boolean noBrand) {
        if (noBrand){
            superBrandRec.setVisibility(View.GONE);
        }else{
            superBrandRec.setVisibility(View.VISIBLE);
        }
    }
}
