package com.example.local_detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.BaseVPAdapter;
import com.example.bean.LocalShopBean;
import com.example.bean.UserCouponBean;
import com.example.local_coupon.LocalCouponActivity;
import com.example.local_detail.local_goods.LocalGoodsFragment;
import com.example.local_detail.local_seller.LocalSellerFragment;
import com.example.local_shop.adapter.ManJianAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.utils.PopUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LocalDetailPresenter extends BasePresenter<LocalDetailView> {
    private String[] titleArr = {"商品", "商家"};
    private LocalGoodsFragment goodsFragment;
    private LocalSellerFragment sellerFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ManJianAdapter manJianAdapter;

    public LocalDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTab(final TabLayout tab) {
        tab.addTab(tab.newTab().setText(titleArr[0]));
        tab.addTab(tab.newTab().setText(titleArr[1]));

        tab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) tab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    public void initVp(FragmentManager fm, LocalShopBean bean) {
        goodsFragment = LocalGoodsFragment.getInstance(bean.getId());
        sellerFragment = LocalSellerFragment.getInstance(bean);
        fragmentList.add(goodsFragment);
        fragmentList.add(sellerFragment);

        BaseVPAdapter vpAdapter = new BaseVPAdapter(fm, fragmentList, titleArr);
        getView().updateVP(vpAdapter);
    }

    public void loadData() {
        List<String> list = new ArrayList<>();
        list.add("满100减10元");
        list.add("满200减30元");
        manJianAdapter = new ManJianAdapter(mContext, list, R.layout.rv_local_seller_inside);
        if (getView() != null) {
            getView().loadManJian(manJianAdapter);
        }
    }

    public void lingquan() {
        List<UserCouponBean> couponList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            UserCouponBean couponBean = new UserCouponBean();
            couponBean.setAmount(10);
            couponBean.setMinPoint(30);
            couponBean.setEnableTime("2020-10-1");
            couponBean.setHas(false);
            couponList.add(couponBean);
        }
        PopUtil.lingquanPop(mContext, couponList);
    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:18503735075");
        intent.setData(data);
        mContext.startActivity(intent);
    }

    public void jumpToCoupon() {
        Intent intent = new Intent(mContext, LocalCouponActivity.class);
        mContext.startActivity(intent);
    }
}
