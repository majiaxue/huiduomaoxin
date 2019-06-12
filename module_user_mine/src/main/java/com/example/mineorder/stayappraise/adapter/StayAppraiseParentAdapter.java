package com.example.mineorder.stayappraise.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.mineorder.bean.MineOrderBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/30
 * Describe:
 */
public class StayAppraiseParentAdapter extends MyRecyclerAdapter<MineOrderBean.OrderListBean> {

    private RecyclerView stayAppraiseChildRec;
    private LinearLayoutManager linearLayoutManager;
    private StayAppraiseChildAdapter stayAppraiseChildAdapter;

    public StayAppraiseParentAdapter(Context context, List<MineOrderBean.OrderListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MineOrderBean.OrderListBean data, int position) {
        if(data.getStatus() == 3) {
            //3待评论
            holder.setText(R.id.mine_order_parent_status, "交易成功");
            holder.setText(R.id.mine_order_parent_btn_left, "再次购买");
            holder.setText(R.id.mine_order_parent_btn_right, "立即评价");
        }
        holder.setText(R.id.stay_appraise_parent_shop, data.getSellerName());

        viewOnClickListener.ViewOnClick(holder.getView(R.id.stay_appraise_parent_shop),position);

        stayAppraiseChildRec = holder.getView(R.id.stay_appraise_child_rec);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        stayAppraiseChildRec.setLayoutManager(linearLayoutManager);
        stayAppraiseChildAdapter = new StayAppraiseChildAdapter(context, data.getOrderItems(), R.layout.item_stay_appraise_child);
        stayAppraiseChildRec.setAdapter(stayAppraiseChildAdapter);

        stayAppraiseChildAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, int position) {

            }
        });
    }
}
