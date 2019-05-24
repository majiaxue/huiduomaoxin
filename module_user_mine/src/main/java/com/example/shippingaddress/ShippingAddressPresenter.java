package com.example.shippingaddress;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.shippingaddress.adapter.ShippingAddressAdapter;
import com.example.shippingaddress.bean.ShippingAddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class ShippingAddressPresenter extends BasePresenter<ShippingAddressView> {

    public ShippingAddressPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setShippingAddressRec(RecyclerView shippingAddressRec) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        shippingAddressRec.setLayoutManager(linearLayoutManager);
        List<ShippingAddressBean> beanList = new ArrayList<>();
        beanList.add(new ShippingAddressBean("付韶", "188  1888  1888", "河南省郑州市金水区金成国际广场3号楼204室", false));
        beanList.add(new ShippingAddressBean("富少", "188  1888  1888", "河南省郑州市金水区金成国际广场3号楼204室", false));
        ShippingAddressAdapter shippingAddressAdapter = new ShippingAddressAdapter(mContext, beanList, R.layout.item_shipping_address_rec);
        shippingAddressRec.setAdapter(shippingAddressAdapter);

        shippingAddressAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
            @Override
            public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                //点击选中
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                //修改
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                //删除
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
