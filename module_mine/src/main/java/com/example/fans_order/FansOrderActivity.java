package com.example.fans_order;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.order.adapter.OrderVPAdapter;

import butterknife.BindView;

@Route(path = "/mine/fansorder")
public class FansOrderActivity extends BaseFragmentActivity<FansOrderView, FansOrderPresenter> implements FansOrderView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.fans_order_txt1)
    TextView fansOrderTxt1;
    @BindView(R2.id.fans_order_txt2)
    TextView fansOrderTxt2;
    @BindView(R2.id.fans_order_txt3)
    TextView fansOrderTxt3;
    @BindView(R2.id.fans_order_tab)
    TabLayout fansOrderTab;
    @BindView(R2.id.fans_order_viewpager)
    ViewPager fansOrderViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fans_order;
    }

    @Override
    public void initData() {
        includeTitle.setText("粉丝订单");
        fansOrderTab.setupWithViewPager(fansOrderViewpager);
        presenter.initTabLayout(fansOrderTab);
        presenter.initViewPager(getSupportFragmentManager());
        fansOrderViewpager.setOffscreenPageLimit(3);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void updateVP(OrderVPAdapter adapter) {
        fansOrderViewpager.setAdapter(adapter);
    }

    @Override
    public FansOrderView createView() {
        return this;
    }

    @Override
    public FansOrderPresenter createPresenter() {
        return new FansOrderPresenter(this);
    }
}
