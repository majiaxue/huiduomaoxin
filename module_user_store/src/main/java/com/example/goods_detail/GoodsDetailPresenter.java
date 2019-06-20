package com.example.goods_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.assess.AssessActivity;
import com.example.bean.AddCartBean;
import com.example.bean.AssessBean;
import com.example.bean.BannerBean;
import com.example.bean.ChooseGoodsBean;
import com.example.bean.ChooseInsideBean;
import com.example.bean.HotSaleBean;
import com.example.bean.OrderConfirmBean;
import com.example.bean.UserCouponBean;
import com.example.bean.UserGoodsDetail;
import com.example.common.CommonResource;
import com.example.entity.ParmsBean;
import com.example.goods_detail.adapter.GoodsAssessAdapter;
import com.example.goods_detail.adapter.GoodsCouponAdapter;
import com.example.goods_detail.adapter.GoodsImageAdapter;
import com.example.goods_detail.adapter.PopChooseAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.order_confirm.OrderConfirmActivity;
import com.example.shop_home.ShopHomeActivity;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_store.R;
import com.example.user_store.UserActivity;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PopUtil;
import com.example.utils.SPUtil;
import com.example.view.flowLayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    //选择商品列表
    private List<UserGoodsDetail.SkuStockListBean> dataList;
    //流式布局--颜色
    private TagFlowLayout flow1;
    //流式布局--尺码
    private TagFlowLayout flow2;
    //颜色选中下标
    private int colorPosition = -1;
    //尺码选中下标
    private int sizePosition = -1;


    private long quantity = 1;

    private boolean isChoose = false;
    //尺码列表
//    private List<UserGoodsDetail.StoInfoBean.RecordsBean.ListBean> sp2List = new ArrayList<>();
    //缩略图
    private List imgList = new ArrayList();
    private List<ChooseInsideBean> sp1List = new ArrayList<>();
    private List<ChooseInsideBean> sp2List = new ArrayList<>();
    private List<ChooseInsideBean> sp3List = new ArrayList<>();
    private List<ChooseGoodsBean> popList = new ArrayList<>();
    //为你推荐
    List<HotSaleBean.DataBean> commendList = new ArrayList<>();

    private UserGoodsDetail userGoodsDetail;
    private List<UserCouponBean> couponBeanList;
    private PopChooseAdapter popChooseAdapter;

    public GoodsDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String id) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHeadWithout(CommonResource.GETGOODSDETAIL + "/" + id, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("商品详情：" + result);
                userGoodsDetail = JSON.parseObject(result, new TypeReference<UserGoodsDetail>() {
                }.getType());


                dataList = userGoodsDetail.getSkuStockList();

                //规格缩略图
                String imgTemp = "";
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getSp1() != null && !imgTemp.equals(dataList.get(i).getSp1())) {
                        imgList.add(dataList.get(i).getPic());
                        imgTemp = dataList.get(i).getSp1();
                        sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), false, dataList.get(i).getPrice()));
                    }
                }

                popList.add(new ChooseGoodsBean(userGoodsDetail.getXsProductAttributes().get(0).getName(), sp1List));
                for (int i = 1; i < userGoodsDetail.getXsProductAttributes().size(); i++) {
                    List<ChooseInsideBean> spList = new ArrayList<>();
                    String[] split = userGoodsDetail.getXsProductAttributes().get(i).getInputList().split(",");
                    for (int j = 0; j < split.length; j++) {
                        spList.add(new ChooseInsideBean(split[j], false));
                    }
                    popList.add(new ChooseGoodsBean(userGoodsDetail.getXsProductAttributes().get(i).getName(), spList));
                }
//                if (dataList.size() > 0 && dataList.get(0).getSp2() != null) {
//                    totalNum = 2;
//                    String[] split = userGoodsDetail.getXsProductAttributes().get(1).getInputList().split(",");
//                    for (int i = 0; i < split.length; i++) {
//                        sp2List.add(new ChooseInsideBean(split[i], false));
//                    }
//                }
//                if (dataList.size() > 0 && dataList.get(0).getSp3() != null) {
//                    totalNum = 3;
//
//                    sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), false, dataList.get(i).getPrice()));
//                }
                GoodsImageAdapter goodsImageAdapter = new GoodsImageAdapter(mContext, imgList, R.layout.rv_goods_choose_image);
                goodsImageAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        chooseGoodsPop();
                    }
                });

                if (getView() != null) {
                    getView().loadImage(goodsImageAdapter);
                    getView().loadUI(userGoodsDetail, sp1List.size());
                }

//                for (int i = 0; i < userGoodsDetail.getXsProductAttributes().size(); i++) {
//                    if (sp1List.size() > 0 && i == 0) {
//                        popList.add(new ChooseGoodsBean(userGoodsDetail.getXsProductAttributes().get(i).getName(), sp1List));
//                    }
//                    if (sp2List.size() > 0 && i == 1) {
//                        popList.add(new ChooseGoodsBean(userGoodsDetail.getXsProductAttributes().get(i).getName(), sp2List));
//                    }
//                    if (sp3List.size() > 0 && i == 2) {
//                        popList.add(new ChooseGoodsBean(userGoodsDetail.getXsProductAttributes().get(i).getName(), sp3List));
//                    }
//                }

                //轮播图
                String albumPics = userGoodsDetail.getAlbumPics();
                List<BannerBean.RecordsBean> bannerList = new ArrayList<>();
                String[] split = albumPics.split(",");
                for (int i = 0; i < split.length; i++) {
                    bannerList.add(new BannerBean.RecordsBean(split[i]));
                }
                if (getView() != null) {
                    getView().loadBanner(bannerList);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

    }

    public void loadAssess(final String id) {
        Map map = MapUtil.getInstance().addParms("page", 1).addParms("pageSize", 2).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.GETASSESS + "/" + id, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("评论：" + result);
                AssessBean assessBean = JSON.parseObject(result, AssessBean.class);
                GoodsAssessAdapter goodsAssessAdapter = new GoodsAssessAdapter(mContext, assessBean.getRecords(), R.layout.rv_goods_assess);

                goodsAssessAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        jumpToAssess(id);
                    }
                });
                if (getView() != null) {
                    getView().loadAssess(goodsAssessAdapter, assessBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void loadCommend(String keyWords) {
        Map map = MapUtil.getInstance().addParms("categoryId", keyWords).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.HOTNEWSEARCH, map, SPUtil.getToken());
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
//                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
//                        intent.putExtra("id", commendList.get(position).getId() + "");
//                        intent.putExtra("commendId", commendList.get(position).getProductCategoryId() + "");
//                        intent.putExtra("sellerId", commendList.get(position).getSellerId());
//                        mContext.startActivity(intent);
                        ARouter.getInstance()
                                .build("/module_user_store/GoodsDetailActivity")
                                .withString("id", commendList.get(position).getId() + "")
                                .withString("sellerId", commendList.get(position).getSellerId())
                                .withString("commendId", commendList.get(position).getProductCategoryId() + "")
                                .navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void toAttention() {
        Map map = MapUtil.getInstance().addParms("productId", userGoodsDetail.getId()).addParms("type", 0).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("收藏：" + result);
                if ("true".equals(result)) {
                    getView().attention();
                } else {
                    getView().cancelAttention();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void lingquan() {
        if (couponBeanList != null) {
            if (couponBeanList.size() > 0) {
                PopUtil.lingquanPop(mContext, couponBeanList);
            } else {
                Toast.makeText(mContext, "无可领优惠券", Toast.LENGTH_SHORT).show();
            }
        } else {
            LogUtil.e("无数据");
        }
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
        if (userGoodsDetail != null && userGoodsDetail.getXsProductAttributes().size() > 0 && popList.size() > 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.pop_choose_goods, null);
            final ImageView img = view.findViewById(R.id.pop_choose_goods_img);
            TextView price = view.findViewById(R.id.pop_choose_goods_price);
            TextView type = view.findViewById(R.id.pop_choose_goods_type);
            ImageView cancel = view.findViewById(R.id.pop_choose_goods_cancel);
//            flow1 = view.findViewById(R.id.pop_choose_goods_flow1);
//            flow2 = view.findViewById(R.id.pop_choose_goods_flow2);
            TextView minus = view.findViewById(R.id.pop_choose_goods_minus);
            TextView add = view.findViewById(R.id.pop_choose_goods_add);
            final TextView count = view.findViewById(R.id.pop_choose_goods_count);
            TextView shopCart = view.findViewById(R.id.pop_choose_goods_cart);
            TextView buy = view.findViewById(R.id.pop_choose_goods_buy);
//            TextView title1 = view.findViewById(R.id.pop_choose_goods_title1);
//            TextView title2 = view.findViewById(R.id.pop_choose_goods_title2);
            RecyclerView rv = view.findViewById(R.id.pop_choose_goods_rv);


            final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) mContext.getResources().getDimension(R.dimen.dp_444), true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
            popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);

            final StringBuffer sb = new StringBuffer();
            sb.append("请选择");
            for (int i = 0; i < userGoodsDetail.getXsProductAttributes().size(); i++) {
                sb.append(userGoodsDetail.getXsProductAttributes().get(i).getName() + "  ");
            }
            type.setText(sb);


            PopUtil.setTransparency(mContext, 0.3f);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    PopUtil.setTransparency(mContext, 1f);
                    if (colorPosition != -1 && sizePosition != -1) {
                        isChoose = true;
//                        getView().yixuanze(popList.get(colorPosition).getType(),popList.get(sizePosition).getType());
                    } else {
                        isChoose = false;
                        getView().weixuanze(sb.toString().replace("请选择", ""));
                    }
                }
            });

            Glide.with(mContext).load(userGoodsDetail.getPic()).into(img);

//            title1.setText(userGoodsDetail.getXsProductAttributes().get(0).getName());
//            title2.setText(userGoodsDetail.getXsProductAttributes().get(1).getName());
            count.setText("" + quantity);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(layoutManager);
            popChooseAdapter = new PopChooseAdapter(mContext, popList, R.layout.pop_rv_choose);
            rv.setAdapter(popChooseAdapter);

            popChooseAdapter.setOnPopChooseListener(new MyRecyclerAdapter.OnPopChooseListener() {
                @Override
                public void onPopChoose(int parentPos, int childPos) {
                    if (parentPos == 0) {
                        colorPosition = childPos;
                        if (userGoodsDetail.getXsProductAttributes().size() > 1) {
                            initSizeList(parentPos, childPos);
                            popChooseAdapter.notifyDataSetChanged();
                        }
                    }
                    if (colorPosition != -1 && parentPos == 1) {
                        sizePosition = childPos;
                        if (userGoodsDetail.getXsProductAttributes().size() > 2) {
                            initSizeList(parentPos, childPos);
                            popChooseAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

//            add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (sizePosition == -1 || colorPosition == -1) {
//                        Toast.makeText(mContext, "请先选择商品", Toast.LENGTH_SHORT).show();
//                    } else {
//                        if (quantity >= userGoodsDetail.getSkuStockList().get().getList().get(sizePosition).getStock()) {
//
//                            quantity = dataList.get(0).getList().get(0).getStock();
//                        } else {
//                            quantity++;
//                        }
//                        count.setText("" + quantity);
//                    }
//                }
//            });
//
//            shopCart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (colorPosition == -1 || sizePosition == -1) {
//                        Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
//                    } else {
//                        popupWindow.dismiss();
//                        chooseOrAddCart();
//
//                    }
//                }
//            });

//            buy.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (colorPosition == -1 || sizePosition == -1) {
//                        Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
//                    } else {
//                        popupWindow.dismiss();
//                        chooseOrJump();
//                    }
//                }
//            });
        }
    }

    private void initSizeList(int parentPos, int childPos) {
        List<ChooseInsideBean> list = new ArrayList<>();
        for (int j = 0; j < dataList.size(); j++) {
            if (sp1List.get(childPos).equals(dataList.get(j).getSp1()) && dataList.get(j).getStock() > 0) {
                list.add(new ChooseInsideBean(dataList.get(j).getSp2(), false, dataList.get(j).getPrice()));
            }
        }
        popList.get(parentPos + 1).setList(list);
    }

    public void jumpToAssess(String id) {
        Intent intent = new Intent(mContext, AssessActivity.class);
        intent.putExtra("id", id);
        mContext.startActivity(intent);
    }

    public void jumpToShop() {
        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
    }

    public void chooseOrJump() {
        if (isChoose) {
//            OrderConfirmBean orderConfirmBean = new OrderConfirmBean();
//            orderConfirmBean.setSellerId(userGoodsDetail.getSellerId() + "");
//            orderConfirmBean.setSellerName(userGoodsDetail.getSellerName());
//            orderConfirmBean.setProductSkuId(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getId());
//            orderConfirmBean.setQuantity((int) quantity);
//            orderConfirmBean.setSp1(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getSp1());
//            orderConfirmBean.setSp2(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getSp2());
//            orderConfirmBean.setPrice(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getPrice());
//            orderConfirmBean.setSourceType(1);
//            orderConfirmBean.setPic(userGoodsDetail.getPic());
//            orderConfirmBean.setProductName(userGoodsDetail.getName());
//            orderConfirmBean.setFeightTemplateId(userGoodsDetail.getFeightTemplateId());
//            orderConfirmBean.setStock(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getStock());
//            orderConfirmBean.setProductId(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getProductId());
//            orderConfirmBean.setProductCategoryId(userGoodsDetail.getProductCategoryId());
//            orderConfirmBean.setProductPrice(userGoodsDetail.getPrice());
//            orderConfirmBean.setProductSn(userGoodsDetail.getProductSn());
//            orderConfirmBean.setPromotionPrice(userGoodsDetail.getPromotionPrice());
//            orderConfirmBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getSkuName() + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getSp2());
//
//            Intent intent = new Intent(mContext, OrderConfirmActivity.class);
//            intent.putExtra("order", orderConfirmBean);
//            mContext.startActivity(intent);
        } else {
            chooseGoodsPop();
        }
    }

    public void chooseOrAddCart() {
        if (isChoose) {
//            AddCartBean cartBean = new AddCartBean();
//            cartBean.setChecked(0);
//            cartBean.setPrice(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getPrice());
//            cartBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getSkuName() + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getSp2());
//            cartBean.setProductCategoryId(userGoodsDetail.getProductCategoryId());
//            cartBean.setProductId(userGoodsDetail.getId());
//            cartBean.setProductSkuId(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getId());
//            cartBean.setProductName(userGoodsDetail.getName());
//            cartBean.setProductPic(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getPic());
//            cartBean.setProductSn(userGoodsDetail.getProductSn());
//            cartBean.setProductSubTitle(userGoodsDetail.getSubTitle());
//            cartBean.setQuantity((int) quantity);
//            cartBean.setSellerId(userGoodsDetail.getSellerId());
//            cartBean.setSellerName(userGoodsDetail.getSellerName());
//            cartBean.setSp1(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getSp1());
//            cartBean.setSp2(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getSp2());
//            cartBean.setSp3(userGoodsDetail.getStoInfo().getRecords().get(colorPosition).getList().get(sizePosition).getSp3());
//            cartBean.setUserId(SPUtil.getUserCode());
//
//            String jsonString = JSON.toJSONString(cartBean);
//            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
//            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.ADD_CART, requestBody, SPUtil.getToken());
//            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
//                @Override
//                public void onSuccess(String result, String msg) {
//                    LogUtil.e("添加购物车：" + result);
//                    Toast.makeText(mContext, "添加成功，可前往购物车查看", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onError(String errorCode, String errorMsg) {
//
//                }
//            }));
        } else {
            chooseGoodsPop();
        }
    }

    public void jumpToCart() {
        Intent intent = new Intent(mContext, UserActivity.class);
        intent.putExtra("key", CommonResource.JUMP_CART);
        mContext.startActivity(intent);
    }

    public void loadCoupon(String id, String sellerId) {
        Map map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("goodsId", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getHead(CommonResource.COUPON_KELING, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("优惠券：" + result);
                couponBeanList = JSON.parseArray(result, UserCouponBean.class);
                GoodsCouponAdapter goodsCouponAdapter = new GoodsCouponAdapter(mContext, couponBeanList, R.layout.rv_goods_coupon);

                goodsCouponAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        lingquan();
                    }
                });
                if (getView() != null) {
                    getView().loadCoupon(goodsCouponAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("优惠券：" + errorCode + "--------------" + errorMsg);
            }
        }));
    }
}
