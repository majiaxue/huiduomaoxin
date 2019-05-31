package com.example.secondarydetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 二级详情
 */
public class SecondaryDetailsActivity extends BaseActivity<SecondaryDetailsView, SecondaryDetailsPresenter> implements SecondaryDetailsView {


    @BindView(R2.id.secondary_details_image_back)
    ImageView secondaryDetailsImageBack;
    @BindView(R2.id.secondary_details_search)
    LinearLayout secondaryDetailsSearch;
    @BindView(R2.id.secondary_details_tab)
    TabLayout secondaryDetailsTab;
    @BindView(R2.id.secondary_details_rec)
    RecyclerView secondaryDetailsRec;
    @Override
    public int getLayoutId() {
        return R.layout.activity_secondary_details;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        presenter.initTab(secondaryDetailsTab);
        presenter.secondaryDetailsRec(secondaryDetailsRec,type);
    }

    @Override
    public void initClick() {
        secondaryDetailsImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击到搜索页面
        secondaryDetailsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondaryDetailsActivity.this, SearchActivity.class));
            }
        });

    }

    @Override
    public SecondaryDetailsView createView() {
        return this;
    }

    @Override
    public SecondaryDetailsPresenter createPresenter() {
        return new SecondaryDetailsPresenter(this);
    }

}
