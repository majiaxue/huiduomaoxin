package com.example.user_shopping_cart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.confirm_order.ConfirmOrderActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;
import com.example.user_shopping_cart.bean.CartBean;
import com.example.user_store.R;
import com.example.utils.ArithUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.OnCountChangeListener;
import com.example.utils.OnSelectViewListener;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;

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
    private CartParentRecAdapter cartParentRecAdapter;
    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();
    private List<CartBean.RecordsBean> dataBeanList = new ArrayList<>();
    private List<CartBean.RecordsBean.ItemsBean> updateList = new ArrayList<>();
    private boolean flag = true;
    private boolean isCheckAllParentAll;
    private boolean compileStatus;
    private PopupWindow popupWindow;


    public ShoppingCartPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setShoppingCartRecommendRec(final RecyclerView shoppingCartRecommendRec) {
//        Map map = MapUtil.getInstance().addParms("searchInfo", "两件套").build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.HOTNEWSEARCH);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                if (hotSaleBean != null) {

                    commendList.clear();
                    commendList.addAll(hotSaleBean.getData());
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    //添加间距
                    spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                    if (shoppingCartRecommendRec.getItemDecorationCount() == 0) {
                        shoppingCartRecommendRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                    }
                    shoppingCartRecommendRec.setLayoutManager(staggeredGridLayoutManager);
                    BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);
                    shoppingCartRecommendRec.setAdapter(baseRecStaggeredAdapter);

                    baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_user_store/GoodsDetailActivity")
                                    .withString("id", commendList.get(position).getId() + "")
                                    .withString("sellerId", commendList.get(position).getSellerId())
                                    .withString("commendId", commendList.get(position).getProductCategoryId() + "")
                                    .navigation();
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
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMsg------->" + errorMsg);
            }
        }));

    }

    public void setShoppingCartRec() {
        final Observable<ResponseBody> cart = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).getDataWithout(CommonResource.CARTLIST + "/" + SPUtil.getUserCode() + "/" + 1);
        RetrofitUtil.getInstance().toSubscribe(cart, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("cart------>" + result);
                CartBean cartBean = JSON.parseObject(result, CartBean.class);
                if (cartBean != null) {
                    dataBeanList.clear();
                    dataBeanList.addAll(cartBean.getRecords());
//                updateIsAll();
                    totalPrice();
                    if (dataBeanList.size() == 0) {
                        getView().isHide(true);

                    } else {
                        getView().isHide(false);
                        if (cartParentRecAdapter == null) {
                            cartParentRecAdapter = new CartParentRecAdapter(mContext, dataBeanList, R.layout.item_cart_parent, new OnSelectViewListener() {
                                @Override
                                public void setOnSelectViewListener(boolean isAllCheck, int parentPos, int childPos) {
                                    updateList.clear();
                                    updateList.add(dataBeanList.get(parentPos).getItems().get(childPos));
                                    reviseStutas();
                                    if (getView() != null) {
                                        getView().isCheckAll(isAllCheck);
                                    }
                                }
                            }, new OnCountChangeListener() {
                                @Override
                                public void setOnCountChangedListener(int parentPos, int childPos, int count) {
                                    dataBeanList.get(parentPos).getItems().get(childPos).setQuantity(count);
                                    updateList.clear();
                                    updateList.add(dataBeanList.get(parentPos).getItems().get(childPos));
                                    reviseStutas();
                                }
                            });
                        } else {
                            cartParentRecAdapter.notifyDataSetChanged();
                        }
                        if (getView() != null) {
                            getView().loadCartRv(cartParentRecAdapter);
                            getView().loadSuccess();
//                        getView().totalPrice(totalPrice);
                        }
                    }
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("cart-------->" + errorCode + "        " + errorMsg);
                getView().loadSuccess();
            }
        }));


    }

    //选中商家
    private void checkAll(int position) {
        if (dataBeanList.get(position).isCheck()) {
            dataBeanList.get(position).setCheck(false);
            cartParentRecAdapter.checkAll(position, true);
        } else {
            dataBeanList.get(position).setCheck(true);
            cartParentRecAdapter.checkAll(position, false);
        }

        cartParentRecAdapter.notifyDataSetChanged();

        for (int i = 0; i < dataBeanList.size(); i++) {
            if (!dataBeanList.get(i).isCheck()) {
                isCheckAllParentAll = false;
                flag = false;
            }
        }

        if (getView() != null) {
            getView().isCheckAll(flag);
        }
    }

    //选中parent全部的checkbox
    public void checkAllParent(boolean isCheckAllParent) {
        this.isCheckAllParentAll = isCheckAllParent;
        if (isCheckAllParent) {
            for (int i = 0; i < dataBeanList.size(); i++) {
                dataBeanList.get(i).setCheck(false);
                cartParentRecAdapter.checkAll(i, true);
            }
        } else {
            for (int i = 0; i < dataBeanList.size(); i++) {
                dataBeanList.get(i).setCheck(true);
                cartParentRecAdapter.checkAll(i, false);
            }
        }
        totalPrice();
        if (cartParentRecAdapter != null)
            cartParentRecAdapter.notifyDataSetChanged();

    }


    public void popupDelete() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_delete, null, false);
        TextView confirm = view.findViewById(R.id.popup_delete_confirm);
        TextView cancel = view.findViewById(R.id.popup_delete_cancel);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(com.example.module_base.R.style.animScale);
        popupWindow.showAtLocation(new View(mContext), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(mContext, 0.3f);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(mContext, 1f);
            }
        });
    }

    private void reviseStutas() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithList(CommonResource.REVISE_CART_ITEM, updateList, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("修改状态：" + result);
                totalPrice();
//                getView().totalPrice(totalPrice);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void editOrDelete(boolean compileStatus) {
        this.compileStatus = compileStatus;
    }

    private void delete() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithout(CommonResource.DELETE_CART, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("删除购物车：" + result);
                setShoppingCartRec();
                getView().deleteSuccess();
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void click() {
        cartParentRecAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = true;
                        checkAll(position);
                        updateList.clear();
                        updateList.addAll(dataBeanList.get(position).getItems());
                        reviseStutas();
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    private void totalPrice() {
        int count = 0;
        double totalPrice = 0;
        boolean bn = true;
        for (int i = 0; i < dataBeanList.size(); i++) {
            boolean boo = true;
            for (int j = 0; j < dataBeanList.get(i).getItems().size(); j++) {
                if (0 == dataBeanList.get(i).getItems().get(j).getChecked()) {
                    count++;
                    totalPrice += ArithUtil.mul(dataBeanList.get(i).getItems().get(j).getQuantity() * 1.0, dataBeanList.get(i).getItems().get(j).getPrice());
                } else if (1 == dataBeanList.get(i).getItems().get(j).getChecked()) {
                    bn = false;
                    boo = false;
                }
            }
            dataBeanList.get(i).setCheck(boo);
        }
        if (dataBeanList.size() == 0) {
            bn = false;
        }
        if (getView() != null) {
            getView().updateCount(count);
            getView().isCheckAll(bn);
            getView().totalPrice(totalPrice);
        }
    }

    public void jiesuan() {
        List<CartBean.RecordsBean> parentList = new ArrayList<>();
        int sellId = 0;
        for (int i = 0; i < dataBeanList.size(); i++) {
            List<CartBean.RecordsBean.ItemsBean> list = new ArrayList<>();
            for (int j = 0; j < dataBeanList.get(i).getItems().size(); j++) {

                if (0 == dataBeanList.get(i).getItems().get(j).getChecked()) {
                    list.add(dataBeanList.get(i).getItems().get(j));
                    if (sellId != dataBeanList.get(i).getSellerId()) {
                        sellId = dataBeanList.get(i).getSellerId();
                        parentList.add(dataBeanList.get(i));
                    }
                }
            }
            if (parentList.size() > i - 1) {
                parentList.get(i).setItems(list);
            }
        }
        String jsonString = JSON.toJSONString(parentList);
        Intent intent = new Intent(mContext, ConfirmOrderActivity.class);
        intent.putExtra("bean", jsonString);
        mContext.startActivity(intent);
    }
}
