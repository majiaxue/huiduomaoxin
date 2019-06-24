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

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.assess.AssessActivity;
import com.example.bean.AddCartBean;
import com.example.bean.AssessBean;
import com.example.bean.BannerBean;
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
import com.example.goods_detail.adapter.PopFlowLayoutAdapter;
import com.example.goods_detail.adapter.SecondFlowAdapter;
import com.example.goods_detail.adapter.ThirdFlowAdapter;
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
import com.example.utils.OnFlowSelectListener;
import com.example.utils.PopUtil;
import com.example.utils.SPUtil;
import com.example.view.flowLayout.FlowLayout;
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

    private TagFlowLayout flow3;
    //颜色选中下标
    private int sp1Position = -1;
    //尺码选中下标
    private int sp2Position = -1;

    private int sp3Position = -1;

    private long quantity = 1;

    private boolean isChoose = false;

    private boolean isTan = false;
    //尺码列表
//    private List<UserGoodsDetail.StoInfoBean.RecordsBean.ListBean> sp2List = new ArrayList<>();
    //缩略图
    private List imgList = new ArrayList();
    private List<ChooseInsideBean> sp1List = new ArrayList<>();
    private List<ChooseInsideBean> sp2List = new ArrayList<>();
    private List<ChooseInsideBean> sp3List = new ArrayList<>();

    //为你推荐
    List<HotSaleBean.DataBean> commendList = new ArrayList<>();

    private UserGoodsDetail userGoodsDetail;
    private List<UserCouponBean> couponBeanList;
    private PopFlowLayoutAdapter sp1Adapter;
    private SecondFlowAdapter sp2Adapter;
    private SecondFlowAdapter sp3Adapter;


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
                        if (dataList.get(i).getStock() > 0) {
                            sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                        } else {
                            sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), false));
                        }
                    }
                }

                if (dataList.size() > 0 && userGoodsDetail.getXsProductAttributes().size() > 1) {
                    String[] split = userGoodsDetail.getXsProductAttributes().get(1).getInputList().split(",");
                    for (int i = 0; i < split.length; i++) {
                        sp2List.add(new ChooseInsideBean(split[i]));
                    }
                }
                if (dataList.size() > 0 && userGoodsDetail.getXsProductAttributes().size() > 2) {
                    String[] split = userGoodsDetail.getXsProductAttributes().get(2).getInputList().split(",");
                    for (int i = 0; i < split.length; i++) {
                        sp3List.add(new ChooseInsideBean(split[i]));
                    }
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
                    getView().loadUI(userGoodsDetail, sp1List.size());
                }

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
        isTan = true;
        if (userGoodsDetail != null && userGoodsDetail.getXsProductAttributes().size() > 0 && dataList.size() > 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.pop_choose_goods, null);
            final ImageView img = view.findViewById(R.id.pop_choose_goods_img);
            final TextView price = view.findViewById(R.id.pop_choose_goods_price);
            final TextView type = view.findViewById(R.id.pop_choose_goods_type);
            ImageView cancel = view.findViewById(R.id.pop_choose_goods_cancel);
            flow1 = view.findViewById(R.id.pop_choose_goods_flow1);
            flow2 = view.findViewById(R.id.pop_choose_goods_flow2);
            flow3 = view.findViewById(R.id.pop_choose_goods_flow3);
            TextView minus = view.findViewById(R.id.pop_choose_goods_minus);
            TextView add = view.findViewById(R.id.pop_choose_goods_add);
            final TextView count = view.findViewById(R.id.pop_choose_goods_count);
            TextView shopCart = view.findViewById(R.id.pop_choose_goods_cart);
            TextView buy = view.findViewById(R.id.pop_choose_goods_buy);
            TextView title1 = view.findViewById(R.id.pop_choose_goods_title1);
            TextView title2 = view.findViewById(R.id.pop_choose_goods_title2);
            TextView title3 = view.findViewById(R.id.pop_choose_goods_title3);
//            RecyclerView rv = view.findViewById(R.id.pop_choose_goods_rv);

            if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                title2.setVisibility(View.GONE);
                title3.setVisibility(View.GONE);
                flow2.setVisibility(View.GONE);
                flow3.setVisibility(View.GONE);
                oneCanClick();
                title1.setText(userGoodsDetail.getXsProductAttributes().get(0).getName());
            } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                title3.setVisibility(View.GONE);
                flow3.setVisibility(View.GONE);
                title2.setText(userGoodsDetail.getXsProductAttributes().get(1).getName());
                title1.setText(userGoodsDetail.getXsProductAttributes().get(0).getName());
            } else {
                title1.setText(userGoodsDetail.getXsProductAttributes().get(0).getName());
                title2.setText(userGoodsDetail.getXsProductAttributes().get(1).getName());
                title3.setText(userGoodsDetail.getXsProductAttributes().get(2).getName());
            }

            sp1Adapter = new PopFlowLayoutAdapter(sp1List, mContext);
            flow1.setAdapter(sp1Adapter);
            flow1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    sp1Position = position;
                    if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                        price.setText("￥" + sp1List.get(sp1Position).getPrice());
                        isChoose = true;
                        type.setText("已选择：" + sp1List.get(sp1Position).getContent());
                    } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                        if (sp2Position == -1) {
                            initSizeList(1);
                            type.setText("已选择：" + sp1List.get(sp1Position).getContent());
                        } else {
                            isChoose = true;
                            initSizeList(1);
                            price.setText("￥" + stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
                            type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                        }
                    } else {
                        if (sp2Position != -1 && sp3Position == -1) {

                        }
                    }
                    return false;
                }
            });

            if (userGoodsDetail.getXsProductAttributes().size() > 1) {
                sp2Adapter = new SecondFlowAdapter(sp2List, mContext);
                flow2.setAdapter(sp2Adapter);
                flow2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        sp2Position = position;
                        if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                            if (sp1Position == -1) {
                                initSizeList(2);
                                type.setText("已选择：" + sp2List.get(sp2Position).getContent());
                            } else {
                                isChoose = true;
                                initSizeList(2);
                                price.setText("￥" + stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
                                type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                            }
                        } else if (userGoodsDetail.getXsProductAttributes().size() == 3 && sp1Position != -1) {
                            initThirdList(position);
                            type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                        }
                        return false;
                    }
                });
            }
            if (userGoodsDetail.getXsProductAttributes().size() > 2) {
                sp3Adapter = new SecondFlowAdapter(sp3List, mContext);
                flow3.setAdapter(sp3Adapter);
                flow3.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        if (sp1Position != -1 && sp2Position != -1) {
                            isChoose = true;
                            sp3Position = position;
                            type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent() + "、" + sp3List.get(sp3Position).getContent());
                            price.setText("￥" + sp3List.get(position).getPrice());
                        }
                        return false;
                    }
                });
            }


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

            if (isChoose) {
                isTan = false;
                if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                    sp1Adapter.setSelectedList(sp1Position);
                    type.setText("已选择：" + sp1List.get(sp1Position).getContent());
                    price.setText("￥" + sp1List.get(sp1Position).getPrice());
                } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                    sp1Adapter.setSelectedList(sp1Position);
                    sp2Adapter.setSelectedList(sp2Position);
                    price.setText("￥" + sp2List.get(sp2Position).getPrice());
                    type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent());
                } else {
                    sp1Adapter.setSelectedList(sp1Position);
                    sp2Adapter.setSelectedList(sp2Position);
                    sp3Adapter.setSelectedList(sp3Position);
                    type.setText("已选择：" + sp1List.get(sp1Position).getContent() + "、" + sp2List.get(sp2Position).getContent() + "、" + sp3List.get(sp3Position).getContent());
                    price.setText("￥" + sp3List.get(sp3Position).getPrice());
                }
                isTan = true;
            }

            PopUtil.setTransparency(mContext, 0.3f);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    PopUtil.setTransparency(mContext, 1f);
                    if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                        if (sp1Position == -1) {
                            getView().weixuanze(sb.toString().replace("请选择", ""));
                        } else {
                            getView().yixuanze(type.getText().toString());
                        }
                    } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                        if (sp1Position == -1 || sp2Position == -1) {
                            getView().weixuanze(sb.toString().replace("请选择", ""));
                        } else {
                            getView().yixuanze(type.getText().toString());
                        }
                    } else {
                        if (sp1Position == -1 || sp2Position == -1 || sp3Position == -1) {
                            getView().weixuanze(sb.toString().replace("请选择", ""));
                        } else {
                            getView().yixuanze(type.getText().toString());
                        }
                    }
                }
            });

            Glide.with(mContext).load(userGoodsDetail.getPic()).into(img);
            count.setText("" + quantity);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                        if (sp1Position == -1) {
                            Toast.makeText(mContext, "请先选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            if (quantity >= dataList.get(sp1Position).getStock()) {
                                quantity = dataList.get(sp1Position).getStock();
                            } else {
                                quantity++;
                            }
                        }
                    } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                        if (sp1Position == -1 || sp2Position == -1) {
                            Toast.makeText(mContext, "请先选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            if (quantity >= stock1(sp1List.get(sp1Position).getContent(), sp2Position).getStock()) {
                                quantity = stock1(sp1List.get(sp1Position).getContent(), sp2Position).getStock();
                            } else {
                                quantity++;
                            }
                        }
                    } else {
                        if (sp1Position == -1 || sp2Position == -1 || sp3Position == -1) {
                            Toast.makeText(mContext, "请先选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            if (quantity >= stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getStock()) {
                                quantity = stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getStock();
                            } else {
                                quantity++;
                            }
                        }
                    }
                    count.setText("" + quantity);
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (quantity <= 1) {
                        quantity = 1;
                    } else {
                        quantity--;
                    }
                }
            });

            shopCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                        if (sp1Position == -1) {
                            Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            popupWindow.dismiss();
                            chooseOrAddCart();
                        }
                    } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                        if (sp1Position == -1 || sp2Position == -1) {
                            Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            popupWindow.dismiss();
                            chooseOrAddCart();
                        }
                    } else {
                        if (sp1Position == -1 || sp2Position == -1 || sp3Position == -1) {
                            Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            popupWindow.dismiss();
                            chooseOrAddCart();
                        }
                    }
                }
            });

            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                        if (sp1Position == -1) {
                            Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            popupWindow.dismiss();
                            chooseOrJump();
                        }
                    } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                        if (sp1Position == -1 || sp2Position == -1) {
                            Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            popupWindow.dismiss();
                            chooseOrJump();
                        }
                    } else {
                        if (sp1Position == -1 || sp2Position == -1 || sp3Position == -1) {
                            Toast.makeText(mContext, "请选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            popupWindow.dismiss();
                            chooseOrJump();
                        }
                    }
                }
            });
        }
    }

    private UserGoodsDetail.SkuStockListBean stock1(String str, int position) {
        List<UserGoodsDetail.SkuStockListBean> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (str.equals(dataList.get(i).getSp1())) {
                list.add(dataList.get(i));
            }
        }
        return list.get(position);
    }

    private UserGoodsDetail.SkuStockListBean stock2(String str, String str2, int position) {
        List<UserGoodsDetail.SkuStockListBean> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (str.equals(dataList.get(i).getSp1()) && str2.equals(dataList.get(i).getSp2())) {
                list.add(dataList.get(i));
            }
        }
        return list.get(position);
    }

    private void initSizeList(int clickPos) {
        if (clickPos == 1) {
            sp2List.clear();
            List<Integer> list = new ArrayList<>();
            int temp = 0;
            for (int i = 0; i < dataList.size(); i++) {
                if (sp1List.get(sp1Position).getContent().equals(dataList.get(i).getSp1())) {
                    boolean isHas = false;
                    for (int j = 0; j < sp2List.size(); j++) {
                        if (sp2List.get(j).getContent().equals(dataList.get(i).getSp2())) {
                            isHas = true;
                        }
                    }
                    if (!isHas) {
                        if (dataList.get(i).getStock() > 0) {
                            sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPrice(), true));
                        } else {
                            list.add(temp);
                            sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPrice(), false));
                        }
                        temp++;
                    }
                }
            }

            flow2.setNoCheckList(list);
            sp2Adapter.notifyDataChanged();
            if (sp2Position != -1) {
                sp2Adapter.setSelectedList(sp2Position);
            }
        } else if (clickPos == 2) {
            sp1List.clear();
            List<Integer> list = new ArrayList<>();
            int temp = 0;
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getSp2().equals(sp2List.get(sp2Position).getContent())) {
                    if (dataList.get(i).getStock() > 0) {
                        sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                    } else {
                        list.add(temp);
                        sp1List.add(new ChooseInsideBean(dataList.get(i).getSp1(), dataList.get(i).getPic(), dataList.get(i).getPrice(), false));
                    }
                    temp++;
                }
            }

            flow1.setNoCheckList(list);
            sp1Adapter.notifyDataChanged();
            if (sp1Position != -1) {
                sp1Adapter.setSelectedList(sp1Position);
            }
        }
    }

    private void initThirdList(int clickPos) {
        if (clickPos == 1) {
            sp2List.clear();
            sp3List.clear();
            int temp2 = 0;
            int temp3 = 0;
            List<Integer> list2 = new ArrayList<>();
            List<Integer> list3 = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                if (sp2Position == -1 && sp3Position == -1) {
                    boolean isHas = false;
                    boolean hasStock = false;
                    if (dataList.get(i).getSp1().equals(sp1List.get(sp1Position).getContent())) {
                        for (int j = 0; j < sp2List.size(); j++) {
                            if (dataList.get(i).getSp2().equals(sp2List.get(j).getContent())) {
                                isHas = true;
                                break;
                            }
                        }
                        if (!isHas){
                            sp2List.add(new ChooseInsideBean(dataList.get(i).getSp2(), dataList.get(i).getPic(), dataList.get(i).getPrice(), true));
                        }
                    }


                }
            }
        }
        sp3List.clear();
        for (int i = 0; i < dataList.size(); i++) {
            if (sp1List.get(sp1Position).getContent().equals(dataList.get(i).getSp1()) && sp2List.get(clickPos).getContent().equals(dataList.get(i).getSp2()) && dataList.get(i).getStock() > 0) {
                sp3List.add(new ChooseInsideBean(dataList.get(i).getSp3(), dataList.get(i).getPrice()));
            }
        }
        sp3Adapter.notifyDataChanged();
    }

    private void oneCanClick() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < sp1List.size(); i++) {
            if (dataList.get(i).getStock() <= 0) {
                list.add(i);
                sp1List.get(i).setCanClick(false);
            }
        }
        flow1.setNoCheckList(list);
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
            OrderConfirmBean orderConfirmBean = new OrderConfirmBean();

            if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                orderConfirmBean.setSellerId(userGoodsDetail.getSellerId() + "");
                orderConfirmBean.setSellerName(userGoodsDetail.getSellerName());
                orderConfirmBean.setProductSkuId(dataList.get(sp1Position).getId() + "");
                orderConfirmBean.setQuantity((int) quantity);
                orderConfirmBean.setSp1(sp1List.get(sp1Position).getContent());
                orderConfirmBean.setPrice(sp1List.get(sp1Position).getPrice());
                orderConfirmBean.setSourceType(1);
                orderConfirmBean.setPic(userGoodsDetail.getPic());
                orderConfirmBean.setProductName(userGoodsDetail.getName());
                orderConfirmBean.setFeightTemplateId((long) userGoodsDetail.getFeightTemplateId());
                orderConfirmBean.setStock(dataList.get(sp1Position).getStock());
                orderConfirmBean.setProductId(userGoodsDetail.getId() + "");
                orderConfirmBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
                orderConfirmBean.setProductPrice(userGoodsDetail.getPrice());
                orderConfirmBean.setProductSn(userGoodsDetail.getProductSn());
                orderConfirmBean.setPromotionPrice(sp1List.get(sp1Position).getPrice());
                orderConfirmBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + ":" + sp1List.get(sp1Position).getContent());

            } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                orderConfirmBean.setSellerId(userGoodsDetail.getSellerId() + "");
                orderConfirmBean.setSellerName(userGoodsDetail.getSellerName());
                orderConfirmBean.setProductSkuId(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getId() + "");
                orderConfirmBean.setQuantity((int) quantity);
                orderConfirmBean.setSp1(sp1List.get(sp1Position).getContent());
                orderConfirmBean.setSp2(sp2List.get(sp2Position).getContent());
                orderConfirmBean.setPrice(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
                orderConfirmBean.setSourceType(1);
                orderConfirmBean.setPic(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPic());
                orderConfirmBean.setProductName(userGoodsDetail.getName());
                orderConfirmBean.setFeightTemplateId((long) userGoodsDetail.getFeightTemplateId());
                orderConfirmBean.setStock(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getStock());
                orderConfirmBean.setProductId(userGoodsDetail.getId() + "");
                orderConfirmBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
                orderConfirmBean.setProductPrice(userGoodsDetail.getPrice());
                orderConfirmBean.setProductSn(userGoodsDetail.getProductSn());
                orderConfirmBean.setPromotionPrice(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
                orderConfirmBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + sp1List.get(sp1Position).getContent() + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + sp2List.get(sp2Position).getContent());

            } else {
                orderConfirmBean.setSellerId(userGoodsDetail.getSellerId() + "");
                orderConfirmBean.setSellerName(userGoodsDetail.getSellerName());
                orderConfirmBean.setProductSkuId(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getId() + "");
                orderConfirmBean.setQuantity((int) quantity);
                orderConfirmBean.setSp1(sp1List.get(sp1Position).getContent());
                orderConfirmBean.setSp2(sp2List.get(sp2Position).getContent());
                orderConfirmBean.setSp2(sp3List.get(sp3Position).getContent());
                orderConfirmBean.setPrice(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPrice());
                orderConfirmBean.setSourceType(1);
                orderConfirmBean.setPic(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPic());
                orderConfirmBean.setProductName(userGoodsDetail.getName());
                orderConfirmBean.setFeightTemplateId((long) userGoodsDetail.getFeightTemplateId());
                orderConfirmBean.setStock(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getStock());
                orderConfirmBean.setProductId(userGoodsDetail.getId() + "");
                orderConfirmBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
                orderConfirmBean.setProductPrice(userGoodsDetail.getPrice());
                orderConfirmBean.setProductSn(userGoodsDetail.getProductSn());
                orderConfirmBean.setPromotionPrice(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPrice());
                orderConfirmBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + sp1List.get(sp1Position) + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + sp2List.get(sp2Position).getContent() + "、" + userGoodsDetail.getXsProductAttributes().get(2).getName() + ":" + sp3List.get(sp3Position).getContent());

            }

            Intent intent = new Intent(mContext, OrderConfirmActivity.class);
            intent.putExtra("order", orderConfirmBean);
            mContext.startActivity(intent);
        } else {
            chooseGoodsPop();
        }
    }

    public void chooseOrAddCart() {
        if (isChoose) {
            AddCartBean cartBean = new AddCartBean();

            if (userGoodsDetail.getXsProductAttributes().size() == 1) {
                cartBean.setChecked(0);
                cartBean.setPrice(sp1List.get(sp1Position).getPrice());
                cartBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + ":" + sp1List.get(sp1Position).getContent());
                cartBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
                cartBean.setProductId(userGoodsDetail.getId() + "");
                cartBean.setProductSkuId(dataList.get(sp1Position).getId() + "");
                cartBean.setProductName(userGoodsDetail.getName());
                cartBean.setProductPic(dataList.get(sp1Position).getPic());
                cartBean.setProductSn(userGoodsDetail.getProductSn());
                cartBean.setProductSubTitle(userGoodsDetail.getSubTitle());
                cartBean.setQuantity((int) quantity);
                cartBean.setSellerId(userGoodsDetail.getSellerId() + "");
                cartBean.setSellerName(userGoodsDetail.getSellerName());
                cartBean.setSp1(sp1List.get(sp1Position).getContent());
                cartBean.setUserId(SPUtil.getUserCode());
            } else if (userGoodsDetail.getXsProductAttributes().size() == 2) {
                cartBean.setChecked(0);
                cartBean.setPrice(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPrice());
                cartBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + sp1List.get(sp1Position).getContent() + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + sp2List.get(sp2Position).getContent());
                cartBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
                cartBean.setProductId(userGoodsDetail.getId() + "");
                cartBean.setProductSkuId(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getId() + "");
                cartBean.setProductName(userGoodsDetail.getName());
                cartBean.setProductPic(stock1(sp1List.get(sp1Position).getContent(), sp2Position).getPic());
                cartBean.setProductSn(userGoodsDetail.getProductSn());
                cartBean.setProductSubTitle(userGoodsDetail.getSubTitle());
                cartBean.setQuantity((int) quantity);
                cartBean.setSellerId(userGoodsDetail.getSellerId() + "");
                cartBean.setSellerName(userGoodsDetail.getSellerName());
                cartBean.setSp1(sp1List.get(sp1Position).getContent());
                cartBean.setSp2(sp2List.get(sp2Position).getContent());
                cartBean.setUserId(SPUtil.getUserCode());
            } else {
                cartBean.setChecked(0);
                cartBean.setPrice(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPrice());
                cartBean.setProductAttr(userGoodsDetail.getXsProductAttributes().get(0).getName() + "：" + sp1List.get(sp1Position) + "、" + userGoodsDetail.getXsProductAttributes().get(1).getName() + "：" + sp2List.get(sp2Position).getContent() + "、" + userGoodsDetail.getXsProductAttributes().get(2).getName() + ":" + sp3List.get(sp3Position).getContent());
                cartBean.setProductCategoryId(userGoodsDetail.getProductCategoryId() + "");
                cartBean.setProductId(userGoodsDetail.getId() + "");
                cartBean.setProductSkuId(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getId() + "");
                cartBean.setProductName(userGoodsDetail.getName());
                cartBean.setProductPic(stock2(sp1List.get(sp1Position).getContent(), sp2List.get(sp2Position).getContent(), sp3Position).getPic());
                cartBean.setProductSn(userGoodsDetail.getProductSn());
                cartBean.setProductSubTitle(userGoodsDetail.getSubTitle());
                cartBean.setQuantity((int) quantity);
                cartBean.setSellerId(userGoodsDetail.getSellerId() + "");
                cartBean.setSellerName(userGoodsDetail.getSellerName());
                cartBean.setSp1(sp1List.get(sp1Position).getContent());
                cartBean.setSp2(sp2List.get(sp2Position).getContent());
                cartBean.setSp3(sp3List.get(sp3Position).getContent());
                cartBean.setUserId(SPUtil.getUserCode());
            }

            String jsonString = JSON.toJSONString(cartBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.ADD_CART, requestBody, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    Toast.makeText(mContext, "添加成功，可前往购物车查看", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {

                }
            }));
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
