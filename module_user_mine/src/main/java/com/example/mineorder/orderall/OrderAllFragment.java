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
    //Fragment的View加载完毕的标记
    private boolean isLoading = false;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_all;
    }

    @Override
    public void initData() {
        presenter.orderAllRec(orderAllRec);
    }

    @Override
    public void initClick() {

    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden){
//            //可见
//            LogUtil.e("可见");
//            presenter.orderAllRec(orderAllRec);
//        }
//    }


    @Override
    public OrderAllView createView() {
        return this;
    }

    @Override
    public OrderAllPresenter createPresenter() {
        return new OrderAllPresenter(getContext());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            LogUtil.e("setUserVisibleHint-------->可见");
            isUIVisible = true;
            lazyLoad();//调用下面的方法
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (!isLoading && isUIVisible) {
//            presenter.orderAllRec(orderAllRec);//加载数据的方法
            //数据加载完毕,恢复标记,防止重复加载
            isLoading = true;
            isUIVisible = false;
        }
    }


}
