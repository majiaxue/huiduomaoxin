package com.example.mineorder.staydeliverygoods;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.mineorder.adapter.MineOrderAdapter;
import com.example.mineorder.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class StayDeliveryGoodsPresenter extends BasePresenter<StayDeliveryGoodsView> {

    public StayDeliveryGoodsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void stayDeliveryGoodsRec(RecyclerView stayDeliveryGoodsRec){
        List<MineOrderBean> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(new MineOrderBean("维纳塔旗舰店", R.drawable.img_104,"买家已付款","【女王价】学生适用清爽护肤纯露保湿水俩件套","保湿水80ml [送两包试用装]","￥163.0","x1","共2件商品  合计：￥163.0"));
            list.add(new MineOrderBean("维纳塔旗舰店", R.drawable.img_105,"买家已付款","【女王价】学生适用清爽护肤纯露保湿水俩件套","保湿水80ml [送两包试用装]","￥163.0","x1","共2件商品  合计：￥163.0"));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        stayDeliveryGoodsRec.setLayoutManager(linearLayoutManager);
        MineOrderAdapter mineOrderAdapter = new MineOrderAdapter(mContext, list, R.layout.item_order_rec);
        stayDeliveryGoodsRec.setAdapter(mineOrderAdapter);
        mineOrderAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
            @Override
            public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                //去店铺
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                //申请退款
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build("/module_user_mine/RefundActivity").navigation();
                    }
                });
                //发货
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
