package com.example.order_confirm;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.bean.AddressBean;
import com.example.bean.OrderConfirmBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;

import butterknife.BindView;

@Route(path = "/user/order_confirm")
public class OrderConfirmActivity extends BaseActivity<OrderConfirmView, OrderConfirmPresneter> implements OrderConfirmView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_confirm_rela)
    RelativeLayout mRela;
    @BindView(R2.id.order_confirm_name)
    TextView orderConfirmName;
    @BindView(R2.id.order_confirm_phone)
    TextView orderConfirmPhone;
    @BindView(R2.id.order_confirm_choose_address)
    TextView orderConfirmChooseAddress;
    @BindView(R2.id.order_confirm_shop_name)
    TextView orderConfirmShopName;
    @BindView(R2.id.order_confirm_goshop)
    LinearLayout orderConfirmGoshop;
    @BindView(R2.id.order_confirm_img)
    ImageView orderConfirmImg;
    @BindView(R2.id.order_confirm_goods)
    TextView orderConfirmGoods;
    @BindView(R2.id.order_confirm_color)
    TextView orderConfirmColor;
    @BindView(R2.id.order_confirm_price)
    TextView orderConfirmPrice;
    @BindView(R2.id.order_confirm_minus)
    TextView orderConfirmMinus;
    @BindView(R2.id.order_confirm_count)
    TextView orderConfirmCount;
    @BindView(R2.id.order_confirm_add)
    TextView orderConfirmAdd;
    @BindView(R2.id.order_confirm_delivery_txt1)
    TextView orderConfirmDeliveryTxt1;
    @BindView(R2.id.order_confirm_delivery_txt2)
    TextView orderConfirmDeliveryTxt2;
    @BindView(R2.id.order_confirm_delivery)
    LinearLayout orderConfirmDelivery;
    @BindView(R2.id.order_confirm_coupon_txt)
    TextView orderConfirmCouponTxt;
    @BindView(R2.id.order_confirm_coupon)
    LinearLayout orderConfirmCoupon;
    @BindView(R2.id.order_confirm_edit)
    EditText orderConfirmEdit;
    @BindView(R2.id.order_confirm_goods_count)
    TextView orderConfirmGoodsCount;
    @BindView(R2.id.order_confirm_xiaoji)
    TextView orderConfirmXiaoji;
    @BindView(R2.id.order_confirm_total_price)
    TextView orderConfirmTotalPrice;
    @BindView(R2.id.order_confirm_total_yunfei)
    TextView orderConfirmTotalYunfei;
    @BindView(R2.id.order_confirm_total_coupon)
    TextView orderConfirmTotalCoupon;
    @BindView(R2.id.order_confirm_final_price)
    TextView orderConfirmFinalPrice;
    @BindView(R2.id.order_confirm_submit)
    TextView orderConfirmSubmit;

    private OrderConfirmBean order;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_confirm;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        order = (OrderConfirmBean) intent.getSerializableExtra("order");
        LogUtil.e("确认：" + order);
        includeTitle.setText("确认订单");
        orderConfirmShopName.setText(order.getSellerName());
        Glide.with(this).load(order.getPic()).into(orderConfirmImg);
        orderConfirmGoods.setText(order.getGoodsName());
        orderConfirmColor.setText("颜色：" + order.getSp1() + "，" + "尺码：" + order.getSp2());
        orderConfirmPrice.setText(order.getPrice() + "");
        orderConfirmCount.setText(order.getQuantity() + "");
        orderConfirmGoodsCount.setText("共" + order.getQuantity() + "件");
        orderConfirmXiaoji.setText("￥" + ArithUtil.mul(order.getPrice(), order.getQuantity()));
        orderConfirmTotalPrice.setText("￥" + ArithUtil.mul(order.getPrice(), order.getQuantity()));

        presenter.loadData(order);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderConfirmGoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShop(order.getSellerId());
            }
        });

        orderConfirmChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });

        mRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });

        orderConfirmMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.minusCount();
            }
        });

        orderConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addCount();
            }
        });

        orderConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submit(orderConfirmEdit.getText().toString());
            }
        });
    }

    @Override
    public void noAddress() {
        orderConfirmChooseAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadAddress(AddressBean addressBean) {
        orderConfirmName.setText(addressBean.getAddressName());
        orderConfirmPhone.setText(addressBean.getAddressPhone());
        orderConfirmChooseAddress.setText(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());
        presenter.getPostage(order);
    }

    @Override
    public void reviseCount(OrderConfirmBean orderConfirmBean) {
        orderConfirmCount.setText(orderConfirmBean.getQuantity() + "");
        orderConfirmGoodsCount.setText("共" + orderConfirmBean.getQuantity() + "件");
        orderConfirmXiaoji.setText("￥" + ArithUtil.mul(orderConfirmBean.getPrice(), orderConfirmBean.getQuantity()));
        orderConfirmTotalPrice.setText("￥" + ArithUtil.mul(orderConfirmBean.getPrice(), orderConfirmBean.getQuantity()));
    }

    @Override
    public OrderConfirmView createView() {
        return this;
    }

    @Override
    public OrderConfirmPresneter createPresenter() {
        return new OrderConfirmPresneter(this);
    }
}
