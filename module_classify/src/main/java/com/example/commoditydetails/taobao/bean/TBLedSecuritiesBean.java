package com.example.commoditydetails.taobao.bean;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public class TBLedSecuritiesBean {

    /**
     * result : 1
     * data : {"category_id":"50011699","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=mOPGPex50NcGQASttHIRqYYzbcwEPG6Joyp1YhheLQnCVuV6PTdZTaIDAQqCw2YCY2jaHdQ%2B97p2J38b%2BUDP3CZlFJKZKYIuIJ1GtccYOa7CKbsAbM2TPPsnwWZGSCD41ug731VBEQm0m3Ckm6GN2CwynAdGnOngkM20EQQvoa4HDL30JIKBNCmksDiqwPnW&traceId=0b83523115609350306525284e&union_lens=lensId:0b153bbd_0bf6_16b6efa5fa4_ea4f&xId=7HuJ4Il4Zg2FIqTAj5wcnv0h9VITzujaQVeCLRNPOCf16JoVRLAaZ6ybgbcHcHwpjJtMAq1iVwBlSrhlKrLyfv","coupon_end_time":"2019-06-23","coupon_info":"满25元减5元","coupon_remain_count":"74403","coupon_start_time":"2019-05-02","coupon_total_count":"100000","coupon_type":"1","commission_rate":"3.00","num_iid":"548439393628","tbk_pwd":"￥iPckYU1rGLh￥","coupon_short_url":"https://s.click.taobao.com/dPzsP7w"}
     */

    private int result;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_id : 50011699
         * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=mOPGPex50NcGQASttHIRqYYzbcwEPG6Joyp1YhheLQnCVuV6PTdZTaIDAQqCw2YCY2jaHdQ%2B97p2J38b%2BUDP3CZlFJKZKYIuIJ1GtccYOa7CKbsAbM2TPPsnwWZGSCD41ug731VBEQm0m3Ckm6GN2CwynAdGnOngkM20EQQvoa4HDL30JIKBNCmksDiqwPnW&traceId=0b83523115609350306525284e&union_lens=lensId:0b153bbd_0bf6_16b6efa5fa4_ea4f&xId=7HuJ4Il4Zg2FIqTAj5wcnv0h9VITzujaQVeCLRNPOCf16JoVRLAaZ6ybgbcHcHwpjJtMAq1iVwBlSrhlKrLyfv
         * coupon_end_time : 2019-06-23
         * coupon_info : 满25元减5元
         * coupon_remain_count : 74403
         * coupon_start_time : 2019-05-02
         * coupon_total_count : 100000
         * coupon_type : 1
         * commission_rate : 3.00
         * num_iid : 548439393628
         * tbk_pwd : ￥iPckYU1rGLh￥
         * coupon_short_url : https://s.click.taobao.com/dPzsP7w
         */

        private String category_id;
        private String coupon_click_url;
        private String coupon_end_time;
        private String coupon_info;
        private String coupon_remain_count;
        private String coupon_start_time;
        private String coupon_total_count;
        private String coupon_type;
        private String commission_rate;
        private String num_iid;
        private String tbk_pwd;
        private String coupon_short_url;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCoupon_click_url() {
            return coupon_click_url;
        }

        public void setCoupon_click_url(String coupon_click_url) {
            this.coupon_click_url = coupon_click_url;
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

        public String getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(String coupon_type) {
            this.coupon_type = coupon_type;
        }

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getTbk_pwd() {
            return tbk_pwd;
        }

        public void setTbk_pwd(String tbk_pwd) {
            this.tbk_pwd = tbk_pwd;
        }

        public String getCoupon_short_url() {
            return coupon_short_url;
        }

        public void setCoupon_short_url(String coupon_short_url) {
            this.coupon_short_url = coupon_short_url;
        }
    }
}
