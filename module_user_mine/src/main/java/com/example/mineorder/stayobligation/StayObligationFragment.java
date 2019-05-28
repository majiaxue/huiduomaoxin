package com.example.mineorder.stayobligation;

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

public class StayObligationFragment extends BaseFragment<StayObligationView, StayObligationPresenter> implements StayObligationView {


    @BindView(R2.id.stay_obligation_rec)
    RecyclerView stayObligationRec;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_obligation;
    }

    @Override
    public void initData() {
        presenter.stayObligationRec(stayObligationRec);
    }

    @Override
    public void initClick() {

    }

    @Override
    public StayObligationView createView() {
        return this;
    }

    @Override
    public StayObligationPresenter createPresenter() {
        return new StayObligationPresenter(getContext());
    }
}
