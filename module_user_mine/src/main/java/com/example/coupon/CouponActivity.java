package com.example.coupon;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.adapter.BaseVPAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragmentActivity;

import butterknife.BindView;

/**
 * 优惠劵
 */
@Route(path = "/module_user_mine/CouponActivity")
public class CouponActivity extends BaseFragmentActivity<CouponView, CouponPresenter> implements CouponView {


    @BindView(R2.id.coupon_back)
    ImageView couponBack;
    @BindView(R2.id.coupon_tab)
    TabLayout couponTab;
    @BindView(R2.id.coupon_vp)
    ViewPager couponVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    public void initData() {
        //初始化tablayout
        presenter.initTabLayout(couponTab);
        presenter.initViewPager(getSupportFragmentManager());
        //预加载
        couponVp.setOffscreenPageLimit(2);
        //tablayout联动viewpager
        couponTab.setupWithViewPager(couponVp);
    }

    @Override
    public void initClick() {
        couponBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public CouponView createView() {
        return this;
    }

    @Override
    public CouponPresenter createPresenter() {
        return new CouponPresenter(this);
    }

    @Override
    public void updateVp(BaseVPAdapter baseVPAdapter) {
        couponVp.setAdapter(baseVPAdapter);
    }
}
