package com.example.user_shopping_cart;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.CommonResource;
import com.example.confirm_order.ConfirmOrderActivity;
import com.example.entity.EventBusBean;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragment;
import com.example.net.RetrofitUtil;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.example.view.CustomHeader;
import com.example.view.CustomerExpandableListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:多用户商城购物车页面
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartView, ShoppingCartPresenter> implements ShoppingCartView {
    @BindView(R2.id.shopping_cart_image_back)
    ImageView shoppingCartImageBack;
    @BindView(R2.id.shopping_cart_compile)
    TextView shoppingCartCompile;
    @BindView(R2.id.shopping_cart_empty)
    LinearLayout shoppingCartEmpty;
    @BindView(R2.id.shopping_cart_rec)
    RecyclerView shoppingCartRec;
    @BindView(R2.id.shopping_cart_recommend_rec)
    RecyclerView shoppingCartRecommendRec;
    @BindView(R2.id.shopping_cart_smart_refresh)
    SmartRefreshLayout shoppingCartSmartRefresh;
    @BindView(R2.id.shopping_cart_check_all)
    ImageView shoppingCartCheckAll;
    @BindView(R2.id.shopping_cart_total)
    TextView shoppingCartTotal;
    @BindView(R2.id.shopping_cart_hide)
    LinearLayout shoppingCartHide;
    @BindView(R2.id.shopping_cart_close_account_and_delete)
    TextView shoppingCartCloseAccountAndDelete;
    public boolean compileStatus = true;
    //全选初始状态
    private boolean isCheckAllParent = false;
    private int totalCount = 0;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_shopping_cart;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        shoppingCartRec.setLayoutManager(linearLayoutManager);
        //商品
//        presenter.setShoppingCartExpandableList(shoppingCartExpandableList);
        //推荐
        presenter.setShoppingCartRecommendRec(shoppingCartRecommendRec);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        shoppingCartSmartRefresh.setRefreshHeader(customHeader);

        //编辑
        shoppingCartCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (compileStatus) {
                    shoppingCartCompile.setText("完成");
                    shoppingCartHide.setVisibility(View.INVISIBLE);
                    shoppingCartCloseAccountAndDelete.setText("删除(" + totalCount + ")");
                    compileStatus = false;
                    presenter.editOrDelete(compileStatus);
                } else {
                    shoppingCartCompile.setText("编辑");
                    shoppingCartHide.setVisibility(View.VISIBLE);
                    shoppingCartCloseAccountAndDelete.setText("去结算(" + totalCount + ")");
                    compileStatus = true;
                }
            }
        });

        //删除订单和去结算
        shoppingCartCloseAccountAndDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compileStatus) {
                    presenter.popupDelete();
                } else {
                    presenter.jiesuan();
                }

            }
        });
        //全选
        shoppingCartCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheckAllParent) {
                    shoppingCartCheckAll.setImageResource(R.drawable.icon_xuanzhong);
                    isCheckAllParent = false;
                } else {
                    shoppingCartCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
                    isCheckAllParent = true;
                }
                presenter.checkAllParent(isCheckAllParent);
            }
        });

    }

    @Override
    public void initClick() {
        shoppingCartImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean2(CommonResource.USER_BACK, 0));
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //显示
            presenter.setShoppingCartRec();
        }
    }

    @Override
    public ShoppingCartView createView() {
        return this;
    }

    @Override
    public void deleteSuccess() {
        shoppingCartCompile.setText("编辑");
        shoppingCartHide.setVisibility(View.VISIBLE);
        compileStatus = true;
        shoppingCartCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
        isCheckAllParent = true;
    }

    @Override
    public ShoppingCartPresenter createPresenter() {
        return new ShoppingCartPresenter(getContext());
    }

    @Override
    public void isHide(boolean isHide) {
        if (isHide) {
            shoppingCartEmpty.setVisibility(View.VISIBLE);
            shoppingCartCompile.setVisibility(View.VISIBLE);
        } else {
            shoppingCartEmpty.setVisibility(View.GONE);
            shoppingCartCompile.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateCount(int count) {
        totalCount = count;
        if (compileStatus) {
            shoppingCartCloseAccountAndDelete.setText("去结算(" + count + ")");
        } else {
            shoppingCartCloseAccountAndDelete.setText("删除(" + count + ")");
        }
    }

    @Override
    public void loadCartRv(CartParentRecAdapter adapter) {
        shoppingCartRec.setAdapter(adapter);
        presenter.click();
    }

    @Override
    public void isCheckAll(boolean isCheckAll) {
        if (isCheckAll) {
            shoppingCartCheckAll.setImageResource(R.drawable.icon_xuanzhong);
            isCheckAllParent = false;
        } else {
            shoppingCartCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
            isCheckAllParent = true;
        }
    }

    @Override
    public void totalPrice(double price) {
        shoppingCartTotal.setText("" + price);
    }
}
