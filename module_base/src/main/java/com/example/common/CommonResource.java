package com.example.common;

public class CommonResource {
    public static final String BASEURL_9001 = "http://47.99.93.123:9999";   //商品//47.99.93.123//192.168.0.118
    public static final String BASEURL_4001 = "http://47.99.93.123:4001";   //用户192.168.0.164
    public static final String BASEURL_9003 = "http://47.99.93.123:9003";   //商家192.168.0.121
    public static final String BASEURL_9004 = "http://47.99.93.123:9999";   //订单192.168.0.104
    public static final String BASEURL_9005 = "http://47.99.93.123:9999";   //参数192.168.0.194
    public static final String BASEURL_9010 = "http://47.99.93.123:9999";  //本地小店
    public static final String BASEURL_4000 = "http://47.99.93.123:4000";   //上传文件


//    public static final String BASEURL_9001 = "http://192.168.0.100:9001";   //商品//47.99.93.123//192.168.0.118
//    public static final String BASEURL_4001 = "http://192.168.0.100:4001";   //用户192.168.0.164
//    public static final String BASEURL_9003 = "http://192.168.0.100:9003";   //商家192.168.0.121
//    public static final String BASEURL_9004 = "http://192.168.0.100:9004";   //订单192.168.0.104
//    public static final String BASEURL_9005 = "http://192.168.0.100:9005";   //参数192.168.0.195
//    public static final String BASEURL_9010 = "http://192.168.0.100:9010";  //本地小店
//    public static final String BASEURL_4000 = "http://192.168.0.100:4000";   //上传文件


    public static final String ALLCATEGORT = "/mall/goods/rest/goods/allCategory"; //商品分类
    public static final String GETGOODSDETAIL = "/mall/goods/rest/goods";  //获取商品详情
    //多用户商城---模糊搜索框,参数:searchInfo->搜索内容,pageNum->当前页,pageSize->每页显示条数,默认20,startPrice->开始价格,endPrice->结束价格,categoryId->分类Id->productAttributeCategoryId->分类规格priceAsc->价格升序,priceDesc->价格降序,sellerId->商家IdsaleAsc->销量升序,saleDesc->销量降序,newStatus->1新品推荐
    public static final String HOTNEWSEARCH = "/mall/goods/rest/goods/search";
    public static final String USERSBANNER = "/mall/parameter/rest/parameter/homeAdvertise";   //多用户商城轮播图
    public static final String TYPENAVBAR = "/mall/goods/rest/goods/category";     //多用户商城---分类导航
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
    public static final String GET_YUNGEI = "/mall/goods/rest/goods/queryFeight";      //获取运费
    public static final String GETBALANCE = "/rest/user/balance";       //查询余额
    public static final String COMMIT_ORDER = "/mall/order/rest/order/nowPlace";      //提交订单
    public static final String IN_OUT = "/rest/user/incomeAndExpend";       //收入和支出(余额)---get:参数 type 0收入 1 支出
    public static final String TOPAY = "/mall/order/rest/alipay/signOrder";             //支付宝支付
    public static final String SUGGESTION = "/rest/user/feedback";     //提交意见反馈
    public static final String QUERYSUGGESTION = "/rest/user/feedback/all";    //查询意见反馈列表
    public static final String COLLECT = "/rest/user/product";      //商品收藏
    public static final String PAYSUCCESS = "/mall/order/rest/order/modifyOrder";      //支付成功 修改订单状态
    public static final String COLLECT_LIST = "/rest/user/product/all";     //收藏列表---淘宝客
    public static final String BROWSE_LIST = "/rest/user/history/all";     //浏览记录
    public static final String ORDER_DETAIL = "/mall/order/rest/order/viewOrderInfo";     //订单详情
    public static final String ADD_CART = "/mall/order/rest/order/addCar";         //添加商品到购物车
    public static final String REVISE_CART_ITEM = "/mall/order/rest/order/updateCarItem";  //修改购物车条目状态
    public static final String DELETE_CART = "/mall/order/rest/order/removeCartByIds";     //删除购物车条目
    public static final String CART_SUBMIT_ORDER = "/mall/order/rest/order/carSub";        //购物车提交订单
    public static final String PAY_CART = "/rest/goods/queryFeight";              //购物车结算
    public static final String QUERY_PDD_ORDER = "/rest/user/platform/order";        //查询拼多多订单----get  参数 int status  订单状态（0：已付款 1：已结算 2：已失效） 参数要是不传 默认是查询所有
    public static final String QUERY_FANS_ORDER = "/rest/user/order/fans/status";   //查询粉丝拼多多订单 get   currentPage 当前页  pageSize每页显示
    public static final String QUERY_BILI = "/mall/goods/rest/pdd/goods/backmoney";            //查询返佣比例
    public static final String FANS_TOTAL_MONEY = "/rest/user/fans/totalMoney";     //查询粉丝订单成交金额
    public static final String COUPON_KELING = "/rest/seller/goods-coupons";        //可领优惠券
    public static final String GETASSESS = "/rest/comment";                    //获取商品评论
    public static final String GETUSERASSESS = "/mall/order/rest/order/page";              //获取多用户商城评论列表
    public static final String GETPREDICT = "/rest/user/settlement";            //预估收益
    public static final String HOME_PREDICT = "/rest/user/settlement/total";    //首页获取预估数据
    public static final String GETOPER = "/rest/user/getOperLevel";             //获取运营商等级列表
    public static final String SEARCH_NEW_TB = "/mall/goods/rest/tbk/goods/sellerTbkList_1";   //搜索淘宝商品（新）  关键词 淘口令  链接
    public static final String SEARCHPDDGOODS = "/mall/goods/rest/pdd/goods/pddgoods";      //搜索拼多多商品
    public static final String SEARCHJDGOODS = "/mall/goods/rest/jd/goodsListByJD";            //搜索京东商品
    public static final String TIXIAN = "/rest/user/cashOut/aliPay";          //提现
    public static final String MESSAGELIST = "/rest/user/message";             //消息列表
    public static final String COLLECT_SHOP = "/rest/user/seller";              //收藏店铺
    public static final String IWANTUP = "/rest/user/level/info";               //我要升级
    public static final String UP_PAY = "/rest/alipay/upLevelSign";             // 升级运营商/等级 支付
    public static final String SHOUQUAN = "/mall/goods/rest/tbk/goods/weiYi";           //转链授权
    public static final String GET_SHARE_URL = "/rest/share/invite";        //获取分享链接
    public static final String UP_JUSTNOW = "/rest/user/level/up";          //立即升级
    public static final String USER_AGREEMENT = "/mall/parameter/rest/html/xy";            //获取用户协议
    public static final String CHECKUP = "/mall/parameter/rest/parameter/apk/0";            //检查更新app
    public static final String GETPOINTS = "/rest/user/my/integration";     //获取我的积分
    public static final String POINTS_CRASH = "/rest/user/cashOut/aliPay/integration";      //积分提现
    public static final String WXPAY = "/mall/order/rest/WXPay/pay";                   //微信支付
    public static final String UP_WXPAY = "/rest/WXPay/recharge";           //升级运营商微信支付
    public static final String LOCAL_BANNER = "/mall/parameter/rest/parameter/homeAdvertiseXx";    //本地商城轮播图
    public static final String LOCAL_NAVBAR = "/rest/seller/sellerCategory";       //本地商城导航 /rest/seller/sellerCategory？type=值   1:线下   0：线上
    public static final String COMMUNITY = "/mall/goods/rest/community/goods/mallGoods";      //社区
    public static final String POINTS_CASHOUT_RECORD = "/rest/sign/history";        //积分记录、积分提现记录  type  0：签到记录 1：积分提现记录   4001
    public static final String LOCAL_SHOP_GOODS = "/mall/goods/rest/goods/localProduct";       //商家里的商品列表
    public static final String OPERATOR_GOODS = "/mall/goods/rest/goods/level";        //升级运营商商品
    public static final String GET_QUANYI = "/rest/user/level";             //根据levelId 获取当前等级的权益
    public static final String LOCALSHOPLIST = "/rest/seller/seller";       //本地商城列表
    public static final String LINGCOUPON = "/rest/seller/coupon";          //领取优惠券
    public static final String LOCAL_CREATEORDER = "/mall/order/rest/localOrder/payLocalProduct";    //本地商城创建订单
    public static final String LIBAO_WXPAY = "/mall/order/rest/WXPay/level";          //礼包升级微信支付
    public static final String LIBAO_ZFBPAY = "/mall/order/rest/alipay/upLevelSign";   //礼包升级支付宝支付
    public static final String QUERY_COUPON = "/rest/seller/coupon/status";     //查询已领取优惠券  未使用 status 0
    public static final String LOCAL_BALANCE_PAY = "/mall/order/rest/localPay/localOrderPay";   //本地商城余额支付
    public static final String LIBAO_CANCEL_ORDER = "/mall/order/rest/order/remove/level";     //取消礼包订单
    public static final String TKOULING = "/rest/tbk/goods/jiexitkl";           //淘口令转商品信息
    public static final String INVITE_ERWEIMA = "/rest/share/register";         //邀请好友页面升成二维码所需地址
    public static final String LOCAL_GET_ORDER = "/mall/local/rest/local-order";           //附近小店  获取订单列表
    public static final String LOCAL_GET_HONGBAO = "/mall/local/rest/local/redpacked/all";     //附近小店  获取红包信息
    public static final String BUY_RED_PACKAGE = "/mall/local/rest/local/alipay/redpacked";    //附近小店  购买红包
    public static final String LOCAL_SHOP = "/mall/local/rest/local/shop-category/";       //附近小店 店铺详情
    public static final String LOCAL_CART_ADD = "/mall/local/rest/local/shop-car/add";     //附近小店 购物车 增加商品
    public static final String LOCAL_CART_MINUS = "/mall/local/rest/local/shop-car/minus"; //附近小店 购物车 减少商品
    public static final String LOCAL_SUBMIT_ORDER = "/mall/local/rest/local-order";        //附近小店 提交订单
    public static final String LOCAL_GET_CART = "/mall/local/rest/local/shopcar/";         //附近小店  查询购物车
    public static final String LOCAL_ALI_PAY = "/mall/local/rest/local/alipay/redpacked";  //附近小店  支付宝支付
    public static final String LOCAL_WX_PAY = "/mall/local/rest/local/wxpay/redpacked";    //附近小店  微信支付
    public static final String LOCAL_CANCEL_ORDER = "/mall/local/rest/local-order/cancel";   //附近小店  取消订单
    public static final String LOCAL_CONFIRM_ORDER = "/mall/local/rest/local-order/ok/";       //附近小店  确认收货
    public static final String LOCAL_TUIKUAN = "/mall/local/rest/local-order/return";          //附近小店  申请退款
    public static final String USER_ISCOLLECT = "/rest/user/favorite/seller";       //多用户商城 店铺是否收藏
    public static final String LOCAL_SELLERS = "/rest/seller/localSeller";          //本地商家
    public static final String HOT_SELLERS = "/rest/seller/hotSeller";              //热门商家
    public static final String CANCEL_ORDER = "/mall/order/rest/order/cancel";                 //多用户商城   取消订单
    public static final String CANCEL_TUIKUAN = "/mall/order/rest/order/cancelReturnOrder";    //多用户商城   取消申请退款
    public static final String LOCAL_COUPON_LIST = "/mall/local/rest/local/redpacked/member/all";  //多用户商城获取红包列表
    public static final String PRODUCT_LIUYAN = "/mall/goods/rest/product/center/message";         //产品中心  用户留言
    public static final String PRODUCT_GETPHONE = "/mall/goods/rest/product/center/talk";          //产品中心  咨询热线电话


    public static final String GOODSCOLLECTION = "/rest/user/product/all";   //查看商品收藏
    public static final String SELLERPAGE = "/rest/user/seller/all";   //商家收藏
    public static final String HISTORYALL = "/rest/user/history/all";   //浏览历史
    public static final String PDDGOODS = "/mall/goods/rest/pdd/goods/pddgoods";   //pdd获取商品列表
    public static final String GOODSCATS = "/mall/goods/rest/pdd/goods/goodscats/0";   //pdd获取商品标准类目接口
    public static final String PDDGOODSDETAIL = "/mall/goods/rest/pdd/goods/detail";   //pdd获取商品详情信息
    public static final String TOPGOODS = "/mall/goods/mall/goods/rest/pdd/goods/topgoods";   //pdd获取获取爆款商品
    public static final String GOODSCOUPON = "/mall/goods/rest/pdd/goods/coupon";   //pdd跳转至拼多多->获取优惠券->下单
    public static final String FAVORITEDELETE = "/rest/user/favorite/delete";   //删除收藏商品,
    public static final String SHOPDELETE = "/rest/user/favorite/delete";   //取消关注商家
    public static final String HISTORYDELETE = "/rest/user/history/delete";   //浏览历史记录删除
    public static final String CARTLIST = "/mall/order/rest/order/list";   //购物车
    public static final String ADDRESSSHOW = "/rest/address/show";   //查询收货地址
    public static final String ADDRESSDEFAULT = "/rest/address/default";   //设置默认收货地址
    public static final String DELETEADDRESS = "/rest/address";   //删除收货地址
    public static final String AMENDADDRESS = "/rest/address";   //修改收货人地址
    public static final String ORDERALL = "/rest/user/order/all";   //查询登录用户所有订单
    public static final String ORDERSTATUS = "/rest/user/order/status";   //根据状态查询登录用户订单
    public static final String FAVORITESTATUS = "/rest/user/favorite";   //查询收藏状态
    public static final String RETURNINFO = "/mall/order/rest/order/returnInfo";   //退换售后详情
    public static final String RETURNTABLE = "/mall/order/rest/order/returnTable";   //退换售后列表
    public static final String ADDRESSADD = "/rest/address/add";   //新增收货地址
    public static final String ADDRESSSELECT = "/rest/address/city";   //三级地址选择
    public static final String COUPONSTATUS = "/rest/user/coupon/status";//根据状态查询优惠券
    public static final String SELLERINFO = "/rest/seller/info";//商家入驻
    public static final String TBKGOODSPRODUCTS = "/mall/goods/rest/tbk/goods/products";//淘宝客推荐
    public static final String TBKGOODSITEMDETAIL = "/rest/tbk/goods/itemdetail";//商品详情接口功能（店铺信息/产品介绍/主图视频/SKU）
    public static final String TBKGOODSGETITEMDESC = "/mall/goods/rest/tbk/goods/getItemDesc";//商品详情接口功能（店铺信息/产品介绍/主图视频/SKU）
    public static final String TBKGOODSGETGYURLBYALL = "/mall/goods/rest/tbk/goods/getgyurlbyall";//转链接API
    public static final String TBKGOODSTBCATEGOTY = "/mall/goods/rest/tbk/goods/tbcategoty";//tb分类
    public static final String SUPERGRAND = "/mall/goods/rest/tbk/goods/superGrand";//超级品牌
    public static final String JDGETCATEGORY = "/mall/goods/rest/jd/getCategory";//获取京东商品类目
    public static final String JDGETGOODSDETAIL = "/rest/jd/getGoodsDetail";//获取京东商品详情
    public static final String JDGETGOODSMARKETLINK = "/mall/goods/rest/jd/getGoodsMarketLink";//获取京东商品推广链接
    public static final String JDGOODSLIST = "/mall/goods/rest/jd/goodsListByJD";//获取京东带优惠券的商品API
    public static final String SELLERSTATE = "/rest/seller/state";//判断商家是否已经入驻
    public static final String ESTIMATEEARN = "/rest/estimateEarn";//第三方商品预估赚接口调用---预估赚
    public static final String ORDERREMOVE = "/rest/user/order/remove";//逻辑删除多用户商城用户订单
    public static final String REFUNDAPPLY = "/mall/order/rest/order/refundApply";     //退款申请
    public static final String ORDERCONFIRM = "/mall/order/rest/order/confirm";        //确定收货
    public static final String USERCOMMENT = "/mall/order/rest/order/addComment";      //立即评价
    public static final String GETORDERTRACESBYJSON = "/mall/order/rest/order/getOrderTracesByJson";//物流信息
    public static final String HISTORYSAVE = "/rest/user/history/save";//保存浏览记录
    public static final String SELLERCATEGORY = "/rest/seller/sellerCategory";//商品本地类型
    public static final String SELLERNETCATEGORY = "/mall/goods/rest/tbk/goods/sellerNetCategory";//商品线上类型
    public static final String TBKGOODSGETTRILLDATA = "/mall/goods/rest/tbk/goods/gettrilldata";//抖券直播
    public static final String SIGNFANS = "/rest/sign/fans";//粉丝数量"
    public static final String SIGNFIRSTORDER = "/rest/sign/first/order";//首次下单
    public static final String SIGNGOODS = "/rest/sign/goods";//每日浏览商品签到
    public static final String SIGNINVITE = "/rest/sign/invite";//邀请好友
    public static final String SIGNORDER = "/rest/sign/order";//有效订单
    public static final String SIGNQUERY = "/rest/sign/query";//查询签到任务完成度
    public static final String SIGNSHARE = "/rest/sign/share";//每日分享商品
    public static final String SIGNTODAY = "/rest/sign/today";//每日签到
    public static final String TAOLIJIN = "/mall/goods/rest/taolijin/taolijin";//免单活动
    public static final String HOMEADVERTISEBOTTOM = "/mall/parameter/rest/parameter/homeAdvertise-bottom";//首页下方轮播图
    public static final String TBKGOODSSALESLIST = "/mall/goods/rest/tbk/goods/sales_list";//优选
    public static final String ADDRESSAREA = "/rest/address/area";//根据市搜索区
    public static final String HOMEADVERTISETK = "/mall/parameter/rest/parameter/homeAdvertiseTK";   //淘客商城轮播图
    public static final String GOODSDETAILS = "/mall/goods/rest/tbk/goods/goods_details";   //最新淘客详情接口
    public static final String GETGOODSLIST = "/mall/goods/rest/tbk/goods/get_goods_list";   //淘抢购聚划算接口
    public static final String BRANDLIST = "/mall/goods/rest/tbk/goods/brandList";   //品牌接口
    public static final String UPLOADORDER = "/file/upload/order";      //上传头像
    public static final String QUERYXSQGSPLIST = "/rest/tbk/goods/time/buy";      //限时抢购
    public static final String DELIVERGOODSREMIND = "/mall/order/rest/order/deliverGoodsRemind";      //订单发货提醒
    public static final String GETSELLERBYID = "/rest/seller/getSellerById";      //商家店铺详情
    public static final String GETNETSELLERCATEGORY = "/mall/goods/rest/tbk/goods/getNetSellerCategory";      //商品进店获取商家分类列表接口
    public static final String PRODUCTCENTERCATEGORY = "/mall/goods/rest/product/center/category";//查询产品分类
    public static final String PRODUCTCENTER = "/mall/goods/rest/product/center/";//查询当前分类下所有产品


    public static final String WXAPPID = "wxf08fd2965ac9ac30";  //本：wxf08fd2965ac9ac30  2：wx7df9caffc7db4493
    public static final String CODE_SUCCESS = "0";  //联网成功
    public static final String TOKEN_EXPIRE = "2";    //token过期
    public static final String ERROR = "ERROR";
    public static final String USER_BACK = "USER_BACK";
    public static final String JUMP_CLASSIFY = "JUMP_CLASSIFY";
    public static final String JUMP_CART = "JUMP_CART";
    public static final String JUMP_OPERATOR = "JUMP_OPERATOR";
    public static final String TOKEN = "token";
    public static final String USERCODE = "userCode";
    public static final String USER_NAME = "name";
    public static final String USER_PIC = "head";
    public static final String USER_INVITE = "userInvite";
    public static final String USER_PHONE = "userPhone";
    public static final String BACKBL = "back";     //佣金比例
    public static final String LEVELID = "levelId";
    public static final String LEVEL = "level";
    public static final String U_APPKEY = "5d0c57294ca35786440001c6";
    public static final String HISTORY_USER = "user";
    public static final String HISTORY_TBK = "tbk";
    public static final String HISTORY_LOCAL = "local";
    public static final String WXPAY_CANCEL = "wxpay_cancel";   //微信支付  取消支付
    public static final String WXPAY_SUCCESS = "wxpay_success"; //礼包微信支付成功
    public static final String WXPAY_SUCCESS_UP = "wxpay_up";   //金银铜微信支付成功
    public static final String WXPAY_SUCCESS_LOCAL = "wxpay_local"; //本地商城微信支付成功
    public static final String WXPAY_SUCCESS_LOCAL_ORDER = "wxpay_local_order"; //本地商城订单支付  微信
    public static final String CART_REFRESH = "cartRefresh";        //从购物车的商品推荐进入商品详情，又跳到购物车时刷新购物车
    public static final String CITY = "city";      //网络发生变化，重新定位
    public static final String TAN_CONTENT = "tan_content";     //粘贴板内容

    public static final String ISTAN = "isTan";     //粘贴板内容是否弹过popupwindow
    public static final String SELLERID = "sellerId";   //店铺ID
    public static final String SELLERNAME = "sellerName";   //店铺名字
    public static final String SUBMIT_ORDER = "submitOrder";    //提交订单
    public static final String DINGWEI = "dingwei";
    public static final String UPCART = "up_cart";      //刷新购物车
    public static final String MINUS_GOODS = "minus_goods";     //本地商城减少不同规格商品
    public static final String LOCAL_SELLER_MANJIAN = "local_seller_manjian";


    public static final String PROJECTNAME = "枫林淘客";
    public static final String SERVICE_PHONE = "18503735075";

}
