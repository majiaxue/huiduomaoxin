package com.example.productdetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.ProductCenterBean;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.productdetail.adapter.ProductAccountAdapter;
import com.stx.xhb.xbanner.XBanner;

import butterknife.BindView;

public class ProductDetailActivity extends BaseActivity<ProductDetailView, ProductDetailPresenter> implements ProductDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.product_detail_xbanner)
    XBanner productDetailXbanner;
    @BindView(R2.id.product_detail_goods_type)
    TextView productDetailGoodsType;
    @BindView(R2.id.product_detail_goods_name)
    TextView productDetailGoodsName;
    @BindView(R2.id.product_detail_goods_price)
    TextView productDetailGoodsPrice;
    @BindView(R2.id.product_detail_goods_introduce)
    TextView productDetailGoodsIntroduce;
    @BindView(R2.id.product_detail_rv)
    RecyclerView productDetailRv;
    @BindView(R2.id.product_detail_webview)
    WebView productDetailWebview;
    @BindView(R2.id.product_detail_liuyan)
    LinearLayout productDetailLiuyan;
    @BindView(R2.id.product_detail_zixun)
    LinearLayout productDetailZixun;

    @Autowired(name = "bean")
    ProductCenterBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("产品详情");
        productDetailGoodsName.setText(bean.getName());
        productDetailGoodsPrice.setText("￥" + bean.getPrice());
        productDetailGoodsIntroduce.setText(bean.getInfo());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productDetailRv.setLayoutManager(linearLayoutManager);

        presenter.loadData(bean);
        presenter.loadPhone();
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
    public void loadRv(ProductAccountAdapter adapter) {
        productDetailRv.setAdapter(adapter);
    }

    @Override
    public ProductDetailView createView() {
        return this;
    }

    @Override
    public ProductDetailPresenter createPresenter() {
        return new ProductDetailPresenter(this);
    }
}
