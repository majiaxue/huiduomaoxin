package com.example.coupon.all;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.coupon.adapter.CouponAdapter;
import com.example.coupon.bean.CouponBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class AllPresenter extends BasePresenter<AllView> {

    private List<CouponBean> couponBeansList;

    public AllPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void allRec(RecyclerView allRec){
        couponBeansList = new ArrayList<>();
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_104,"5","满50元可使用","有效期至  2019/05/28"));
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_105,"5","满50元可使用","有效期至  2019/05/28"));
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_106,"5","满50元可使用","有效期至  2019/05/28"));
        couponBeansList.add(new CouponBean("维纳塔旗舰店", R.drawable.img_104,"5","满50元可使用","有效期至  2019/05/28"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        allRec.setLayoutManager(linearLayoutManager);
        CouponAdapter couponAdapter = new CouponAdapter(mContext, couponBeansList, R.layout.item_coupon_rec);
        allRec.setAdapter(couponAdapter);

        couponAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
//                        ARouter.getInstance().build("").navigation();
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
