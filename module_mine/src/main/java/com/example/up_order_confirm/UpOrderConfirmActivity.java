package com.example.up_order_confirm;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.OrderConfirmBean;
import com.example.bean.ShippingAddressBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class UpOrderConfirmActivity extends BaseActivity<UpOrderConfirmView, UpOrderConfirmPresenter> implements UpOrderConfirmView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.up_order_name)
    TextView upOrderName;
    @BindView(R2.id.up_order_phone)
    TextView upOrderPhone;
    @BindView(R2.id.up_order_address_detail)
    TextView upOrderAddressDetail;
    @BindView(R2.id.up_order_choose_address)
    TextView upOrderChooseAddress;
    @BindView(R2.id.up_order_rela)
    RelativeLayout upOrderRela;
    @BindView(R2.id.up_order_img)
    ImageView upOrderImg;
    @BindView(R2.id.up_order_goods_name)
    TextView upOrderGoodsName;
    @BindView(R2.id.up_order_attr)
    TextView upOrderAttr;
    @BindView(R2.id.up_order_price)
    TextView upOrderPrice;
    @BindView(R2.id.up_order_wx_img)
    ImageView upOrderWxImg;
    @BindView(R2.id.up_order_wx)
    LinearLayout upOrderWx;
    @BindView(R2.id.up_order_zfb_img)
    ImageView upOrderZfbImg;
    @BindView(R2.id.up_order_zfb)
    LinearLayout upOrderZfb;
    @BindView(R2.id.up_order_total)
    TextView upOrderTotal;
    @BindView(R2.id.up_order_btn)
    TextView upOrderBtn;

    private OrderConfirmBean confirmBean;
    private boolean isWeChat = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_up_order_confirm;
    }

    @Override
    public void initData() {
        includeTitle.setText("确认订单");

        Intent intent = getIntent();
        confirmBean = (OrderConfirmBean) intent.getSerializableExtra("order");
        Glide.with(this).load(confirmBean.getPic()).into(upOrderImg);
        upOrderGoodsName.setText(confirmBean.getProductName());
        upOrderAttr.setText(confirmBean.getProductAttr() == null ? "" : confirmBean.getProductAttr());
        upOrderPrice.setText("￥" + confirmBean.getPrice());
        upOrderTotal.setText(confirmBean.getPrice() + "");

        presenter.getAddress();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        upOrderRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });

        upOrderWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = true;
                upOrderWxImg.setImageResource(R.drawable.icon_xuanzhong);
                upOrderZfbImg.setImageResource(R.drawable.icon_weixuanzhong);
            }
        });

        upOrderZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = false;
                upOrderWxImg.setImageResource(R.drawable.icon_weixuanzhong);
                upOrderZfbImg.setImageResource(R.drawable.icon_xuanzhong);
            }
        });

        upOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commit(isWeChat);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            ShippingAddressBean addressBean = (ShippingAddressBean) data.getSerializableExtra("address");
            presenter.addressBean = addressBean;
            presenter.isCan = true;
            loadAddress(addressBean);
        }
    }

    @Override
    public void loadAddress(ShippingAddressBean addressBean) {
        upOrderChooseAddress.setVisibility(View.GONE);
        upOrderName.setText(addressBean.getAddressName());
        upOrderPhone.setText(addressBean.getAddressPhone());
        upOrderAddressDetail.setText(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());

    }

    @Override
    public void noAddress() {
        upOrderChooseAddress.setVisibility(View.VISIBLE);

    }

    @Override
    public UpOrderConfirmView createView() {
        return this;
    }

    @Override
    public UpOrderConfirmPresenter createPresenter() {
        return new UpOrderConfirmPresenter(this);
    }
}
