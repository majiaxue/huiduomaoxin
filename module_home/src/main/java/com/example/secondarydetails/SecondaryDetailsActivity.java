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
import com.example.secondarydetails.adapter.SecondaryPddRecAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

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
    @BindView(R2.id.secondary_details_no_goods)
    ImageView secondaryDetailsNoGoods;
    @BindView(R2.id.secondary_details_smart_refresh)
    SmartRefreshLayout secondaryDetailsSmartRefresh;
    @Autowired(name = "type")
    String type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_secondary_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
//        Intent intent = getIntent();
//        String type = intent.getStringExtra("type");
        presenter.initView(secondaryDetailsTab, secondaryDetailsRec, secondaryDetailsSmartRefresh, type);
        secondaryDetailsSmartRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 球脉冲 样式
        secondaryDetailsSmartRefresh.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
//        presenter.secondaryDetailsRec(secondaryDetailsRec,type);
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

    @Override
    public void lodeRec(SecondaryPddRecAdapter baseRecAdapter) {
        secondaryDetailsRec.setAdapter(baseRecAdapter);
    }

    @Override
    public void noGoods(boolean isNoGoods) {
        if (isNoGoods) {
            secondaryDetailsNoGoods.setVisibility(View.VISIBLE);
            secondaryDetailsRec.setVisibility(View.GONE);
        } else {
            secondaryDetailsNoGoods.setVisibility(View.GONE);
            secondaryDetailsRec.setVisibility(View.VISIBLE);
        }
    }
}
