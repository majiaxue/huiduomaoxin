package com.example.bean;

import java.util.List;

public class GoodChoiceBean {

    /**
     * code : 200
     * data : [{"category":"50023745","commission_rate":"4.50","coupon_end_time":"2019-07-02 23:59:59","coupon_info":"满219.00元减50.00元","coupon_remain_count":"2175","coupon_start_time":"2019-07-02 00:00:00","coupon_total_count":"2500","item_description":"双人切换 一台抵两台 语音播报 背光大屏","item_url":"https://detail.tmall.com/item.htm?id=563392434345","nick":"鱼跃官方旗舰店","num_iid":"563392434345","pict_url":"http://img.alicdn.com/tfscom/i2/2107759029/O1CN01afiMB02GZKcRulT1f_!!0-item_pic.jpg","seller_id":"2107759029","shop_title":"鱼跃官方旗舰店","small_images":{"string":["http://img.alicdn.com/tfscom/i1/2107759029/O1CN01eM4aKo2GZKc1EIBiQ_!!2107759029.jpg","http://img.alicdn.com/tfscom/i2/2107759029/TB2qgsfaTlYBeNjSszcXXbwhFXa_!!2107759029.jpg","http://img.alicdn.com/tfscom/i2/2107759029/TB2I7SJaHSYBuNjSspfXXcZCpXa_!!2107759029.jpg","http://img.alicdn.com/tfscom/i4/2107759029/O1CN01kxeqGS2GZKYHDlyyT_!!2107759029.jpg"]},"title":"鱼跃电子血压计臂式高精准血压测量仪家用全自动高血压测压仪","user_type":"1","volume":"32981","zk_final_price":"219.00","youhuiquan":50,"quanlimit":219},{"category":"50012587","commission_rate":"9.00","coupon_end_time":"2019-07-08 23:59:59","coupon_info":"满9.00元减5.00元","coupon_remain_count":"99000","coupon_start_time":"2019-07-02 00:00:00","coupon_total_count":"100000","item_description":"真钻石膜 三倍防爆 不碎边 无白边","item_url":"https://detail.tmall.com/item.htm?id=596198137886","nick":"gusgu古尚古专卖店","num_iid":"596198137886","pict_url":"http://img.alicdn.com/tfscom/i4/2975892770/O1CN01p13A2i1WKhduCfwPe_!!0-item_pic.jpg","seller_id":"2975892770","shop_title":"gusgu古尚古专卖店","small_images":{"string":["http://img.alicdn.com/tfscom/i4/2975892770/O1CN01Qp1hsD1WKhdaOyLnn_!!2975892770.jpg","http://img.alicdn.com/tfscom/i4/2975892770/O1CN01gQ7gQa1WKhdbcwjAj_!!2975892770.jpg","http://img.alicdn.com/tfscom/i1/2975892770/O1CN01A7Kbhy1WKhdbJSyWO_!!2975892770.jpg","http://img.alicdn.com/tfscom/i2/2975892770/O1CN01G0tIv71WKhdbcSrDW_!!2975892770.jpg"]},"title":"苹果x钢化膜iPhoneX全屏覆盖6/6s/7/8/6P/7P/8P防偷窥iPhone xs蓝光iPhonexr保护贴膜xsmax防指纹7plus手机xr","user_type":"1","volume":"423958","zk_final_price":"9.80","youhuiquan":5,"quanlimit":9}]
     * msg : 品牌商品获取成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category : 50023745
         * commission_rate : 4.50
         * coupon_end_time : 2019-07-02 23:59:59
         * coupon_info : 满219.00元减50.00元
         * coupon_remain_count : 2175
         * coupon_start_time : 2019-07-02 00:00:00
         * coupon_total_count : 2500
         * item_description : 双人切换 一台抵两台 语音播报 背光大屏
         * item_url : https://detail.tmall.com/item.htm?id=563392434345
         * nick : 鱼跃官方旗舰店
         * num_iid : 563392434345
         * pict_url : http://img.alicdn.com/tfscom/i2/2107759029/O1CN01afiMB02GZKcRulT1f_!!0-item_pic.jpg
         * seller_id : 2107759029
         * shop_title : 鱼跃官方旗舰店
         * small_images : {"string":["http://img.alicdn.com/tfscom/i1/2107759029/O1CN01eM4aKo2GZKc1EIBiQ_!!2107759029.jpg","http://img.alicdn.com/tfscom/i2/2107759029/TB2qgsfaTlYBeNjSszcXXbwhFXa_!!2107759029.jpg","http://img.alicdn.com/tfscom/i2/2107759029/TB2I7SJaHSYBuNjSspfXXcZCpXa_!!2107759029.jpg","http://img.alicdn.com/tfscom/i4/2107759029/O1CN01kxeqGS2GZKYHDlyyT_!!2107759029.jpg"]}
         * title : 鱼跃电子血压计臂式高精准血压测量仪家用全自动高血压测压仪
         * user_type : 1
         * volume : 32981
         * zk_final_price : 219.00
         * youhuiquan : 50
         * quanlimit : 219
         */

        private String category;
        private String commission_rate;
        private String coupon_end_time;
        private String coupon_info;
        private String coupon_remain_count;
        private String coupon_start_time;
        private String coupon_total_count;
        private String item_description;
        private String item_url;
        private String nick;
        private String num_iid;
        private String pict_url;
        private String seller_id;
        private String shop_title;
        private SmallImagesBean small_images;
        private String title;
        private String user_type;
        private String volume;
        private String zk_final_price;
        private int youhuiquan;
        private int quanlimit;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
        }

        public String getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(String coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public String getCoupon_info() {
            return coupon_info;
        }

        public void setCoupon_info(String coupon_info) {
            this.coupon_info = coupon_info;
        }

        public String getCoupon_remain_count() {
            return coupon_remain_count;
        }

        public void setCoupon_remain_count(String coupon_remain_count) {
            this.coupon_remain_count = coupon_remain_count;
        }

        public String getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(String coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public String getCoupon_total_count() {
            return coupon_total_count;
        }

        public void setCoupon_total_count(String coupon_total_count) {
            this.coupon_total_count = coupon_total_count;
        }

        public String getItem_description() {
            return item_description;
        }

        public void setItem_description(String item_description) {
            this.item_description = item_description;
        }

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public SmallImagesBean getSmall_images() {
            return small_images;
        }

        public void setSmall_images(SmallImagesBean small_images) {
            this.small_images = small_images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getZk_final_price() {
            return zk_final_price;
        }

        public void setZk_final_price(String zk_final_price) {
            this.zk_final_price = zk_final_price;
        }

        public int getYouhuiquan() {
            return youhuiquan;
        }

        public void setYouhuiquan(int youhuiquan) {
            this.youhuiquan = youhuiquan;
        }

        public int getQuanlimit() {
            return quanlimit;
        }

        public void setQuanlimit(int quanlimit) {
            this.quanlimit = quanlimit;
        }

        public static class SmallImagesBean {
            private List<String> string;

            public List<String> getString() {
                return string;
            }

            public void setString(List<String> string) {
                this.string = string;
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "category='" + category + '\'' +
                    ", commission_rate='" + commission_rate + '\'' +
                    ", coupon_end_time='" + coupon_end_time + '\'' +
                    ", coupon_info='" + coupon_info + '\'' +
                    ", coupon_remain_count='" + coupon_remain_count + '\'' +
                    ", coupon_start_time='" + coupon_start_time + '\'' +
                    ", coupon_total_count='" + coupon_total_count + '\'' +
                    ", item_description='" + item_description + '\'' +
                    ", item_url='" + item_url + '\'' +
                    ", nick='" + nick + '\'' +
                    ", num_iid='" + num_iid + '\'' +
                    ", pict_url='" + pict_url + '\'' +
                    ", seller_id='" + seller_id + '\'' +
                    ", shop_title='" + shop_title + '\'' +
                    ", small_images=" + small_images +
                    ", title='" + title + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", volume='" + volume + '\'' +
                    ", zk_final_price='" + zk_final_price + '\'' +
                    ", youhuiquan=" + youhuiquan +
                    ", quanlimit=" + quanlimit +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodChoiceBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
