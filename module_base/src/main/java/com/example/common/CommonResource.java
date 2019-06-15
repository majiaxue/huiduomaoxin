package com.example.common;

public class CommonResource {
    public static final String BASEURL = "http://192.168.1.30:9001";
    public static final String BASEURL2 = "http://192.168.1.4:9001";
    public static final String BASEURL3 = "http://192.168.1.4:9005";//轮播图
    public static final String BASEURL6 = "http://192.168.1.4:5003";//商家申请
    public static final String BASEURL4 = "http://192.168.1.27:4001";//用户
    public static final String BASEURLCART = "http://192.168.1.30:9004";//购物车

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
    public static final String WXLOGIN_GETCODE = "/rest/wx";        //微信登陆后获取手机验证码
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
    public static final String GROUP_FANS_POPPLE = "/rest/user/fans/detail";    //团队粉丝--一级粉丝数、今日新增
    public static final String MOREN_ADDRESS = "/rest/address/default";     //获取默认收货地址
    public static final String GET_YUNGEI = "/rest/goods/queryFeight";      //获取运费
    public static final String GETBALANCE = "/rest/user/balance";       //查询余额
    public static final String COMMIT_ORDER = "/rest/order/nowPlace";      //提交订单
    public static final String IN_OUT = "/rest/user/incomeAndExpend";       //收入和支出(余额)---get:参数 type 0收入 1 支出
    public static final String TOPAY = "/alipay/signOrder";             //支付宝支付
    public static final String SUGGESSTION = "/rest/user/feedback";     //提交意见反馈
    public static final String QUERYSUGGESSTION = "/rest/user/feedback/all";    //查询意见反馈列表
    public static final String COLLECT = "/rest/user/product";      //商品收藏
    public static final String SUGGESTION = "/rest/user/feedback";     //提交意见反馈
    public static final String QUERYSUGGESTION = "/rest/user/feedback/all";    //查询意见反馈列表
    public static final String PAYSUCCESS = "/rest/order/modifyOrder";      //支付成功 修改订单状态

    public static final String GOODSCOLLECTION = "/rest/user/product/all";   //查看商品收藏
    public static final String SELLERPAGE = "/rest/user/seller/all";   //商家收藏
    public static final String HISTORYALL = "/rest/user/history/all";   //浏览历史
    public static final String PDDGOODS = "/rest/pdd/goods/pddgoods";   //pdd获取商品列表
    public static final String GOODSCATS = "/rest/pdd/goods/goodscats/0";   //pdd获取商品标准类目接口
    public static final String PDDGOODSDETAIL = "/rest/pdd/goods/detail";   //pdd获取商品详情信息
    public static final String TOPGOODS = "/rest/pdd/goods/topgoods";   //pdd获取获取爆款商品
    public static final String GOODSCOUPON = "/rest/pdd/goods/coupon";   //pdd跳转至拼多多->获取优惠券->下单
    public static final String FAVORITEDELETE = "/rest/user/favorite/delete";   //删除收藏商品,取消关注商家
    public static final String HISTORYDELETE = "/rest/user/history/delete";   //浏览历史记录删除
    public static final String CARTLIST = "/rest/order/list";   //购物车
    public static final String ADDRESSSHOW = "/rest/address/show";   //查询收货地址
    public static final String ADDRESSDEFAULT = "/rest/address/default";   //设置默认收货地址
    public static final String DELETEADDRESS = "/rest/address";   //删除收货地址
    public static final String AMENDADDRESS = "/rest/address";   //修改收货人地址
    public static final String ORDERALL = "/rest/user/order/all";   //查询登录用户所有订单
    public static final String ORDERSTATUS = "/rest/user/order/status";   //根据状态查询登录用户订单
    public static final String FAVORITESTATUS = "/rest/user/favorite";   //查询收藏状态
    public static final String RETURNTABLE = "/rest/order/returnTable";   //退换售后列表
    public static final String ADDRESSADD = "/rest/address/add";   //新增收货地址
    public static final String ADDRESSSELECT = "/rest/address/city";   //三级地址选择
    public static final String COUPONSTATUS = "/rest/user/coupon/status";//根据状态查询优惠券
    public static final String SELLERINFO = "/rest/seller/info";//根据状态查询优惠券
    public static final String TBKGOODSPRODUCTS = "/rest/tbk/goods/products";//淘宝客推荐
    public static final String TBKGOODSITEMDETAIL = "/rest/tbk/goods/itemdetail";//商品详情接口功能（店铺信息/产品介绍/主图视频/SKU）
    public static final String TBKGOODSGETGYURLBYALL = "/rest/tbk/goods/getgyurlbyall";//转链接API
    public static final String TBKGOODSTBCATEGOTY = "/rest/tbk/goods/tbcategoty";//tb分类
    public static final String TBKGOODSSELLERTBKLIST = "/rest/tbk/goods/sellerTbkList";//获取全网淘客商品API
    public static final String TBKGOODSGETTBKSHOP = "/rest/tbk/goods/gettbkshop";//全网搜索淘宝店铺列表






    public static final String WXAPPID = "wxf08fd2965ac9ac30";
    public static final String CODE_SUCCESS = "0";  //联网成功
    public static final String TOKEN_EXPIRE = "2";    //token过期
    public static final String ERROR = "ERROR";
    public static final String USER_BACK = "USER_BACK";
    public static final String JUMP_CLASSIFY = "JUMP_CLASSIFY";
    public static final String JUMP_CART = "JUMP_CART";
    public static final String TOKEN = "token";
    public static final String USERCODE = "userCode";
}
