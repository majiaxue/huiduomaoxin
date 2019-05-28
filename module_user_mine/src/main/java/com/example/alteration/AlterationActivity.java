package com.example.alteration;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;
/**
 * 退货/售后
 */
@Route(path = "/module_user_mine/AlterationActivity")
public class AlterationActivity extends BaseActivity<AlterationView, AlterationPresenter> implements AlterationView {


    @BindView(R2.id.alteration_back)
    ImageView alterationBack;
    @BindView(R2.id.alteration_rec)
    RecyclerView alterationRec;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alteration;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        alterationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        presenter.alterationRec(alterationRec);
    }

    @Override
    public AlterationView createView() {
        return this;
    }

    @Override
    public AlterationPresenter createPresenter() {
        return new AlterationPresenter(this);
    }
}
