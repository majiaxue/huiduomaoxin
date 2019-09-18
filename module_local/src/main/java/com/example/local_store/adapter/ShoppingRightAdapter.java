package com.example.local_store.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.bean.GoodsToCartBean;
import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.local_store.ShoppingRight.RvAdapter;
import com.example.local_store.ShoppingRight.RvHolder;
import com.example.local_store.ShoppingRight.RvListener;
import com.example.local_store.ShoppingRight.ShopOnClickListtener;
import com.example.module_local.R;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by yadianna02 on 2018/7/31.
 */

public class ShoppingRightAdapter extends RvAdapter<LocalStoreBean.ListBean> {
    public ShoppingRightAdapter(Context context, List<LocalStoreBean.ListBean> list, RvListener listener, ShopOnClickListtener shopOnClickListtener) {
        super(context, list, listener, shopOnClickListtener);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.shopping_right_title : R.layout.rv_shop_right;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new ShoppingRightHolder(view, viewType, listener);
    }

    class ShoppingRightHolder extends RvHolder<LocalStoreBean.ListBean> {
        private TextView title;
        private ImageView pic;
        private TextView name;
        private TextView newPrice;
        private TextView oldPrice;
        private ImageView add;
        private ImageView minus;
        private TextView count;

        public ShoppingRightHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            switch (type) {
                case 0:
                    title = itemView.findViewById(R.id.shopping_right_title_txt);
                    break;
                case 1:
                    pic = itemView.findViewById(R.id.rv_shop_right_img);
                    name = itemView.findViewById(R.id.rv_shop_right_name);
                    newPrice = itemView.findViewById(R.id.rv_shop_right_new_price);
                    oldPrice = itemView.findViewById(R.id.rv_shop_right_old_price);
                    add = itemView.findViewById(R.id.rv_shop_add);
                    minus = itemView.findViewById(R.id.rv_shop_right_minus);
                    count = itemView.findViewById(R.id.rv_shop_right_count);
                    break;
            }
        }

        @Override
        public void bindHolder(final LocalStoreBean.ListBean commodity, final int position) {
            int itemViewType = ShoppingRightAdapter.this.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    title.setText(commodity.getName());
                    break;
                case 1:
                    Glide.with(mContext).load(commodity.getPics()).into(pic);
                    name.setText(commodity.getName());
                    if (TextUtils.isEmpty(commodity.getDiscountPrice())) {
                        newPrice.setText("￥" + commodity.getPrice());
                        oldPrice.setVisibility(View.GONE);
                    } else {
                        oldPrice.setVisibility(View.VISIBLE);
                        newPrice.setText("￥" + commodity.getDiscountPrice());
                        oldPrice.setText("￥" + commodity.getPrice());
                        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                        oldPrice.getPaint().setAntiAlias(true);// 抗锯齿
                    }
                    if (commodity.getCount() > 0) {
                        count.setText(commodity.getCount() + "");
                    }

                    isShow(commodity);

                    pic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addGoods(commodity);

                            int currentCount = commodity.getCount();
                            if (currentCount == 0) {
                                minus.setAnimation(getShowAnimation());
                                count.setAnimation(getShowAnimation());
                            }
                            currentCount++;
                            minus.setEnabled(true);
                            shopOnClickListtener.startPlace(add);
                            count.setText(currentCount + "");
                            commodity.setCount(currentCount);
                            isShow(commodity);
                            notifyDataSetChanged();
                        }
                    });

                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            minusGoods(commodity);

                            add.setEnabled(true);
                            Integer currentCount = commodity.getCount();
                            currentCount--;
                            if (currentCount <= 0) {
                                currentCount = 0;
                                minus.setEnabled(false);
                                minus.setAnimation(getHiddenAnimation());
                                count.setAnimation(getHiddenAnimation());
                            }
                            count.setText(currentCount + "");
                            commodity.setCount(currentCount);
                            isShow(commodity);
                            notifyDataSetChanged();
                        }
                    });
            }
        }

        private void isShow(LocalStoreBean.ListBean commodity) {
            if (commodity.getCount() > 0) {
                minus.setVisibility(View.VISIBLE);
                count.setVisibility(View.VISIBLE);
            } else {
                minus.setVisibility(View.GONE);
                count.setVisibility(View.GONE);
            }
        }

        private void addGoods(LocalStoreBean.ListBean data) {
            GoodsToCartBean goodsToCartBean = new GoodsToCartBean(SPUtil.getStringValue(CommonResource.SELLERID), data.getId(), SPUtil.getUserCode(), data.getCount() + "");
            String jsonString = JSON.toJSONString(goodsToCartBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_ADD, requestBody);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("添加商品：" + result);
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "--------------" + errorMsg);
                }
            }));
        }

        private void minusGoods(LocalStoreBean.ListBean data) {
            GoodsToCartBean goodsToCartBean = new GoodsToCartBean(SPUtil.getStringValue(CommonResource.SELLERID), data.getId(), SPUtil.getUserCode(), data.getCount() + "");
            String jsonString = JSON.toJSONString(goodsToCartBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_MINUS, requestBody);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("去掉商品：" + result);
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "--------------" + errorMsg);
                }
            }));
        }
    }

    //显示减号的动画
    private Animation getShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 1f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    //隐藏减号的动画
    private Animation getHiddenAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 1f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

}
