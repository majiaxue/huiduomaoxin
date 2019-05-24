package com.example.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.entity.BaseStaggeredRecBean;
import com.example.module_base.R;
import com.example.utils.DisplayUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class BaseRecStaggeredAdapter extends MyRecyclerAdapter<BaseStaggeredRecBean> {


    public BaseRecStaggeredAdapter(Context context, List<BaseStaggeredRecBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);

    }

    @Override
    public void convert(RecyclerViewHolder holder, BaseStaggeredRecBean data, int position) {

        if (position == 0) {
            SimpleDraweeView simpleDraweeView = holder.getView(R.id.base_staggered_image);
            ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
            layoutParams.height = DisplayUtil.dip2px(context, 142);
        }
        holder.setImageResource(R.id.base_staggered_image, data.getImage());
        holder.setText(R.id.base_staggered_name, data.getName());
        holder.setText(R.id.base_staggered_price, data.getPrice());
        holder.setText(R.id.base_staggered_payment_amount, data.getPayment_amount());
        holder.setText(R.id.base_staggered_shop, data.getShop());

        viewOnClickListener.ViewOnClick(holder.getView(R.id.base_staggered_go_shop), position);
    }
}
