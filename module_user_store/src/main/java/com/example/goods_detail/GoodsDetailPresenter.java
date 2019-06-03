package com.example.goods_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.assess.AssessActivity;
import com.example.bean.BannerBean;
import com.example.bean.HotSaleBean;
import com.example.bean.UserGoodsDetail;
import com.example.common.CommonResource;
import com.example.confirm_order.ConfirmOrderActivity;
import com.example.entity.AssessBean;
import com.example.entity.CouponBean;
import com.example.entity.ParmsBean;
import com.example.goods_detail.adapter.ColorFlowLayoutAdapter;
import com.example.goods_detail.adapter.GoodsAssessAdapter;
import com.example.goods_detail.adapter.GoodsCouponAdapter;
import com.example.goods_detail.adapter.GoodsImageAdapter;
import com.example.goods_detail.adapter.SizeFlowLayoutAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.shop_home.ShopHomeActivity;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_store.R;
import com.example.user_store.UserActivity;
import com.example.utils.MapUtil;
import com.example.utils.OnFlowSelectListener;
import com.example.utils.PopUtil;
import com.example.view.flowLayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    //是否关注
    private boolean isAttention = false;
    //选择商品列表
    private List<UserGoodsDetail.StoInfoBean.RecordsBean> dataList = new ArrayList<>();
    //流式布局--颜色
    private TagFlowLayout flow1;
    //流式布局--尺码
    private TagFlowLayout flow2;
    //颜色选中下标
    private int colorPosition = -1;
    //尺码选中下标
    private int sizePosition = -1;

    private boolean isChoose = false;
    //尺码列表
    private List<UserGoodsDetail.StoInfoBean.RecordsBean.ListBean> sizeList = new ArrayList<>();
    //缩略图
    private List imgList = new ArrayList();
    //为你推荐
    List<HotSaleBean.DataBean> commendList = new ArrayList<>();

    public GoodsDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String id) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi1(mContext).goodsDetail("39");
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                UserGoodsDetail userGoodsDetail = JSON.parseObject(result, new TypeReference<UserGoodsDetail>() {
                }.getType());
                if (getView() != null) {
                    getView().loadUI(userGoodsDetail);
                }

                dataList.clear();
                dataList.addAll(userGoodsDetail.getStoInfo().getRecords());

                //规格缩略图
                for (int i = 0; i < dataList.size(); i++) {
                    imgList.add(dataList.get(i).getList().get(0).getPic());
                }
                GoodsImageAdapter goodsImageAdapter = new GoodsImageAdapter(mContext, imgList, R.layout.rv_goods_choose_image);
                goodsImageAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        chooseGoodsPop();
                    }
                });
                if (getView() != null) {
                    getView().loadImage(goodsImageAdapter);
                }

                //轮播图
                String albumPics = userGoodsDetail.getAlbumPics();
                List<BannerBean> bannerList = new ArrayList<>();
                String[] split = albumPics.split(",");
                for (int i = 0; i < split.length; i++) {
                    bannerList.add(new BannerBean(split[i]));
                }
                if (getView() != null) {
                    getView().loadBanner(bannerList);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

        //优惠券
        List couponList = new ArrayList();
        couponList.add("满50减5");
        couponList.add("满1500减100");
        GoodsCouponAdapter goodsCouponAdapter = new GoodsCouponAdapter(mContext, couponList, R.layout.rv_goods_coupon);

        goodsCouponAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                lingquan();
            }
        });
        if (getView() != null) {
            getView().loadCoupon(goodsCouponAdapter);
        }

        //评论
        List<AssessBean> assessList = new ArrayList<>();
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        GoodsAssessAdapter goodsAssessAdapter = new GoodsAssessAdapter(mContext, assessList, R.layout.rv_goods_assess);

        goodsAssessAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                jumpToAssess();
            }
        });
        if (getView() != null) {
            getView().loadAssess(goodsAssessAdapter);
        }
    }

    public void loadCommend(String keyWords) {
        Map map = MapUtil.getInstance().addParms("searchInfo", keyWords).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi1(mContext).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                commendList.clear();
                commendList.addAll(hotSaleBean.getData());
                CommendAdapter commendAdapter = new CommendAdapter(mContext, commendList, R.layout.rv_commend);
                if (getView() != null) {
                    getView().loadCommend(commendAdapter);
                }
                commendAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("id", commendList.get(position).getId());
                        mContext.startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void toAttention() {
        if (isAttention) {
            isAttention = false;
            getView().cancelAttention();
        } else {
            isAttention = true;
            getView().attention();
        }
    }

    public void lingquan() {
        final List<CouponBean> dataList = new ArrayList<>();
        dataList.add(new CouponBean("5", "满50元可使用", "2019/12/12", false));
        dataList.add(new CouponBean("500", "满50000元可使用", "2019/12/12", false));
        dataList.add(new CouponBean("50", "满500元可使用", "2019/12/12", false));
        dataList.add(new CouponBean("5", "满50元可使用", "2019/12/12", false));
        dataList.add(new CouponBean("5", "满50元可使用", "2019/12/12", false));
        dataList.add(new CouponBean("5", "满50元可使用", "2019/12/12", false));
        dataList.add(new CouponBean("5", "满50元可使用", "2019/12/12", false));
        PopUtil.lingquanPop(mContext, dataList);
    }

    public void ensure() {
        PopUtil.ensurePop(mContext);
    }

    public void detailParms() {
        List<ParmsBean> dataList = new ArrayList<>();
        dataList.add(new ParmsBean("品牌", "华为"));
        dataList.add(new ParmsBean("尺码", "M  L  XL  XXL"));
        dataList.add(new ParmsBean("领型", "鸡心领"));
        dataList.add(new ParmsBean("颜色", "黑色  白色  灰色  卡其色"));
        dataList.add(new ParmsBean("袖型", "常规"));
        dataList.add(new ParmsBean("货号", "LN19A-Y3269"));
        PopUtil.parmsPop(mContext, dataList);
    }

    public void chooseGoodsPop() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_choose_goods, null);
        final ImageView img = view.findViewById(R.id.pop_choose_goods_img);
        TextView price = view.findViewById(R.id.pop_choose_goods_price);
        ImageView cancel = view.findViewById(R.id.pop_choose_goods_cancel);
        flow1 = view.findViewById(R.id.pop_choose_goods_flow1);
        flow2 = view.findViewById(R.id.pop_choose_goods_flow2);
        TextView minus = view.findViewById(R.id.pop_choose_goods_minus);
        TextView add = view.findViewById(R.id.pop_choose_goods_add);
        final TextView count = view.findViewById(R.id.pop_choose_goods_count);
        TextView shopCart = view.findViewById(R.id.pop_choose_goods_cart);
        TextView buy = view.findViewById(R.id.pop_choose_goods_buy);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) mContext.getResources().getDimension(R.dimen.dp_444), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);

        PopUtil.setTransparency(mContext, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtil.setTransparency(mContext, 1f);
                if (colorPosition != -1 && sizePosition != -1) {
                    isChoose = true;
                    getView().yixuanze(dataList.get(colorPosition).getSkuName(), dataList.get(colorPosition).getList().get(sizePosition).getSp2());
                } else {
                    isChoose = false;
                    getView().weixuanze();
                }
            }
        });

        sizeList.addAll(dataList.get(0).getList());

        final SizeFlowLayoutAdapter sizeFlowLayoutAdapter = new SizeFlowLayoutAdapter(sizeList, mContext, new OnFlowSelectListener() {
            @Override
            public void setOnFlowSelect(int position) {
                sizePosition = position;
            }
        });

        flow1.setAdapter(new ColorFlowLayoutAdapter(dataList, mContext, new OnFlowSelectListener() {
            @Override
            public void setOnFlowSelect(int position) {
                colorPosition = position;
                Glide.with(mContext).load(dataList.get(position).getList().get(0).getPic()).into(img);
                sizeList.clear();
                sizePosition = -1;
                initSizeList(position);
                sizeFlowLayoutAdapter.notifyDataChanged();
            }
        }));

        flow2.setAdapter(sizeFlowLayoutAdapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(count.getText().toString()) <= 1) {
                    count.setText("1");
                } else {
                    count.setText(Integer.valueOf(count.getText().toString()) - 1 + "");
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizePosition == -1) {
                    Toast.makeText(mContext, "请先选择商品", Toast.LENGTH_SHORT).show();
                } else {
                    if (Integer.valueOf(count.getText().toString()) >= dataList.get(colorPosition).getList().get(sizePosition).getStock()) {
                        count.setText(dataList.get(0).getList().get(0).getStock() + "");
                    } else {
                        count.setText(Integer.valueOf(count.getText().toString()) + 1 + "");
                    }
                }
            }
        });

        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorPosition == -1 || sizePosition == -1) {
                    Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                } else {
                    popupWindow.dismiss();
                }
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorPosition == -1 || sizePosition == -1) {
                    Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                } else {
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void initSizeList(int position) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(position).getList().get(i).getStock() > 0) {
                sizeList.add(dataList.get(position).getList().get(i));
            }
        }
    }

    public void jumpToAssess() {
        mContext.startActivity(new Intent(mContext, AssessActivity.class));
    }

    public void jumpToShop() {
        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
    }

    public void chooseOrJump() {
        if (isChoose) {
            mContext.startActivity(new Intent(mContext, ConfirmOrderActivity.class));
        } else {
            chooseGoodsPop();
        }
    }

    public void jumpToCart() {
        Intent intent = new Intent(mContext, UserActivity.class);
        intent.putExtra("key", CommonResource.JUMP_CART);
        mContext.startActivity(intent);
    }
}
