package com.example.mineorder.staysendgoods;

import android.support.v7.widget.RecyclerView;

import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 待发货
 */
public class StaySendGoodsFragment extends BaseFragment<StaySendGoodsView, StaySendGoodsPresenter> implements StaySendGoodsView {


    @BindView(R2.id.stay_send_goods_rec)
    RecyclerView staySendGoodsRec;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_send_goods;
    }

    @Override
    public void initData() {
        presenter.staySendGoodsRec(staySendGoodsRec);
    }

    @Override
    public void initClick() {

    }

    @Override
    public StaySendGoodsView createView() {
        return this;
    }

    @Override
    public StaySendGoodsPresenter createPresenter() {
        return new StaySendGoodsPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->当前可见");
    }



}
