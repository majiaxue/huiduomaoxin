package com.example.goods_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assess.AssessActivity;
import com.example.entity.AssessBean;
import com.example.entity.ChooseGoodsBean;
import com.example.entity.CommendBean;
import com.example.entity.CouponBean;
import com.example.entity.ParmsBean;
import com.example.entity.TopBannerBean;
import com.example.goods_detail.adapter.ColorFlowLayoutAdapter;
import com.example.goods_detail.adapter.GoodsAssessAdapter;
import com.example.goods_detail.adapter.GoodsCouponAdapter;
import com.example.goods_detail.adapter.GoodsImageAdapter;
import com.example.goods_detail.adapter.SizeFlowLayoutAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.OnFlowSelectListener;
import com.example.utils.PopUtil;
import com.example.view.flowLayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    //是否关注
    private boolean isAttention = false;
    //选择商品列表
    private List<ChooseGoodsBean> dataList;
    //流式布局--颜色
    private TagFlowLayout flow1;
    //流式布局--尺码
    private TagFlowLayout flow2;
    //颜色选中下标
    private int colorPosition = -1;
    //尺码选中下标
    private int sizePosition = -1;
    //尺码列表
    private List<ChooseGoodsBean.GoodsSize> sizeList = new ArrayList<>();

    public GoodsDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        //优惠券
        List couponList = new ArrayList();
        couponList.add("满50减5");
        couponList.add("满1500减100");
        GoodsCouponAdapter goodsCouponAdapter = new GoodsCouponAdapter(mContext, couponList, R.layout.rv_goods_coupon);
        if (getView() != null) {
            getView().loadCoupon(goodsCouponAdapter);
        }
        //图片
        List imgList = new ArrayList();
        imgList.add("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg");
        imgList.add("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg");
        imgList.add("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg");
        imgList.add("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg");
        GoodsImageAdapter goodsImageAdapter = new GoodsImageAdapter(mContext, imgList, R.layout.rv_goods_choose_image);
        if (getView() != null) {
            getView().loadImage(goodsImageAdapter);
        }
        //评论
        List<AssessBean> assessList = new ArrayList<>();
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        assessList.add(new AssessBean("http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg", "上帝发誓", "衣服包装很好，薄款适中，款式好看"));
        GoodsAssessAdapter goodsAssessAdapter = new GoodsAssessAdapter(mContext, assessList, R.layout.rv_goods_assess);
        if (getView() != null) {
            getView().loadAssess(goodsAssessAdapter);
        }
        //推荐
        List<CommendBean> commendList = new ArrayList<>();
        commendList.add(new CommendBean(R.drawable.img_114, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_115, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_116, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_117, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_114, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_115, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        CommendAdapter commendAdapter = new CommendAdapter(mContext, commendList, R.layout.rv_commend);
        if (getView() != null) {
            getView().loadCommend(commendAdapter);
        }
        //轮播图
        List<TopBannerBean> bannerList = new ArrayList<>();
        bannerList.add(new TopBannerBean("http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg"));
        bannerList.add(new TopBannerBean("http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg"));
        bannerList.add(new TopBannerBean("http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg"));
        if (getView() != null) {
            getView().loadBanner(bannerList);
        }
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

    public void chooseGoods() {
        dataList = new ArrayList<>();
        List<ChooseGoodsBean.GoodsSize> sizeList1 = new ArrayList<>();
        sizeList1.add(new ChooseGoodsBean.GoodsSize("M", 100));
        sizeList1.add(new ChooseGoodsBean.GoodsSize("L", 0));
        sizeList1.add(new ChooseGoodsBean.GoodsSize("XL", 100));
        sizeList1.add(new ChooseGoodsBean.GoodsSize("XXL", 100));
        sizeList1.add(new ChooseGoodsBean.GoodsSize("XXXL", 100));

        List<ChooseGoodsBean.GoodsSize> sizeList2 = new ArrayList<>();
        sizeList2.add(new ChooseGoodsBean.GoodsSize("M", 100));
        sizeList2.add(new ChooseGoodsBean.GoodsSize("L", 10));
        sizeList2.add(new ChooseGoodsBean.GoodsSize("XL", 100));
        sizeList2.add(new ChooseGoodsBean.GoodsSize("XXL", 100));
        sizeList2.add(new ChooseGoodsBean.GoodsSize("XXXL", 100));

        List<ChooseGoodsBean.GoodsSize> sizeList3 = new ArrayList<>();
        sizeList3.add(new ChooseGoodsBean.GoodsSize("M", 100));
        sizeList3.add(new ChooseGoodsBean.GoodsSize("L", 10));
        sizeList3.add(new ChooseGoodsBean.GoodsSize("XL", 0));
        sizeList3.add(new ChooseGoodsBean.GoodsSize("XXL", 0));
        sizeList3.add(new ChooseGoodsBean.GoodsSize("XXXL", 100));

        List<ChooseGoodsBean.GoodsSize> sizeList4 = new ArrayList<>();
        sizeList4.add(new ChooseGoodsBean.GoodsSize("M", 0));
        sizeList4.add(new ChooseGoodsBean.GoodsSize("L", 10));
        sizeList4.add(new ChooseGoodsBean.GoodsSize("XL", 100));
        sizeList4.add(new ChooseGoodsBean.GoodsSize("XXL", 100));
        sizeList4.add(new ChooseGoodsBean.GoodsSize("XXXL", 100));
        dataList.add(new ChooseGoodsBean("黑色", sizeList1, R.drawable.img_48));
        dataList.add(new ChooseGoodsBean("白色", sizeList2, R.drawable.img_49));
        dataList.add(new ChooseGoodsBean("蓝色", sizeList3, R.drawable.img_50));
        dataList.add(new ChooseGoodsBean("没有颜色", sizeList4, R.drawable.img_51));
        chooseGoodsPop();
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
            }
        });

        sizeList.addAll(dataList.get(0).getSize());

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
                img.setImageResource(dataList.get(position).getImg());
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
                if (colorPosition == -1) {
                    Toast.makeText(mContext, "请先选择商品", Toast.LENGTH_SHORT).show();
                } else {
                    if (Integer.valueOf(count.getText().toString()) >= dataList.get(colorPosition).getSize().get(sizePosition).getCount()) {
                        count.setText(dataList.get(0).getSize().get(0).getCount() + "");
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
            if (dataList.get(position).getSize().get(i).getCount() > 0) {
                sizeList.add(dataList.get(position).getSize().get(i));
            }
        }
    }

    public void jumpToAssess() {
        mContext.startActivity(new Intent(mContext, AssessActivity.class));
    }
}
