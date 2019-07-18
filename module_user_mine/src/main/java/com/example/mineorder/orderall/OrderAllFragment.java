package com.example.mineorder.orderall;

import android.support.v7.widget.RecyclerView;

import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 全部订单
 */
public class OrderAllFragment extends BaseFragment<OrderAllView, OrderAllPresenter> implements OrderAllView {

    @BindView(R2.id.order_all_rec)
    RecyclerView orderAllRec;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_all;
    }

    @Override
    public void initData() {
//        LogUtil.e("setUserVisibleHint-------->初始化布局");
//        presenter.orderAllRec(orderAllRec);
    }

    @Override
    public void initClick() {

    }


    @Override
    public OrderAllView createView() {
        return this;
    }

    @Override
    public OrderAllPresenter createPresenter() {
        return new OrderAllPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->全部订单当前可见");
        presenter.orderAllRec(orderAllRec);
    }


}
