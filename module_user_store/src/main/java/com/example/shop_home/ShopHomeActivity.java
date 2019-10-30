package com.example.shop_home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.mvp.BaseFragmentActivity;
import com.example.shop_home.adapter.ShopHomeVPAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

@Route(path = "/module_user_store/ShopHomeActivity")
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
    @Autowired(name = "sellerId")
    String shop_id;
    @Autowired(name = "shop_name")
    String shop_name;
    @Autowired(name = "shop_icon")
    String shop_icon;
    @Autowired(name = "type")
    int type;


    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_home;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        Intent intent = getIntent();
        if (1 != type) {
            shop_name = intent.getStringExtra("shop_name");
            shop_icon = intent.getStringExtra("shop_icon");
            shop_id = intent.getStringExtra("shop_id");
        }
        String number = intent.getStringExtra("number");
        Glide.with(this).load(shop_icon).into(shopHomeStoreImage);
        shopHomeStoreName.setText(shop_name);
        shopHomeStoreCollectNumber.setText(number == null ? "0" : number + "收藏");

//        presenter.initTabLayout(shopHomeTab, shop_id);
        presenter.initViewPager(getSupportFragmentManager(), shop_id);

        shopHomeVp.setOffscreenPageLimit(1);
        shopHomeTab.setupWithViewPager(shopHomeVp);

        presenter.isCollect(shop_id);
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

        shopHomeCollectStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.collectShop(shop_id);
            }
        });
    }

    @Override
    public void isCollect(String result) {
        if ("true".equals(result)) {
            shopHomeCollectStore.setText("取消收藏");
        } else if ("false".equals(result)) {
            shopHomeCollectStore.setText("收藏");
        }
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
