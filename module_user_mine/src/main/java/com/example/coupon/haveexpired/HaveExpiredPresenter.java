package com.example.coupon.haveexpired;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.coupon.adapter.CouponAdapter;
import com.example.coupon.adapter.HaveExpiredAdapter;
import com.example.coupon.bean.CouponBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class HaveExpiredPresenter extends BasePresenter<HaveExpiredView> {

    private List<CouponBean> couponBeansList;

    public HaveExpiredPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void haveExpiredRec(RecyclerView haveExpiredRec){
        couponBeansList = new ArrayList<>();
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_104,"5","满50元可使用","有效期至  2019/05/28"));
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_105,"5","满50元可使用","有效期至  2019/05/28"));
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_106,"5","满50元可使用","有效期至  2019/05/28"));
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_104,"5","满50元可使用","有效期至  2019/05/28"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        haveExpiredRec.setLayoutManager(linearLayoutManager);
        HaveExpiredAdapter haveExpiredAdapter = new HaveExpiredAdapter(mContext, couponBeansList, R.layout.item_have_expired_rec);
        haveExpiredRec.setAdapter(haveExpiredAdapter);
        haveExpiredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int position) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
