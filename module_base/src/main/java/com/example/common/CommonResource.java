package com.example.common;

public class CommonResource {
    public static final String BASEURL = "http://192.168.1.30:9004";
    public static final String BASEURL2 = "http://192.168.1.23:9005";
    public static final String BASEURL4 = "http://192.168.1.2:4001";
    public static final String BASEURLCART= "http://192.168.1.30:9004";

    public static final String ALLCATEGORT = "/rest/goods/allCategory"; //商品分类
    public static final String GETGOODSDETAIL = "/rest/goods";  //获取商品详情
    //多用户商城---模糊搜索框,参数:searchInfo->搜索内容,pageNum->当前页,pageSize->每页显示条数,默认20,startPrice->开始价格,endPrice->结束价格,categoryId->分类Id->productAttributeCategoryId->分类规格priceAsc->价格升序,priceDesc->价格降序,sellerId->商家IdsaleAsc->销量升序,saleDesc->销量降序,newStatus->1新品推荐
    public static final String HOTNEWSEARCH = "/rest/goods/search";
    public static final String USERSBANNER = "/rest/parameter/homeAdvertise";   //多用户商城轮播图
    public static final String TYPENAVBAR = "/rest/goods/category";     //多用户商城---分类导航
    public static final String WXLOGIN_CODE = "/rest/wx/login";   //微信登录把code传给后台
    public static final String WXBIND_CODE = "/rest/wx/save";   //微信绑定把code传给后台
    public static final String GETREGISTERCODE = "/rest/register";      //获取注册验证码
    public static final String PHONEREGISTER = "/rest/register/phone";      //手机号注册
    public static final String WXLOGIN_PHONE = "/rest/wx/save";       //微信登陆后绑定手机号
    public static final String GETUSERINFO = "/rest/user/info";     //获取个人信息
    public static final String LOGIN_PHONE = "/rest/login";         //手机号密码登录----获取登录/忘记密码验证码
    public static final String LOGIN_CODE = "/rest/login/code";     //手机验证码登录
    public static final String FORGET = "/rest/forget/password";     //忘记密码
    public static final String REVISEINFO = "/rest/update/detail";         //修改昵称、签名
    public static final String REVISEHEADER = "/rest/update/icon";         //修改头像
    public static final String REVISEPASSWORD = "/rest/update/password";    //修改密码
    public static final String REVISEPHONE = "/rest/phone";         //修改手机号
    public static final String LOGOUT = "/rest/logout";             //退出登录
    public static final String GROUP_FANS = "/rest/user/fans";    //获取团队粉丝

    public static final String GOODSCOLLECTION = "/rest/user/product/page";   //商品收藏
    public static final String SELLERPAGE = "/rest/user/seller/page";   //商家收藏
    public static final String HISTORYALL = "/rest/user/history/all";   //浏览历史
    public static final String FAVORITEDELETE = "/rest/user/favorite/delete";   //商家收藏删除

    public static final String WXAPPID = "wxf08fd2965ac9ac30";
    public static final String CODE_SUCCESS = "0";  //联网成功
    public static final String TOKEN_EXPIRE = "2";    //token过期
    public static final String ERROR = "ERROR";
    public static final String USER_BACK = "USER_BACK";
    public static final String JUMP_CLASSIFY = "JUMP_CLASSIFY";
    public static final String JUMP_CART = "JUMP_CART";
    public static final String TOKEN = "token";
}
