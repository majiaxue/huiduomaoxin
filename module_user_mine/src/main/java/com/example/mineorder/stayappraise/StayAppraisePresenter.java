package com.example.mineorder.stayappraise;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.mineorder.stayappraise.adapter.StayAppraiseParentAdapter;
import com.example.mineorder.stayappraise.bean.StayAppraiseParentBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class StayAppraisePresenter extends BasePresenter<StayAppraiseView> {

    public StayAppraisePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void stayAppraiseRec(RecyclerView stayAppraiseRec) {

        List<StayAppraiseParentBean> parentBeanList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parentBeanList.add(new StayAppraiseParentBean("维纳塔旗舰店", "交易成功"));
            parentBeanList.add(new StayAppraiseParentBean("维纳塔旗舰店", "交易成功"));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        stayAppraiseRec.setLayoutManager(linearLayoutManager);
        StayAppraiseParentAdapter stayAppraiseParentAdapter = new StayAppraiseParentAdapter(mContext, parentBeanList, R.layout.item_stay_appraise_parent);
        stayAppraiseRec.setAdapter(stayAppraiseParentAdapter);

        stayAppraiseParentAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //去店铺

                    }
                });
            }
        });
    }
}
