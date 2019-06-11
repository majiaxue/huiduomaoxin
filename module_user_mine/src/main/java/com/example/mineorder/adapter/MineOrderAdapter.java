package com.example.mineorder.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.mineorder.bean.MineOrderBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class MineOrderAdapter extends MyRecyclerAdapter<MineOrderBean.OrderListBean> {

    public MineOrderAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MineOrderBean.OrderListBean data, int position) {
        holder.setText(R.id.order_rec_shop, data.getSellerName());
        holder.setImageFresco(R.id.order_rec_img, data.getImage());
        if (data.getStatus() == 1) {
            //1待发货
            holder.setText(R.id.order_rec_status, "买家已付款");
            holder.setText(R.id.order_rec_btn_left, "申请退款");
            holder.setText(R.id.order_rec_btn_right, "提醒发货");
        } else if (data.getStatus() == 3) {
            //3待评论
            holder.setText(R.id.order_rec_status, "交易成功");
            holder.setText(R.id.order_rec_btn_left, "再次购买");
            holder.setText(R.id.order_rec_btn_right, "立即评价");
        } else if (data.getStatus() == 6) {
            //6待付款
            holder.setText(R.id.order_rec_status, "等待买家付款");
            holder.setText(R.id.order_rec_btn_left, "删除订单");
            holder.setText(R.id.order_rec_btn_right, "付款");
        } else if (data.getStatus() == 8) {
            //8待收货
            holder.setText(R.id.order_rec_status, "卖家已发货");
            holder.setText(R.id.order_rec_btn_left, "查看物流");
            holder.setText(R.id.order_rec_btn_right, "确认收货");
        }

        holder.setText(R.id.order_rec_name, data.getGoodsName());
//        Arrays.asList(data.getSpecification());
//        LogUtil.e("list------->" + Arrays.asList(data.getSpecification()));

//        try {
//            JSONArray jsonArray = new JSONArray(data.getSpecification());
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String key = jsonObject.getString("key");
//                String value = jsonObject.getString("value");
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        holder.setText(R.id.order_rec_message, data.getSp1() + data.getSp2() + data.getSp3());
        holder.setText(R.id.order_rec_price, "￥" + data.getGoodsPrice());
        holder.setText(R.id.order_rec_count, "X" + data.getGoodsNum());
        holder.setText(R.id.order_rec_total, "共计" + data.getGoodsNum() + "件商品" + " 合计: ￥" + data.getGoodsPrice() * data.getGoodsNum());
        viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.order_rec_shop), holder.getView(R.id.order_rec_btn_left), holder.getView(R.id.order_rec_btn_right), position);

    }

}
