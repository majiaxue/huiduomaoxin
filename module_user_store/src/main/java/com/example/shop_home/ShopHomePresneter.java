package com.example.shop_home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mvp.BasePresenter;
import com.example.shop_home.adapter.ShopHomeVPAdapter;
import com.example.shop_home.first_page.ShopFirstFragment;
import com.example.shop_home.treasure.ShopTreasureFragment;
import com.example.utils.OnClearCacheListener;
import com.example.utils.PopUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ShopHomePresneter extends BasePresenter<ShopHomeView> {
    private String[] titleArr = {"首页", "宝贝"};
    private List<Fragment> fragmentList = new ArrayList<>();

    public ShopHomePresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTabLayout(final TabLayout intoShopTab) {
        for (String title : titleArr) {
            intoShopTab.addTab(intoShopTab.newTab().setText(title));
        }

        intoShopTab.addTab(intoShopTab.newTab().setText("首页"));
        intoShopTab.addTab(intoShopTab.newTab().setText("宝贝"));

        fragmentList.add(new ShopFirstFragment());
        fragmentList.add(new ShopTreasureFragment());

        intoShopTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) intoShopTab.getChildAt(0);

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

    public void initViewPager(FragmentManager fm) {
        ShopHomeVPAdapter intoShopVPAdapter = new ShopHomeVPAdapter(fm, fragmentList, titleArr);
        getView().loadVP(intoShopVPAdapter);
    }

    public void showMore(ImageView more) {
        PopUtil.showMore(mContext, more, new OnClearCacheListener() {
            @Override
            public void setOnClearCache(final PopupWindow pop, View confirm) {
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
            }
        });
    }
}
