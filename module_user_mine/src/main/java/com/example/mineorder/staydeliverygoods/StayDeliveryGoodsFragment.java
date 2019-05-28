package com.example.mineorder.staydeliverygoods;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StayDeliveryGoodsFragment extends BaseFragment<StayDeliveryGoodsView, StayDeliveryGoodsPresenter> implements StayDeliveryGoodsView {

    @BindView(R2.id.stay_delivery_goods_rec)
    RecyclerView stayDeliveryGoodsRec;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_delivery_goods;
    }

    @Override
    public void initData() {
        presenter.stayDeliveryGoodsRec(stayDeliveryGoodsRec);
    }

    @Override
    public void initClick() {

    }

    @Override
    public StayDeliveryGoodsView createView() {
        return this;
    }

    @Override
    public StayDeliveryGoodsPresenter createPresenter() {
        return new StayDeliveryGoodsPresenter(getContext());
    }

}
