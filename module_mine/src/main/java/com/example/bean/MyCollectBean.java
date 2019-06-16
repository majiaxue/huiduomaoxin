package com.example.bean;

import java.util.List;

public class MyCollectBean {

    /**
     * goods_search_response : {"goods_list":[{"has_mall_coupon":false,"mall_coupon_id":0,"mall_coupon_discount_pct":0,"mall_coupon_min_order_amount":0,"mall_coupon_max_discount_amount":0,"mall_coupon_total_quantity":0,"mall_coupon_remain_quantity":0,"mall_coupon_start_time":0,"mall_coupon_end_time":0,"goods_id":2338511,"goods_name":"【9.78元抢100000件，抢完恢复13.9元】【4-10斤】语衣甘蓝薰衣草香氛洗衣液 持久留香 低泡易漂家庭装","goods_desc":"【语衣甘蓝官方旗舰店-厂家直营正品保障】【温馨提示】优先点击\u201c去拼单\u201d成团立刻发货!分享到朋友圈、朋友群,成团更快更轻松!【语衣甘蓝,一心一意做洗涤】","goods_thumbnail_url":"https://t00img.yangkeduo.com/goods/images/2018-10-11/4f76d68b1123c812e8e8dce4fb45be69.jpeg","goods_image_url":"https://t00img.yangkeduo.com/t04img/images/2018-07-11/08b4c0f38e765963650ec42817d68f42.jpeg","goods_gallery_urls":["https://t00img.yangkeduo.com/goods/images/2018-10-11/340a8df38625128267232f6828d2a0b6.jpeg","https://t00img.yangkeduo.com/t09img/images/2018-07-11/ef9145c73254baaf7cd1a5179f8184cd.jpeg","https://t00img.yangkeduo.com/goods/images/2018-09-14/09f1cd686fdec767f370809c5ceb1b56.jpeg","https://t00img.yangkeduo.com/goods/images/2018-09-14/e29807bbea24d19da0cb4deeee6652ac.jpeg","https://t00img.yangkeduo.com/goods/images/2019-03-22/62fadbb7-2fd3-4190-a9e9-ab83f9dbfdaa.jpg"],"sold_quantity":4130775,"min_group_price":978,"min_normal_price":1600,"mall_name":"语衣甘蓝旗舰店","merchant_type":3,"category_id":8590,"category_name":"百货","opt_id":8590,"opt_name":"百货","opt_ids":[8592,328,408,8569,8570,8571,8590,8591,15],"cat_ids":[17285,17296,17393],"mall_cps":1,"has_coupon":true,"coupon_min_order_amount":100,"coupon_discount":100,"coupon_total_quantity":50000,"coupon_remain_quantity":21000,"coupon_start_time":1560182400,"coupon_end_time":1561910399,"promotion_rate":200,"goods_eval_score":4.57,"goods_eval_count":148199,"avg_desc":467,"avg_lgst":469,"avg_serv":470,"desc_pct":0.5869,"lgst_pct":0.5256,"serv_pct":0.5458,"sales_tip":"10万+","activity_type":3}],"total_count":1}
     */

    private GoodsSearchResponseBean goods_search_response;

    public GoodsSearchResponseBean getGoods_search_response() {
        return goods_search_response;
    }

    public void setGoods_search_response(GoodsSearchResponseBean goods_search_response) {
        this.goods_search_response = goods_search_response;
    }

    public static class GoodsSearchResponseBean {
        /**
         * goods_list : [{"has_mall_coupon":false,"mall_coupon_id":0,"mall_coupon_discount_pct":0,"mall_coupon_min_order_amount":0,"mall_coupon_max_discount_amount":0,"mall_coupon_total_quantity":0,"mall_coupon_remain_quantity":0,"mall_coupon_start_time":0,"mall_coupon_end_time":0,"goods_id":2338511,"goods_name":"【9.78元抢100000件，抢完恢复13.9元】【4-10斤】语衣甘蓝薰衣草香氛洗衣液 持久留香 低泡易漂家庭装","goods_desc":"【语衣甘蓝官方旗舰店-厂家直营正品保障】【温馨提示】优先点击\u201c去拼单\u201d成团立刻发货!分享到朋友圈、朋友群,成团更快更轻松!【语衣甘蓝,一心一意做洗涤】","goods_thumbnail_url":"https://t00img.yangkeduo.com/goods/images/2018-10-11/4f76d68b1123c812e8e8dce4fb45be69.jpeg","goods_image_url":"https://t00img.yangkeduo.com/t04img/images/2018-07-11/08b4c0f38e765963650ec42817d68f42.jpeg","goods_gallery_urls":["https://t00img.yangkeduo.com/goods/images/2018-10-11/340a8df38625128267232f6828d2a0b6.jpeg","https://t00img.yangkeduo.com/t09img/images/2018-07-11/ef9145c73254baaf7cd1a5179f8184cd.jpeg","https://t00img.yangkeduo.com/goods/images/2018-09-14/09f1cd686fdec767f370809c5ceb1b56.jpeg","https://t00img.yangkeduo.com/goods/images/2018-09-14/e29807bbea24d19da0cb4deeee6652ac.jpeg","https://t00img.yangkeduo.com/goods/images/2019-03-22/62fadbb7-2fd3-4190-a9e9-ab83f9dbfdaa.jpg"],"sold_quantity":4130775,"min_group_price":978,"min_normal_price":1600,"mall_name":"语衣甘蓝旗舰店","merchant_type":3,"category_id":8590,"category_name":"百货","opt_id":8590,"opt_name":"百货","opt_ids":[8592,328,408,8569,8570,8571,8590,8591,15],"cat_ids":[17285,17296,17393],"mall_cps":1,"has_coupon":true,"coupon_min_order_amount":100,"coupon_discount":100,"coupon_total_quantity":50000,"coupon_remain_quantity":21000,"coupon_start_time":1560182400,"coupon_end_time":1561910399,"promotion_rate":200,"goods_eval_score":4.57,"goods_eval_count":148199,"avg_desc":467,"avg_lgst":469,"avg_serv":470,"desc_pct":0.5869,"lgst_pct":0.5256,"serv_pct":0.5458,"sales_tip":"10万+","activity_type":3}]
         * total_count : 1
         */

        private int total_count;
        private List<GoodsListBean> goods_list;

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        @Override
        public String toString() {
            return "GoodsSearchResponseBean{" +
                    "total_count=" + total_count +
                    ", goods_list=" + goods_list +
                    '}';
        }

        public static class GoodsListBean {

            /**
             * has_mall_coupon : false
             * mall_coupon_id : 0
             * mall_coupon_discount_pct : 0
             * mall_coupon_min_order_amount : 0
             * mall_coupon_max_discount_amount : 0
             * mall_coupon_total_quantity : 0
             * mall_coupon_remain_quantity : 0
             * mall_coupon_start_time : 0
             * mall_coupon_end_time : 0
             * goods_id : 2338511
             * goods_name : 【9.78元抢100000件，抢完恢复13.9元】【4-10斤】语衣甘蓝薰衣草香氛洗衣液 持久留香 低泡易漂家庭装
             * goods_desc : 【语衣甘蓝官方旗舰店-厂家直营正品保障】【温馨提示】优先点击“去拼单”成团立刻发货!分享到朋友圈、朋友群,成团更快更轻松!【语衣甘蓝,一心一意做洗涤】
             * goods_thumbnail_url : https://t00img.yangkeduo.com/goods/images/2018-10-11/4f76d68b1123c812e8e8dce4fb45be69.jpeg
             * goods_image_url : https://t00img.yangkeduo.com/t04img/images/2018-07-11/08b4c0f38e765963650ec42817d68f42.jpeg
             * goods_gallery_urls : ["https://t00img.yangkeduo.com/goods/images/2018-10-11/340a8df38625128267232f6828d2a0b6.jpeg","https://t00img.yangkeduo.com/t09img/images/2018-07-11/ef9145c73254baaf7cd1a5179f8184cd.jpeg","https://t00img.yangkeduo.com/goods/images/2018-09-14/09f1cd686fdec767f370809c5ceb1b56.jpeg","https://t00img.yangkeduo.com/goods/images/2018-09-14/e29807bbea24d19da0cb4deeee6652ac.jpeg","https://t00img.yangkeduo.com/goods/images/2019-03-22/62fadbb7-2fd3-4190-a9e9-ab83f9dbfdaa.jpg"]
             * sold_quantity : 4141666
             * min_group_price : 978
             * min_normal_price : 1600
             * mall_name : 语衣甘蓝旗舰店
             * merchant_type : 3
             * category_id : 8590
             * category_name : 百货
             * opt_id : 8590
             * opt_name : 百货
             * opt_ids : [8592,328,408,8569,8570,8571,8590,8591,15]
             * cat_ids : [17285,17296,17393]
             * mall_cps : 1
             * has_coupon : true
             * coupon_min_order_amount : 100
             * coupon_discount : 100
             * coupon_total_quantity : 50000
             * coupon_remain_quantity : 45000
             * coupon_start_time : 1560182400
             * coupon_end_time : 1561910399
             * promotion_rate : 200
             * goods_eval_score : 4.57
             * goods_eval_count : 147862
             * avg_desc : 467
             * avg_lgst : 470
             * avg_serv : 470
             * desc_pct : 0.5875
             * lgst_pct : 0.5264
             * serv_pct : 0.5471
             * sales_tip : 10万+
             * activity_type : 3
             */

            private boolean has_mall_coupon;
            private String mall_coupon_id;
            private String mall_coupon_discount_pct;
            private String mall_coupon_min_order_amount;
            private String mall_coupon_max_discount_amount;
            private int mall_coupon_total_quantity;
            private int mall_coupon_remain_quantity;
            private String mall_coupon_start_time;
            private String mall_coupon_end_time;
            private String goods_id;
            private String goods_name;
            private String goods_desc;
            private String goods_thumbnail_url;
            private String goods_image_url;
            private int sold_quantity;
            private double min_group_price;
            private double min_normal_price;
            private String mall_name;
            private int merchant_type;
            private int category_id;
            private String category_name;
            private int opt_id;
            private String opt_name;
            private int mall_cps;
            private boolean has_coupon;
            private String coupon_min_order_amount;
            private String coupon_discount;
            private int coupon_total_quantity;
            private int coupon_remain_quantity;
            private String coupon_start_time;
            private String coupon_end_time;
            private String promotion_rate;
            private double goods_eval_score;
            private int goods_eval_count;
            private int avg_desc;
            private int avg_lgst;
            private int avg_serv;
            private double desc_pct;
            private double lgst_pct;
            private double serv_pct;
            private String sales_tip;
            private int activity_type;
            private List<String> goods_gallery_urls;
            private List<Integer> opt_ids;
            private List<Integer> cat_ids;
            private boolean isCheck;

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public boolean isHas_mall_coupon() {
                return has_mall_coupon;
            }

            public void setHas_mall_coupon(boolean has_mall_coupon) {
                this.has_mall_coupon = has_mall_coupon;
            }

            public String getMall_coupon_id() {
                return mall_coupon_id;
            }

            public void setMall_coupon_id(String mall_coupon_id) {
                this.mall_coupon_id = mall_coupon_id;
            }

            public String getMall_coupon_discount_pct() {
                return mall_coupon_discount_pct;
            }

            public void setMall_coupon_discount_pct(String mall_coupon_discount_pct) {
                this.mall_coupon_discount_pct = mall_coupon_discount_pct;
            }

            public String getMall_coupon_min_order_amount() {
                return mall_coupon_min_order_amount;
            }

            public void setMall_coupon_min_order_amount(String mall_coupon_min_order_amount) {
                this.mall_coupon_min_order_amount = mall_coupon_min_order_amount;
            }

            public String getMall_coupon_max_discount_amount() {
                return mall_coupon_max_discount_amount;
            }

            public void setMall_coupon_max_discount_amount(String mall_coupon_max_discount_amount) {
                this.mall_coupon_max_discount_amount = mall_coupon_max_discount_amount;
            }

            public int getMall_coupon_total_quantity() {
                return mall_coupon_total_quantity;
            }

            public void setMall_coupon_total_quantity(int mall_coupon_total_quantity) {
                this.mall_coupon_total_quantity = mall_coupon_total_quantity;
            }

            public int getMall_coupon_remain_quantity() {
                return mall_coupon_remain_quantity;
            }

            public void setMall_coupon_remain_quantity(int mall_coupon_remain_quantity) {
                this.mall_coupon_remain_quantity = mall_coupon_remain_quantity;
            }

            public String getMall_coupon_start_time() {
                return mall_coupon_start_time;
            }

            public void setMall_coupon_start_time(String mall_coupon_start_time) {
                this.mall_coupon_start_time = mall_coupon_start_time;
            }

            public String getMall_coupon_end_time() {
                return mall_coupon_end_time;
            }

            public void setMall_coupon_end_time(String mall_coupon_end_time) {
                this.mall_coupon_end_time = mall_coupon_end_time;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public String getGoods_thumbnail_url() {
                return goods_thumbnail_url;
            }

            public void setGoods_thumbnail_url(String goods_thumbnail_url) {
                this.goods_thumbnail_url = goods_thumbnail_url;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public int getSold_quantity() {
                return sold_quantity;
            }

            public void setSold_quantity(int sold_quantity) {
                this.sold_quantity = sold_quantity;
            }

            public double getMin_group_price() {
                return min_group_price;
            }

            public void setMin_group_price(double min_group_price) {
                this.min_group_price = min_group_price;
            }

            public double getMin_normal_price() {
                return min_normal_price;
            }

            public void setMin_normal_price(double min_normal_price) {
                this.min_normal_price = min_normal_price;
            }

            public String getMall_name() {
                return mall_name;
            }

            public void setMall_name(String mall_name) {
                this.mall_name = mall_name;
            }

            public int getMerchant_type() {
                return merchant_type;
            }

            public void setMerchant_type(int merchant_type) {
                this.merchant_type = merchant_type;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public int getOpt_id() {
                return opt_id;
            }

            public void setOpt_id(int opt_id) {
                this.opt_id = opt_id;
            }

            public String getOpt_name() {
                return opt_name;
            }

            public void setOpt_name(String opt_name) {
                this.opt_name = opt_name;
            }

            public int getMall_cps() {
                return mall_cps;
            }

            public void setMall_cps(int mall_cps) {
                this.mall_cps = mall_cps;
            }

            public boolean isHas_coupon() {
                return has_coupon;
            }

            public void setHas_coupon(boolean has_coupon) {
                this.has_coupon = has_coupon;
            }

            public String getCoupon_min_order_amount() {
                return coupon_min_order_amount;
            }

            public void setCoupon_min_order_amount(String coupon_min_order_amount) {
                this.coupon_min_order_amount = coupon_min_order_amount;
            }

            public String getCoupon_discount() {
                return coupon_discount;
            }

            public void setCoupon_discount(String coupon_discount) {
                this.coupon_discount = coupon_discount;
            }

            public int getCoupon_total_quantity() {
                return coupon_total_quantity;
            }

            public void setCoupon_total_quantity(int coupon_total_quantity) {
                this.coupon_total_quantity = coupon_total_quantity;
            }

            public int getCoupon_remain_quantity() {
                return coupon_remain_quantity;
            }

            public void setCoupon_remain_quantity(int coupon_remain_quantity) {
                this.coupon_remain_quantity = coupon_remain_quantity;
            }

            public String getCoupon_start_time() {
                return coupon_start_time;
            }

            public void setCoupon_start_time(String coupon_start_time) {
                this.coupon_start_time = coupon_start_time;
            }

            public String getCoupon_end_time() {
                return coupon_end_time;
            }

            public void setCoupon_end_time(String coupon_end_time) {
                this.coupon_end_time = coupon_end_time;
            }

            public String getPromotion_rate() {
                return promotion_rate;
            }

            public void setPromotion_rate(String promotion_rate) {
                this.promotion_rate = promotion_rate;
            }

            public double getGoods_eval_score() {
                return goods_eval_score;
            }

            public void setGoods_eval_score(double goods_eval_score) {
                this.goods_eval_score = goods_eval_score;
            }

            public int getGoods_eval_count() {
                return goods_eval_count;
            }

            public void setGoods_eval_count(int goods_eval_count) {
                this.goods_eval_count = goods_eval_count;
            }

            public int getAvg_desc() {
                return avg_desc;
            }

            public void setAvg_desc(int avg_desc) {
                this.avg_desc = avg_desc;
            }

            public int getAvg_lgst() {
                return avg_lgst;
            }

            public void setAvg_lgst(int avg_lgst) {
                this.avg_lgst = avg_lgst;
            }

            public int getAvg_serv() {
                return avg_serv;
            }

            public void setAvg_serv(int avg_serv) {
                this.avg_serv = avg_serv;
            }

            public double getDesc_pct() {
                return desc_pct;
            }

            public void setDesc_pct(double desc_pct) {
                this.desc_pct = desc_pct;
            }

            public double getLgst_pct() {
                return lgst_pct;
            }

            public void setLgst_pct(double lgst_pct) {
                this.lgst_pct = lgst_pct;
            }

            public double getServ_pct() {
                return serv_pct;
            }

            public void setServ_pct(double serv_pct) {
                this.serv_pct = serv_pct;
            }

            public String getSales_tip() {
                return sales_tip;
            }

            public void setSales_tip(String sales_tip) {
                this.sales_tip = sales_tip;
            }

            public int getActivity_type() {
                return activity_type;
            }

            public void setActivity_type(int activity_type) {
                this.activity_type = activity_type;
            }

            public List<String> getGoods_gallery_urls() {
                return goods_gallery_urls;
            }

            public void setGoods_gallery_urls(List<String> goods_gallery_urls) {
                this.goods_gallery_urls = goods_gallery_urls;
            }

            public List<Integer> getOpt_ids() {
                return opt_ids;
            }

            public void setOpt_ids(List<Integer> opt_ids) {
                this.opt_ids = opt_ids;
            }

            public List<Integer> getCat_ids() {
                return cat_ids;
            }

            public void setCat_ids(List<Integer> cat_ids) {
                this.cat_ids = cat_ids;
            }

            @Override
            public String toString() {
                return "GoodsListBean{" +
                        "has_mall_coupon=" + has_mall_coupon +
                        ", mall_coupon_id='" + mall_coupon_id + '\'' +
                        ", mall_coupon_discount_pct='" + mall_coupon_discount_pct + '\'' +
                        ", mall_coupon_min_order_amount='" + mall_coupon_min_order_amount + '\'' +
                        ", mall_coupon_max_discount_amount='" + mall_coupon_max_discount_amount + '\'' +
                        ", mall_coupon_total_quantity=" + mall_coupon_total_quantity +
                        ", mall_coupon_remain_quantity=" + mall_coupon_remain_quantity +
                        ", mall_coupon_start_time='" + mall_coupon_start_time + '\'' +
                        ", mall_coupon_end_time='" + mall_coupon_end_time + '\'' +
                        ", goods_id='" + goods_id + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", goods_desc='" + goods_desc + '\'' +
                        ", goods_thumbnail_url='" + goods_thumbnail_url + '\'' +
                        ", goods_image_url='" + goods_image_url + '\'' +
                        ", sold_quantity=" + sold_quantity +
                        ", min_group_price=" + min_group_price +
                        ", min_normal_price=" + min_normal_price +
                        ", mall_name='" + mall_name + '\'' +
                        ", merchant_type=" + merchant_type +
                        ", category_id=" + category_id +
                        ", category_name='" + category_name + '\'' +
                        ", opt_id=" + opt_id +
                        ", opt_name='" + opt_name + '\'' +
                        ", mall_cps=" + mall_cps +
                        ", has_coupon=" + has_coupon +
                        ", coupon_min_order_amount='" + coupon_min_order_amount + '\'' +
                        ", coupon_discount='" + coupon_discount + '\'' +
                        ", coupon_total_quantity=" + coupon_total_quantity +
                        ", coupon_remain_quantity=" + coupon_remain_quantity +
                        ", coupon_start_time='" + coupon_start_time + '\'' +
                        ", coupon_end_time='" + coupon_end_time + '\'' +
                        ", promotion_rate='" + promotion_rate + '\'' +
                        ", goods_eval_score=" + goods_eval_score +
                        ", goods_eval_count=" + goods_eval_count +
                        ", avg_desc=" + avg_desc +
                        ", avg_lgst=" + avg_lgst +
                        ", avg_serv=" + avg_serv +
                        ", desc_pct=" + desc_pct +
                        ", lgst_pct=" + lgst_pct +
                        ", serv_pct=" + serv_pct +
                        ", sales_tip='" + sales_tip + '\'' +
                        ", activity_type=" + activity_type +
                        ", goods_gallery_urls=" + goods_gallery_urls +
                        ", opt_ids=" + opt_ids +
                        ", cat_ids=" + cat_ids +
                        ", isCheck=" + isCheck +
                        '}';
            }
        }
    }
}
