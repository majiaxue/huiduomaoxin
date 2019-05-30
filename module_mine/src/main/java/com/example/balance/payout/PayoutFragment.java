package com.example.balance.payout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.balance.adapter.IncomeAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class PayoutFragment extends BaseFragment<PayoutView, PayoutPresenter> implements PayoutView {
    @BindView(R2.id.income_rv)
    RecyclerView mRv;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_income;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_12)));
        presenter.loadData();
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadRv(IncomeAdapter adapter) {
        mRv.setAdapter(adapter);
    }

    @Override
    public PayoutView createView() {
        return this;
    }

    @Override
    public PayoutPresenter createPresenter() {
        return new PayoutPresenter(getContext());
    }
}
