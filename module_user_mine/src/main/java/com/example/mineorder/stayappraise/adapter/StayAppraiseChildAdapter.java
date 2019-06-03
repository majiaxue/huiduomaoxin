package com.example.mineorder.stayappraise.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.mineorder.stayappraise.bean.StayAppraiseChildBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/30
 * Describe:
 */
public class StayAppraiseChildAdapter extends MyRecyclerAdapter<StayAppraiseChildBean> {

    public StayAppraiseChildAdapter(Context context, List<StayAppraiseChildBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, StayAppraiseChildBean data, int position) {
        holder.setImageResource(R.id.stay_appraise_child_img, data.getImage());
        holder.setText(R.id.stay_appraise_child_name, data.getName());
        holder.setText(R.id.stay_appraise_child_message, data.getMessage());
        holder.setText(R.id.stay_appraise_child_price, data.getPrice());
        holder.setText(R.id.stay_appraise_child_count, data.getCount());
        holder.setText(R.id.stay_appraise_child_total, data.getTotal());
        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.order_rec_btn_left), holder.getView(R.id.order_rec_btn_right), position);

    }
}
