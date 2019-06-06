package com.example.shopcollect;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.view.SlideRecyclerView;

import butterknife.BindView;

/**
 * 店铺收藏
 */
@Route(path = "/module_user_mine/ShopCollectActivity")
public class ShopCollectActivity extends BaseActivity<ShopCollectView, ShopCollectPresenter> implements ShopCollectView {


    @BindView(R2.id.shop_collect_back)
    ImageView shopCollectBack;
    @BindView(R2.id.shop_collect_rec)
    SlideRecyclerView shopCollectRec;
    @BindView(R2.id.shop_collect_bottom_rec)
    RecyclerView shopCollectBottomRec;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_collect;
    }

    @Override
    public void initData() {
        //店铺
        presenter.initShopCollectRec(shopCollectRec);
        //为你推荐
        presenter.shopCollectBottomRec(shopCollectBottomRec);
    }

    @Override
    public void initClick() {
        shopCollectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public ShopCollectView createView() {
        return this;
    }

    @Override
    public ShopCollectPresenter createPresenter() {
        return new ShopCollectPresenter(this);
    }

}
