package com.example.order_info;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.LocalOrderBean;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseActivity;
import com.example.order_info.adapter.OrderInfoAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/module_local/OrderInfoActivity")
public class OrderInfoActivity extends BaseActivity<OrderInfoView, OrderInfoPresenter> implements OrderInfoView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_info_shop_name)
    TextView orderInfoShopName;
    @BindView(R2.id.order_info_rv)
    RecyclerView orderInfoRv;
    @BindView(R2.id.order_info_peisong)
    TextView orderInfoPeisong;
    @BindView(R2.id.order_info_peisong_money)
    TextView orderInfoPeisongMoney;
    @BindView(R2.id.order_info_manjian_money)
    TextView orderInfoManjianMoney;
    @BindView(R2.id.order_info_total_money)
    TextView orderInfoTotalMoney;
    @BindView(R2.id.order_info_address)
    TextView orderInfoAddress;
    @BindView(R2.id.order_info_ordersn)
    TextView orderInfoOrdersn;
    @BindView(R2.id.order_info_time)
    TextView orderInfoTime;
    @BindView(R2.id.order_info_pay_type)
    TextView orderInfoPayType;

    @Autowired(name = "bean")
    LocalOrderBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_info;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("订单详情");
        orderInfoShopName.setText(bean.getSellerInfo().getShop_name());
        orderInfoManjianMoney.setText("-￥" + bean.getSellerInfo().getAmount());
        orderInfoTotalMoney.setText("￥" + bean.getTotalMoney());
        orderInfoAddress.setText(bean.getUserAddress() + "\n" + bean.getUserName() + "      " + bean.getUserPhone());
        orderInfoOrdersn.setText(bean.getOrderSn());
        orderInfoTime.setText(bean.getCreateTime());
        orderInfoPayType.setText("0".equals(bean.getPayWay()) ? "微信" : "支付宝");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orderInfoRv.setLayoutManager(layoutManager);
        orderInfoRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_14)));

        presenter.loadData(bean.getLocalOrderItemList());
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
    public void loadRv(OrderInfoAdapter adapter) {
        orderInfoRv.setAdapter(adapter);
    }

    @Override
    public OrderInfoView createView() {
        return this;
    }

    @Override
    public OrderInfoPresenter createPresenter() {
        return new OrderInfoPresenter(this);
    }
}
