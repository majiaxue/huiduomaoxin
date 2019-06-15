package com.example.commoditydetails.taobao.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public class TBLedSecuritiesBean {

    /**
     * error : 0
     * msg : 免授权转链成功（仅适用有券商品）
     * data : {"commission_rate":"30.01","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=MuZqyM6qMA8GQASttHIRqZ5D%2BF1mDDhxerZPTvWZOckYXB958FVfbPZJc8WjgU7f9AxYqVsfaZMaYrfYX9%2FN1mJXtP%2FursSvm4VLH9mslwyUQJ9RRUWmftd1yHO7fNodRidv9CsSAArAbUHw%2FIMRSt8%2FjWnuLO%2FPbTFatlyBSza1glM%2BZNWQP8HVq%2Fdxq%2FDATJnbK5InWznIDQGvV%2Bk%2B%2FUMuxoRQ3C%2BHES9NubPg2COie%2FpBy9wBFg%3D%3D&traceId=0b82d96815604973096611317e","coupon_end_time":"2019-06-15","coupon_info":"满39元减25元","coupon_remain_count":"46000","coupon_start_time":"2019-06-13","coupon_total_count":"100000","num_iid":"592742347679","tbk_pwd":"￥VokeY4LJViY￥","coupon_short_url":"https://s.click.taobao.com/nMMoo7w","category_id":"21","coupon_type":3,"small_images":[]}
     */

    private String error;
    private String msg;
    private DataBean data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commission_rate : 30.01
         * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=MuZqyM6qMA8GQASttHIRqZ5D%2BF1mDDhxerZPTvWZOckYXB958FVfbPZJc8WjgU7f9AxYqVsfaZMaYrfYX9%2FN1mJXtP%2FursSvm4VLH9mslwyUQJ9RRUWmftd1yHO7fNodRidv9CsSAArAbUHw%2FIMRSt8%2FjWnuLO%2FPbTFatlyBSza1glM%2BZNWQP8HVq%2Fdxq%2FDATJnbK5InWznIDQGvV%2Bk%2B%2FUMuxoRQ3C%2BHES9NubPg2COie%2FpBy9wBFg%3D%3D&traceId=0b82d96815604973096611317e
         * coupon_end_time : 2019-06-15
         * coupon_info : 满39元减25元
         * coupon_remain_count : 46000
         * coupon_start_time : 2019-06-13
         * coupon_total_count : 100000
         * num_iid : 592742347679
         * tbk_pwd : ￥VokeY4LJViY￥
         * coupon_short_url : https://s.click.taobao.com/nMMoo7w
         * category_id : 21
         * coupon_type : 3
         * small_images : []
         */

        private String commission_rate;
        private String coupon_click_url;
        private String coupon_end_time;
        private String coupon_info;
        private String coupon_remain_count;
        private String coupon_start_time;
        private String coupon_total_count;
        private String num_iid;
        private String tbk_pwd;
        private String coupon_short_url;
        private String category_id;
        private int coupon_type;
        private List<?> small_images;

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
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

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public int getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(int coupon_type) {
            this.coupon_type = coupon_type;
        }

        public List<?> getSmall_images() {
            return small_images;
        }

        public void setSmall_images(List<?> small_images) {
            this.small_images = small_images;
        }
    }
}
