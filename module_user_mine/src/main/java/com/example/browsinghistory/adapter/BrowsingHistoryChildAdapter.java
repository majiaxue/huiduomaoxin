package com.example.browsinghistory.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.browsinghistory.bean.BrowsingHistoryChildBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryChildAdapter extends MyRecyclerAdapter<BrowsingHistoryChildBean> {

    public BrowsingHistoryChildAdapter(Context context, List<BrowsingHistoryChildBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BrowsingHistoryChildBean data, int position) {
        holder.setImageResource(R.id.browsing_history_child_image, data.getImage());
        holder.setText(R.id.browsing_history_child_name, data.getName());
        holder.setText(R.id.browsing_history_child_price, data.getPrice());
        holder.setText(R.id.browsing_history_child_payment_amount, data.getPayment_amount());
        holder.setText(R.id.browsing_history_child_good_reputation, data.getGood_reputation());
        holder.setText(R.id.browsing_history_child_shop, data.getShop());
        viewOnClickListener.ViewOnClick(holder.getView(R.id.browsing_history_child_look_similar),position);
    }
}
