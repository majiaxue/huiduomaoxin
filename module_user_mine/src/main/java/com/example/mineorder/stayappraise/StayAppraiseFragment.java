package com.example.mineorder.stayappraise;

import android.support.v7.widget.RecyclerView;

import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 待评价
 */
public class StayAppraiseFragment extends BaseFragment<StayAppraiseView, StayAppraisePresenter> implements StayAppraiseView {


    @BindView(R2.id.stay_appraise_rec)
    RecyclerView stayAppraiseRec;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_stay_appraise;
    }

    @Override
    public void initData() {
        LogUtil.e("-------->onCreate");
        presenter.stayAppraiseRec(stayAppraiseRec);
    }

    @Override
    public void initClick() {

    }

    @Override
    public StayAppraiseView createView() {
        return this;
    }

    @Override
    public StayAppraisePresenter createPresenter() {
        return new StayAppraisePresenter(getContext());
    }



    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("setUserVisibleHint-------->onResume");
        presenter.stayAppraiseRec(stayAppraiseRec);
    }

}
