package com.example.mineorder.stayappraise.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.mineorder.stayappraise.bean.StayAppraiseChildBean;
import com.example.mineorder.stayappraise.bean.StayAppraiseParentBean;
import com.example.module_user_mine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/30
 * Describe:
 */
public class StayAppraiseParentAdapter extends MyRecyclerAdapter<StayAppraiseParentBean> {

    private RecyclerView stayAppraiseChildRec;
    private LinearLayoutManager linearLayoutManager;
    private StayAppraiseChildAdapter stayAppraiseChildAdapter;

    public StayAppraiseParentAdapter(Context context, List<StayAppraiseParentBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, StayAppraiseParentBean data, int position) {
        holder.setText(R.id.stay_appraise_parent_shop, data.getShopName());
        holder.setText(R.id.stay_appraise_parent_status, data.getStatus());

        viewOnClickListener.ViewOnClick(holder.getView(R.id.stay_appraise_parent_shop),position);

        List<StayAppraiseChildBean> childBeanList = new ArrayList<>();
        childBeanList.add(new StayAppraiseChildBean(R.drawable.img_108, "【女王价】学生适用清爽护肤纯露保湿水俩件套", "保湿水80ml [送两包试用装]", "￥163.0", "x1", "共1件商品  合计：￥163.0"));
        childBeanList.add(new StayAppraiseChildBean(R.drawable.img_108, "【女王价】学生适用清爽护肤纯露保湿水俩件套", "保湿水80ml [送两包试用装]", "￥163.0", "x1", "共1件商品  合计：￥163.0"));
        stayAppraiseChildRec = holder.getView(R.id.stay_appraise_child_rec);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        stayAppraiseChildRec.setLayoutManager(linearLayoutManager);
        stayAppraiseChildAdapter = new StayAppraiseChildAdapter(context, childBeanList, R.layout.item_stay_appraise_child);
        stayAppraiseChildRec.setAdapter(stayAppraiseChildAdapter);

        stayAppraiseChildAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, int position) {

            }
        });
    }
}
