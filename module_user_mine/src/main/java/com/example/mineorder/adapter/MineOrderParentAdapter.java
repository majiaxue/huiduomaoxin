package com.example.mineorder.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.mineorder.bean.MineOrderBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class MineOrderParentAdapter extends MyRecyclerAdapter<MineOrderBean.OrderListBean> {

    public MineOrderParentAdapter(Context context, List<MineOrderBean.OrderListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MineOrderBean.OrderListBean data, int position) {
        if (data.getStatus() == 1) {
            //1待发货
            holder.setText(R.id.mine_order_parent_status, "买家已付款");
            holder.setText(R.id.mine_order_parent_btn_left, "申请退款");
            holder.setText(R.id.mine_order_parent_btn_right, "提醒发货");
        } else if (data.getStatus() == 3) {
            //3待评论
            holder.setText(R.id.mine_order_parent_status, "交易成功");
            holder.setText(R.id.mine_order_parent_btn_left, "再次购买");
            holder.setText(R.id.mine_order_parent_btn_right, "立即评价");
        } else if (data.getStatus() == 6) {
            //6待付款
            holder.setText(R.id.mine_order_parent_status, "等待买家付款");
            holder.setText(R.id.mine_order_parent_btn_left, "删除订单");
            holder.setText(R.id.mine_order_parent_btn_right, "付款");
        } else if (data.getStatus() == 8) {
            //8待收货
            holder.setText(R.id.mine_order_parent_status, "卖家已发货");
            holder.setText(R.id.mine_order_parent_btn_left, "查看物流");
            holder.setText(R.id.mine_order_parent_btn_right, "确认收货");
        }

        holder.setText(R.id.mine_order_parent_shop, data.getSellerName());

        holder.setText(R.id.mine_order_parent_total, "共" + data.getTotalCount() + "件商品  合计：￥" + data.getTotalAmount());

        viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.mine_order_parent_shop), holder.getView(R.id.mine_order_parent_btn_left), holder.getView(R.id.mine_order_parent_btn_right), position);

        RecyclerView childRec = holder.getView(R.id.mine_order_parent_child_rec);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        MineOrderChildAdapter mineOrderChildAdapter = new MineOrderChildAdapter(context, data.getOrderItems(), R.layout.item_mine_order_child_rec);
        childRec.setLayoutManager(linearLayoutManager);
        childRec.setAdapter(mineOrderChildAdapter);

    }
}
