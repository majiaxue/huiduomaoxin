package com.example.alteration;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.alteration.adapter.AlterationAdapter;
import com.example.alteration.bean.AlterationBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class AlterationPresenter extends BasePresenter<AlterationView> {

    public AlterationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void alterationRec(RecyclerView alterationRec) {
        List<AlterationBean> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(new AlterationBean("private简约男装", R.drawable.img_108, "原宿潮牌短袖T恤男夏季新款休闲欧美bf风青少年韩版宽松大码T恤", "白色", "XL", "X1", "仅退款", "退款成功"));
            list.add(new AlterationBean("private简约男装", R.drawable.img_109, "原宿潮牌短袖T恤男夏季新款休闲欧美bf风青少年韩版宽松大码T恤", "白色", "XL", "X1", "仅退款", "退款成功"));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        alterationRec.setLayoutManager(linearLayoutManager);
        AlterationAdapter alterationAdapter = new AlterationAdapter(mContext, list, R.layout.item_alteration_rec);
        alterationRec.setAdapter(alterationAdapter);
        alterationAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int position) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //查看详情
                        ARouter.getInstance().build("/module_user_mine/RefundParticularsActivity").navigation();
                    }
                });
            }
        });
    }
}
