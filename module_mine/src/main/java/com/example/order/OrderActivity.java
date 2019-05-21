package com.example.order;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.order.adapter.OrderVPAdapter;
import com.example.utils.LogUtil;

import butterknife.BindView;

@Route(path = "/mine/order")
public class OrderActivity extends BaseFragmentActivity<OrderView, OrderPresenter> implements OrderView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_tab)
    TabLayout orderTab;
    @BindView(R2.id.order_viewpager)
    ViewPager orderViewpager;
    @Autowired(name = "type")
    int type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("我的订单");
        orderTab.setupWithViewPager(orderViewpager);
        presenter.initTabLayout(orderTab);
        presenter.initViewPager(getSupportFragmentManager());
        orderViewpager.setOffscreenPageLimit(3);
        orderViewpager.setCurrentItem(type);
        orderTab.getTabAt(type).select();

    }

    @Override
    public void initClick() {
        orderTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                presenter.TabScoll(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void updateVP(OrderVPAdapter adapter) {
        orderViewpager.setAdapter(adapter);
    }

    @Override
    public OrderView createView() {
        return this;
    }

    @Override
    public OrderPresenter createPresenter() {
        return new OrderPresenter(this);
    }
}
