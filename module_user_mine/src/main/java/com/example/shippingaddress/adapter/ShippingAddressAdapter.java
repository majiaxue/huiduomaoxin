package com.example.shippingaddress.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_user_mine.R;
import com.example.shippingaddress.bean.ShippingAddressBean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class ShippingAddressAdapter extends MyRecyclerAdapter<ShippingAddressBean> {

    public ShippingAddressAdapter(Context context, List<ShippingAddressBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ShippingAddressBean data, int position) {
        holder.setText(R.id.shipping_address_name, data.getName());
        holder.setText(R.id.shipping_address_phone, data.getPhone());
        holder.setText(R.id.shipping_address_site, data.getSite());

        viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.shipping_address_check), holder.getView(R.id.shipping_address_amend), holder.getView(R.id.shipping_address_delete), position);
    }
}
