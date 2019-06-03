package com.example.order;

import android.graphics.Color;
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
    @BindView(R2.id.order_back)
    ImageView mBack;
    @BindView(R2.id.order_tb)
    TextView orderTb;
    @BindView(R2.id.order_pdd)
    TextView orderPdd;
    @BindView(R2.id.order_jd)
    TextView orderJd;
    @BindView(R2.id.order_sc)
    TextView orderSc;
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
        orderTab.setupWithViewPager(orderViewpager);
        presenter.initTabLayout(orderTab);
        presenter.initViewPager(getSupportFragmentManager());
        orderViewpager.setOffscreenPageLimit(3);
        orderViewpager.setCurrentItem(type);
        orderTab.getTabAt(type).select();

    }

    @Override
    public void initClick() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(0);
            }
        });

        orderPdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(1);
            }
        });

        orderJd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(2);
            }
        });

        orderSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(3);
            }
        });

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
    }

    @Override
    public void updateVP(OrderVPAdapter adapter) {
        orderViewpager.setAdapter(adapter);
    }

    @Override
    public void typeChanged(int position) {
        orderTb.setTextColor(Color.parseColor(position == 0 ? "#ffffff" : "#222222"));
        orderPdd.setTextColor(Color.parseColor(position == 1 ? "#ffffff" : "#222222"));
        orderJd.setTextColor(Color.parseColor(position == 2 ? "#ffffff" : "#222222"));
        orderSc.setTextColor(Color.parseColor(position == 3 ? "#ffffff" : "#222222"));

        orderTb.setBackgroundResource(position == 0 ? R.drawable.predict_xuan_left : 0);
        orderPdd.setBackgroundResource(position == 1 ? R.drawable.predict_xuan : 0);
        orderJd.setBackgroundResource(position == 2 ? R.drawable.predict_xuan : 0);
        orderSc.setBackgroundResource(position == 3 ? R.drawable.predict_xuan_right : 0);
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
