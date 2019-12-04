package com.example.productdetail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TestAccountBean;
import com.example.module_home.R;

import java.util.List;

public class ProductAccountInsideAdapter extends MyRecyclerAdapter<TestAccountBean.Account> {
    public ProductAccountInsideAdapter(Context context, List<TestAccountBean.Account> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, final TestAccountBean.Account data, int position) {
        holder.setText(R.id.rv_product_detail_account_account, data.getAccount())
                .setText(R.id.rv_product_detail_account_password, data.getPassword());
    }
}
