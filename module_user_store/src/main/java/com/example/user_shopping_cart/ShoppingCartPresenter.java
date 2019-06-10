package com.example.user_shopping_cart;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.ApiService;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;
import com.example.user_shopping_cart.bean.CartBean;
import com.example.user_store.R;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ShoppingCartPresenter extends BasePresenter<ShoppingCartView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;
    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();
    private List<CartBean.RecordsBean> dataBeanList = new ArrayList<>();

    public ShoppingCartPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setShoppingCartRecommendRec(final RecyclerView shoppingCartRecommendRec) {
//        Map map = MapUtil.getInstance().addParms("searchInfo", "两件套").build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi1(mContext).getDataWithout(CommonResource.HOTNEWSEARCH);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                commendList.clear();
                commendList.addAll(hotSaleBean.getData());
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //添加间距
                SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                if (shoppingCartRecommendRec.getItemDecorationCount() == 0) {
                    shoppingCartRecommendRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                }
                shoppingCartRecommendRec.setLayoutManager(staggeredGridLayoutManager);
                BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);
                shoppingCartRecommendRec.setAdapter(baseRecStaggeredAdapter);

                baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                baseRecStaggeredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "position:" + index, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMsg------->" + errorMsg);
            }
        }));

    }

    public void setShoppingCartRec(final RecyclerView shoppingCartRec) {

//        Observable<ResponseBody> cart = RetrofitUtil.getInstance().getApi5(mContext).cart(1, 1);
//        RetrofitUtil.getInstance().toSubscribe(cart, new OnMyCallBack(new OnDataListener() {
//            @Override
//            public void onSuccess(String result, String msg) {
//                LogUtil.e("cart------>" + result);
//                CartBean cartBean = JSON.parseObject(result, new TypeReference<CartBean>() {
//                }.getType());
//
//                dataBeanList.clear();
//                dataBeanList.addAll(cartBean.getRecords());
//
//                if (dataBeanList.size() == 0) {
//                    getView().isHide(true);
//
//                } else {
//                    getView().isHide(false);
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//                    shoppingCartRec.setLayoutManager(linearLayoutManager);
//                    CartParentRecAdapter cartParentRecAdapter = new CartParentRecAdapter(mContext, dataBeanList, R.layout.item_cart_parent);
//                    shoppingCartRec.setAdapter(cartParentRecAdapter);
//
//                }
//
//            }
//
//            @Override
//            public void onError(String errorCode, String errorMsg) {
//                LogUtil.e("cart-------->" + errorCode + "        " + errorMsg);
//            }
//        }));


    }


    public void popupDelete() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_delete, null, false);
        final TextView confirm = view.findViewById(R.id.popup_delete_confirm);
        final TextView cancel = view.findViewById(R.id.popup_delete_cancel);
        PopUtils.createPopCenter(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //确定删除
                        Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
            }
        });
        PopUtils.setTransparency(mContext, 0.3f);
    }

}
