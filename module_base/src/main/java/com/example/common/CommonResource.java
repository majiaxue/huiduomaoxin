package com.example.common;

public class CommonResource {
    public static final String BASEURL_9001 = "http://47.99.93.123:9001";   //商品//47.99.93.123
    public static final String BASEURL_4001 = "http://47.99.93.123:4001";   //用户
    public static final String BASEURL_5003 = "http://47.99.93.123:9003";   //商家
    public static final String BASEURL_9004 = "http://47.99.93.123:9004";   //订单
    public static final String BASEURL_9005 = "http://47.99.93.123:9005";   //参数

    public static final String URL_30_9001 = "http://192.168.1.30:9001";
    public static final String URL_4_9001 = "http://192.168.1.4:9001";
    public static final String URL_4_4001 = "http://192.168.1.4:4001";
    public static final String URL_9_4001 = "http://47.99.93.123:4001";
    public static final String URL_4_5003 = "http://192.168.1.4:5003";
    public static final String URL_30_9004 = "http://192.168.1.30:9004";
    public static final String URL_4_9005 = "http://192.168.1.4:9005";

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
    public static final String TOPAY = "/rest/alipay/signOrder";             //支付宝支付
    public static final String SUGGESTION = "/rest/user/feedback";     //提交意见反馈
    public static final String QUERYSUGGESTION = "/rest/user/feedback/all";    //查询意见反馈列表
    public static final String COLLECT = "/rest/user/product";      //商品收藏
    public static final String PAYSUCCESS = "/rest/order/modifyOrder";      //支付成功 修改订单状态
    public static final String COLLECT_LIST = "/rest/user/product/all";     //收藏列表---淘宝客
    public static final String BROWSE_LIST = "/rest/user/history/all ";     //浏览记录
    public static final String ORDER_DETAIL = "/rest/order/viewOrderInfo";     //订单详情
    public static final String ADD_CART = "/rest/order/addCar";         //添加商品到购物车
    public static final String REVISE_CART_ITEM = "/rest/order/updateCarItem";  //修改购物车条目状态
    public static final String DELETE_CART = "/rest/order/removeCartByIds";     //删除购物车条目
    public static final String CART_SUBMIT_ORDER = "/rest/order/carSub";        //购物车提交订单
    public static final String PAY_CART = "/rest/goods/queryFeight";              //购物车结算
    public static final String QUERY_PDD_ORDER = "/rest/user/platform/order";        //查询拼多多订单----get  参数 int status  订单状态（0：已付款 1：已结算 2：已失效） 参数要是不传 默认是查询所有
    public static final String QUERY_FANS_ORDER = "/rest/user/order/fans/status";   //查询粉丝拼多多订单 get   currentPage 当前页  pageSize每页显示
    public static final String QUERY_BILI = "/rest/pdd/goods/backmoney";            //查询返佣比例
    public static final String FANS_TOTAL_MONEY = "/rest/user/fans/totalMoney";     //查询粉丝订单成交金额
    public static final String COUPON_KELING = "/rest/seller/goods-coupons";        //可领优惠券
    public static final String GETASSESS = "/rest/comment";                    //获取商品评论
    public static final String GETPREDICT = "/rest/user/settlement";            //预估收益
    public static final String HOME_PREDICT = "/rest/user/settlement/total";    //首页获取预估数据
    public static final String GETOPER = "/rest/user/getOperLevel";             //获取运营商等级列表
    public static final String SEARCHTBGOODS = "/rest/tbk/goods/sellerTbkList"; //搜索淘宝商品
    public static final String SEARCHPDDGOODS = "/rest/pdd/goods/pddgoods";      //搜索拼多多商品
    public static final String SEARCHJDGOODS = "/rest/jd/goodsList";            //搜索京东商品
    public static final String TIXIAN = "/rest/user/cashOut/aliPay  ";          //提现
    public static final String MESSAGELIST = "/rest/user/message";             //消息列表


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
    public static final String SELLERINFO = "/rest/seller/info";//商家入驻
    public static final String TBKGOODSPRODUCTS = "/rest/tbk/goods/products";//淘宝客推荐
    public static final String TBKGOODSITEMDETAIL = "/rest/tbk/goods/itemdetail";//商品详情接口功能（店铺信息/产品介绍/主图视频/SKU）
    public static final String TBKGOODSGETGYURLBYALL = "/rest/tbk/goods/getgyurlbyall";//转链接API
    public static final String TBKGOODSTBCATEGOTY = "/rest/tbk/goods/tbcategoty";//tb分类
    public static final String TBKGOODSSELLERTBKLIST = "/rest/tbk/goods/sellerTbkList";//获取全网淘客商品API
    public static final String TBKGOODSGETTBKSHOP = "/rest/tbk/goods/gettbkshop";//全网搜索淘宝店铺列表
    public static final String JDGETCATEGORY = "/rest/jd/getCategory";//获取京东商品类目
    public static final String JDGETGOODSDETAIL = "/rest/jd/getGoodsDetail";//获取京东商品详情
    public static final String JDGETGOODSMARKETLINK = "/rest/jd/getGoodsMarketLink";//获取京东商品推广链接
    public static final String JDGOODSLIST = "/rest/jd/goodsList";//获取京东带优惠券的商品API
    public static final String SELLERSTATE = "/rest/seller/seller-state";//判断商家是否已经入驻
    public static final String ESTIMATEEARN = "/rest/estimateEarn";//第三方商品预估赚接口调用---预估赚
    public static final String ORDERREMOVE = "/rest/user/order/remove";//逻辑删除多用户商城用户订单
    public static final String REFUNDAPPLY = "/rest/order/refundApply";//退款申请
    public static final String ORDERCONFIRM = "/rest/order/confirm";//确定收货
    public static final String USERCOMMENT = "/rest/user/comment";//立即评价
    public static final String GETORDERTRACESBYJSON = "/rest/order/getOrderTracesByJson";//物流信息


    public static final String WXAPPID = "wxf08fd2965ac9ac30";
    public static final String CODE_SUCCESS = "0";  //联网成功
    public static final String TOKEN_EXPIRE = "2";    //token过期
    public static final String ERROR = "ERROR";
    public static final String USER_BACK = "USER_BACK";
    public static final String JUMP_CLASSIFY = "JUMP_CLASSIFY";
    public static final String JUMP_CART = "JUMP_CART";
    public static final String TOKEN = "token";
    public static final String USERCODE = "userCode";
    public static final String TABABLE_NAME = "HISTORY";
}
