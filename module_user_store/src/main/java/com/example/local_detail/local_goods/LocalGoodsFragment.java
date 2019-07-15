package com.example.local_detail.local_goods;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.local_detail.adapter.LocalDetailGoodsAdapter;
import com.example.mvp.BaseFragment;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;

public class LocalGoodsFragment extends BaseFragment<LocalGoodsView, LocalGoodsPresenter> implements LocalGoodsView {
    @BindView(R2.id.local_detail_goods_rv)
    RecyclerView localDetailGoodsRv;
    @BindView(R2.id.local_detail_goods_btn)
    TextView localDetailGoodsBtn;

    private static String sellerId;

    public static LocalGoodsFragment getInstance(String id) {
        sellerId = id;
        return new LocalGoodsFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_goods;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        localDetailGoodsRv.setLayoutManager(layoutManager);

        presenter.loadData(sellerId);
    }

    @Override
    public void initClick() {
        localDetailGoodsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToPay();
            }
        });
    }

    @Override
    public void loadGoods(LocalDetailGoodsAdapter adapter) {
        localDetailGoodsRv.setAdapter(adapter);
    }

    @Override
    public LocalGoodsView createView() {
        return this;
    }

    @Override
    public LocalGoodsPresenter createPresenter() {
        return new LocalGoodsPresenter(getContext());
    }
}
