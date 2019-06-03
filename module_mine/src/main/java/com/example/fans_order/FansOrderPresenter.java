package com.example.fans_order;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp.BasePresenter;
import com.example.order.adapter.OrderVPAdapter;
import com.example.order.fragment_all.AllOrderFragment;
import com.example.order.fragment_lose.LoseOrderFragment;
import com.example.order.fragment_pay.PayOrderFragment;
import com.example.order.fragment_settle.SettleOrderFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FansOrderPresenter extends BasePresenter<FansOrderView> {
    private String[] titleArr = {"全部订单", "已付款", "已结算", "已失效"};
    private List<Fragment> fragmentList = new ArrayList<>();

    public FansOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTabLayout(final TabLayout orderTab) {
        fragmentList.add(AllOrderFragment.getInstance("fans"));
        fragmentList.add(PayOrderFragment.getInstance("fans"));
        fragmentList.add(SettleOrderFragment.getInstance("fans"));
        fragmentList.add(LoseOrderFragment.getInstance("fans"));

        orderTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) orderTab.getChildAt(0);

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
        OrderVPAdapter vpAdapter = new OrderVPAdapter(fm, fragmentList, titleArr);
        getView().updateVP(vpAdapter);
    }

    public void change(int i) {
        getView().typeChanged(i);
    }
}
