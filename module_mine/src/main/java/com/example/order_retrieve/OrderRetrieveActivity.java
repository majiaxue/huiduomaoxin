package com.example.order_retrieve;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.order_retrieve.adapter.OrderRetrieveAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/mine/orderretrieve")
public class OrderRetrieveActivity extends BaseActivity<OrderRetrieveView, OrderRetrievePresenter> implements OrderRetrieveView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_retrieve_edit)
    EditText orderRetrieveEdit;
    @BindView(R2.id.order_retrieve_search)
    TextView orderRetrieveSearch;
    @BindView(R2.id.order_retrieve_rv)
    RecyclerView orderRetrieveRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_retrieve;
    }

    @Override
    public void initData() {
        includeTitle.setText("订单找回");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderRetrieveRv.setLayoutManager(layoutManager);
        orderRetrieveRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_5)));
        presenter.loadData();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadRv(OrderRetrieveAdapter adapter) {
        orderRetrieveRv.setAdapter(adapter);
    }

    @Override
    public OrderRetrieveView createView() {
        return this;
    }

    @Override
    public OrderRetrievePresenter createPresenter() {
        return new OrderRetrievePresenter(this);
    }
}
