package com.example.order.fragment_lose;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.order.adapter.RvListAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class LoseOrderFragment extends BaseFragment<LoseOrderView, LoseOrderPresenter> implements LoseOrderView {
    @BindView(R2.id.order_list_rv)
    RecyclerView orderListRv;

    private static LoseOrderFragment fragment;
    private int index = 1;

    public static LoseOrderFragment getInstance() {
        if (fragment == null) {
            synchronized (LoseOrderFragment.class) {
                if (fragment == null) {
                    fragment = new LoseOrderFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderListRv.setLayoutManager(layoutManager);
        orderListRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_10)));
        presenter.loadData();

    }

    @Override
    public void initClick() {

    }

    public void setOrign(int index) {
        this.index = index;
        presenter.loadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }

    @Override
    public void loadMineRv(RvListAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public LoseOrderView createView() {
        return this;
    }

    @Override
    public LoseOrderPresenter createPresenter() {
        return new LoseOrderPresenter(getContext());
    }
}
