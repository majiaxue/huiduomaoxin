package com.example.common;

public class CommonResource {
    public static final String BASEURL = "http://192.168.1.30:9001";
    public static final String BASEURL2 = "http://192.168.1.23:9001";
    public static final String BASEURL4 = "http://192.168.1.2:4001";
    public static final String BASEURLCART= "http://192.168.1.30:9004";

    public static final String ALLCATEGORT = "/rest/goods/allCategory"; //商品分类
    public static final String GETGOODSDETAIL = "/rest/goods";  //获取商品详情
    public static final String HOTNEWSEARCH = "/rest/goods/search";     //多用户商城---热销 新品推荐 搜索
    public static final String USERSBANNER = "/rest/parameter/homeAdvertise";   //多用户商城轮播图
    public static final String TYPENAVBAR = "/rest/goods/category";     //多用户商城---分类导航
    public static final String WXLOGIN_CODE = "/wx/login";   //微信登录把code传给后台

    public static final String GOODSCOLLECTION = "/rest/user/product/all";   //商品收藏
    public static final String SELLERPAGE = "/rest/user/seller/all";   //商家收藏
    public static final String HISTORYALL = "/rest/user/history/all";   //浏览历史
    public static final String PDDGOODS = "/rest/pdd/goods/pddgoods";   //pdd获取商品列表
    public static final String GOODSCATS = "/rest/pdd/goods/goodscats/0";   //pdd获取商品标准类目接口
    public static final String FAVORITEDELETE = "/rest/user/favorite/delete";   //删除收藏商品,取消关注商家
    public static final String HISTORYDELETE = "/rest/user/history/delete";   //浏览历史记录删除
    public static final String CARTLIST = "/rest/order/list";   //购物车




    public static String WX_CODE = "";
    public static final String WXAPPID = "wxf08fd2965ac9ac30";
    public static final String CODE_SUCCESS = "0";
    public static final String ERROR = "ERROR";
    public static final String USER_BACK = "USER_BACK";
    public static final String JUMP_CLASSIFY = "JUMP_CLASSIFY";
    public static final String JUMP_CART = "JUMP_CART";
}
