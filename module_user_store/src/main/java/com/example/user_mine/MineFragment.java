package com.example.user_mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.common.CommonResource;
import com.example.confirm_order.ConfirmOrderActivity;
import com.example.entity.EventBusBean;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragment;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_mine.bean.ApplicationBean;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

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
    @BindView(R2.id.goods_collection_count)
    TextView goodsCollectionCount;
    @BindView(R2.id.shop_collect_count)
    TextView shopCollectCount;
    @BindView(R2.id.browsing_history_count)
    TextView browsingHistoryCount;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_mine;
    }

    @Override
    public void initData() {
        userMineName.setText(SPUtil.getUserCode());
    }

    @Override
    public void initClick() {
        userMineImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean2(CommonResource.USER_BACK, 0));
            }
        });
        //商品收藏
        userMineGoodsCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/GoodsCollectionActivity").navigation();
            }
        });
        //店铺收藏
        userMineShopCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/ShopCollectActivity").navigation();
            }
        });
        //收货地址
        userMineShippingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").navigation();
            }
        });
        //商家申请
        userMineBusinessApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.businessApplication();
            }
        });
        //消息通知
        userMineMessageNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });
        //优惠劵
        userMineDiscountCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/CouponActivity").navigation();
            }
        });
        //全部订单
        userMineMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/MineOrderActivity").withInt("type", 0).navigation();
            }
        });
        //待付款
        userMineDaifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/MineOrderActivity").withInt("type", 1).navigation();
            }
        });
        //待发货
        userMineDaifahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/MineOrderActivity").withInt("type", 2).navigation();
            }
        });
        //待收货
        userMineDaishouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/MineOrderActivity").withInt("type", 3).navigation();
            }
        });
        //待评价
        userMineDaipingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/MineOrderActivity").withInt("type", 4).navigation();
            }
        });
        //退货/售后
        userMineShouhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/AlterationActivity").navigation();
            }
        });
        //浏览记录
        userMineBrowsingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/BrowsingHistoryActivity").navigation();
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

    @Override
    public void browsingHistoryCount(int count) {
        if (count > 99) {
            browsingHistoryCount.setText(count + "+");
        } else {
            browsingHistoryCount.setText(count + "");
        }
    }

    @Override
    public void shopCollectCount(int count) {
        shopCollectCount.setText(count + "");
    }

    @Override
    public void goodsCollectionCount(int count) {
        goodsCollectionCount.setText(count + "");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            presenter.goodsCollectionCount();
            presenter.shopCollectCount();
            presenter.browsingHistoryCount();
        }
    }
}
