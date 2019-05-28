package com.example.shippingaddress;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.shippingaddress.adapter.ShippingAddressAdapter;
import com.example.shippingaddress.bean.ShippingAddressBean;
import com.example.utils.PopUtils;
import com.example.view.SelfDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class ShippingAddressPresenter extends BasePresenter<ShippingAddressView> {

    private ShippingAddressAdapter shippingAddressAdapter;
    private List<ShippingAddressBean> beanList;

    public ShippingAddressPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setShippingAddressRec(RecyclerView shippingAddressRec) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        shippingAddressRec.setLayoutManager(linearLayoutManager);
        beanList = new ArrayList<>();
        beanList.add(new ShippingAddressBean("付韶", "188  1888  1888", "河南省郑州市金水区金成国际广场3号楼204室", true));
        beanList.add(new ShippingAddressBean("富少", "188  1888  1888", "河南省郑州市金水区金成国际广场3号楼204室", false));
        shippingAddressAdapter = new ShippingAddressAdapter(mContext, beanList, R.layout.item_shipping_address_rec);
        shippingAddressRec.setAdapter(shippingAddressAdapter);

        shippingAddressAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
            @Override
            public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                //点击选中
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < beanList.size(); i++) {
                            if (i != position) {
                                beanList.get(i).setCheck(false);
                            }
                            beanList.get(position).setCheck(true);
                            shippingAddressAdapter.notifyDataSetChanged();
                        }
                    }
                });
                //修改
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build("/module_user_mine/AmendAddressActivity").navigation();
                    }
                });
                //删除
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //删除地址
                        final SelfDialog selfDialog = new SelfDialog(mContext);
                        selfDialog.setTitle("提示");
                        selfDialog.setMessage("您确定要删除此地址吗？");
                        selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                Toast.makeText(mContext, "还不能取消关注", Toast.LENGTH_SHORT).show();
                                selfDialog.dismiss();
                                PopUtils.setTransparency(mContext,1f);
                            }
                        });
                        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                selfDialog.dismiss();
                                PopUtils.setTransparency(mContext,1f);
                            }
                        });
                        PopUtils.setTransparency(mContext,0.3f);
                        selfDialog.show();
                    }
                });

            }
        });
    }
}
