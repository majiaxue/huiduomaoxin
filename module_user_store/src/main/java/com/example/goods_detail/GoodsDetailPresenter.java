package com.example.goods_detail;

import android.content.Context;

import com.example.entity.AssessBean;
import com.example.entity.ChooseGoodsBean;
import com.example.entity.CommendBean;
import com.example.entity.CouponBean;
import com.example.entity.ParmsBean;
import com.example.entity.TopBannerBean;
import com.example.goods_detail.adapter.GoodsAssessAdapter;
import com.example.goods_detail.adapter.GoodsCouponAdapter;
import com.example.goods_detail.adapter.GoodsImageAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_store.R;
import com.example.utils.PopUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    private boolean isAttention = false;

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
        List<ChooseGoodsBean> dataList = new ArrayList<>();
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
        dataList.add(new ChooseGoodsBean("黑色", sizeList2, R.drawable.img_48));
        dataList.add(new ChooseGoodsBean("黑色", sizeList3, R.drawable.img_48));
        dataList.add(new ChooseGoodsBean("黑色", sizeList4, R.drawable.img_48));
        PopUtil.chooseGoodsPop(mContext, dataList);
    }
}
