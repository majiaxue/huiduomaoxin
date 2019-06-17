package com.example.mineorder.stayobligation;

import android.support.v7.widget.RecyclerView;

import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 待付款
 */
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

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->当前可见");
        presenter.stayObligationRec(stayObligationRec);
    }


}
