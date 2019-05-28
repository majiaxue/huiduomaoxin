package com.example.shop_home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvp.BaseFragmentActivity;
import com.example.shop_home.adapter.ShopHomeVPAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

public class ShopHomeActivity extends BaseFragmentActivity<ShopHomeView, ShopHomePresneter> implements ShopHomeView {
    @BindView(R2.id.shop_home_back)
    ImageView shopHomeBack;
    @BindView(R2.id.shop_home_search)
    TextView shopHomeSearch;
    @BindView(R2.id.shop_home_store_image)
    ImageView shopHomeStoreImage;
    @BindView(R2.id.shop_home_store_name)
    TextView shopHomeStoreName;
    @BindView(R2.id.shop_home_store_collect_number)
    TextView shopHomeStoreCollectNumber;
    @BindView(R2.id.shop_home_collect_store)
    TextView shopHomeCollectStore;
    @BindView(R2.id.shop_home_tab)
    TabLayout shopHomeTab;
    @BindView(R2.id.shop_home_vp)
    ViewPager shopHomeVp;
    @BindView(R2.id.shop_home_more)
    ImageView mMore;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_home;
    }

    @Override
    public void initData() {
        presenter.initTabLayout(shopHomeTab);
        presenter.initViewPager(getSupportFragmentManager());

        shopHomeVp.setOffscreenPageLimit(2);
        shopHomeTab.setupWithViewPager(shopHomeVp);
    }

    @Override
    public void initClick() {
        shopHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shopHomeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showMore(mMore);
            }
        });
    }

    @Override
    public void loadVP(ShopHomeVPAdapter adapter) {
        shopHomeVp.setAdapter(adapter);
    }

    @Override
    public ShopHomeView createView() {
        return this;
    }

    @Override
    public ShopHomePresneter createPresenter() {
        return new ShopHomePresneter(this);
    }
}
