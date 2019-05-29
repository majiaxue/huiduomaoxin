package com.example.browsinghistory.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.example.adapter.MyListAdapter;
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

    private boolean isChildCompile;
    private CheckBox browsingHistoryChildCheck;

    public BrowsingHistoryChildAdapter(Context context, List<BrowsingHistoryChildBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public BrowsingHistoryChildAdapter(Context context, List<BrowsingHistoryChildBean> mList, int mLayoutId,boolean childCompile) {
        super(context, mList, mLayoutId);
        this.isChildCompile = childCompile;
    }

    @Override
    public void convert(RecyclerViewHolder holder, BrowsingHistoryChildBean data, int position) {

        if (isChildCompile) {
            holder.getView(R.id.browsing_history_child_check).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.browsing_history_child_check).setVisibility(View.GONE);
        }

        browsingHistoryChildCheck = holder.getView(R.id.browsing_history_child_check);

        if (data.isCheck()){
            browsingHistoryChildCheck.setChecked(true);
        }else{
            browsingHistoryChildCheck.setChecked(false);
        }

        holder.setImageResource(R.id.browsing_history_child_image, data.getImage());
        holder.setText(R.id.browsing_history_child_name, data.getName());
        holder.setText(R.id.browsing_history_child_price, data.getPrice());
        holder.setText(R.id.browsing_history_child_payment_amount, data.getPayment_amount());
        holder.setText(R.id.browsing_history_child_good_reputation, data.getGood_reputation());
        holder.setText(R.id.browsing_history_child_shop, data.getShop());
        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.browsing_history_child_check),holder.getView(R.id.browsing_history_child_look_similar),position);
    }

    public void setChildCompile(boolean childCompile){
        isChildCompile = childCompile;
    }
}
