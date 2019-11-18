package com.example.local_order.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalTuiKuanBean;
import com.example.module_local.R;

import java.util.List;

public class LocalTuiKuanAdapter extends MyRecyclerAdapter<LocalTuiKuanBean.RecordsBean> {
    public LocalTuiKuanAdapter(Context context, List<LocalTuiKuanBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalTuiKuanBean.RecordsBean data, int position) {
        if (data.getLocalOrderItemList().size() > 0) {
            holder.setText(R.id.rv_local_order_list_shop_name, data.getSeller().getSellerShopName())
                    .setText(R.id.rv_local_order_list_count, "共" + data.getLocalOrderItemList().size() + "件商品");
//                    .setText(R.id.rv_local_order_list_money, "￥" + data.getTotalMoney());
            if (!TextUtils.isEmpty(data.getSeller().getSellerLogo())) {
                holder.setImageFresco(R.id.rv_local_order_list_img, data.getSeller().getSellerLogo());
            }

            holder.getView(R.id.rv_local_order_list_code).setVisibility(View.GONE);

        }
    }
}
