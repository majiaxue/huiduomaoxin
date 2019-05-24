package com.example.user_mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.entity.EventBusBean;
import com.example.mvp.BaseFragment;
import com.example.user_store.R;
import com.example.user_store.R2;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:商城我的页面
 */
public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    @BindView(R2.id.user_mine_image_back)
    ImageView userMineImageBack;
    @BindView(R2.id.mine_header)
    ImageView mineHeader;
    @BindView(R2.id.user_mine_icon)
    RelativeLayout userMineIcon;
    @BindView(R2.id.user_mine_name)
    TextView userMineName;
    @BindView(R2.id.user_mine_id)
    TextView userMineId;
    @BindView(R2.id.user_mine_goods_collection)
    LinearLayout userMineGoodsCollection;
    @BindView(R2.id.user_mine_shop_collect)
    LinearLayout userMineShopCollect;
    @BindView(R2.id.user_mine_browsing_history)
    LinearLayout userMineBrowsingHistory;
    @BindView(R2.id.user_mine_my_order)
    LinearLayout userMineMyOrder;
    @BindView(R2.id.user_mine_daifukuan)
    LinearLayout userMineDaifukuan;
    @BindView(R2.id.user_mine_daifahuo)
    LinearLayout userMineDaifahuo;
    @BindView(R2.id.user_mine_daishouhuo)
    LinearLayout userMineDaishouhuo;
    @BindView(R2.id.user_mine_daipingjia)
    LinearLayout userMineDaipingjia;
    @BindView(R2.id.user_mine_shouhou)
    LinearLayout userMineShouhou;
    @BindView(R2.id.user_mine_up_yys)
    ImageView userMineUpYys;
    @BindView(R2.id.user_mine_business_application)
    LinearLayout userMineBusinessApplication;
    @BindView(R2.id.user_mine_discount_coupon)
    LinearLayout userMineDiscountCoupon;
    @BindView(R2.id.user_mine_shipping_address)
    LinearLayout userMineShippingAddress;
    @BindView(R2.id.user_mine_message_notification)
    LinearLayout userMineMessageNotification;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_mine;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        userMineImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean("user_back"));
            }
        });
        //商品收藏
        userMineGoodsCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/GoodsCollectionActivity").navigation();
            }
        });
        //收货地址
        userMineShippingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").navigation();
            }
        });


    }

    @Override
    public MineView createView() {
        return this;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getContext());
    }

}
