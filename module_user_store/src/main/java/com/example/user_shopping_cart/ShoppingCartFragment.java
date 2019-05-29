package com.example.user_shopping_cart;

import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entity.EventBusBean;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragment;
import com.example.user_store.R;
import com.example.user_store.R2;
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
    CheckBox shoppingCartCheckAll;
    @BindView(R2.id.shopping_cart_total)
    TextView shoppingCartTotal;
    @BindView(R2.id.shopping_cart_hide)
    LinearLayout shoppingCartHide;
    @BindView(R2.id.shopping_cart_close_account_and_delete)
    TextView shoppingCartCloseAccountAndDelete;
    private boolean compileStatus = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_shopping_cart;
    }

    @Override
    public void initData() {
        //商品
//        presenter.setShoppingCartExpandableList(shoppingCartExpandableList);
        presenter.setShoppingCartRec(shoppingCartRec);
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
                    shoppingCartCloseAccountAndDelete.setText("删除");
                    compileStatus = false;
                } else {
                    shoppingCartCompile.setText("编辑");
                    shoppingCartHide.setVisibility(View.VISIBLE);
                    shoppingCartCloseAccountAndDelete.setText("去结算(0)");
                    compileStatus = true;
                }
            }
        });

        //删除订单
        shoppingCartCloseAccountAndDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingCartCloseAccountAndDelete.getText().equals("删除")){
                    presenter.popupDelete();
                }else{
                    Toast.makeText(getContext(), "没有选中商品" , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void initClick() {
        shoppingCartImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean2("user_back", 0));
            }
        });
    }

    @Override
    public ShoppingCartView createView() {
        return this;
    }

    @Override
    public ShoppingCartPresenter createPresenter() {
        return new ShoppingCartPresenter(getContext());
    }

}
