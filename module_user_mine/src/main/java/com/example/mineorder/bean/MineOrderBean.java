package com.example.mineorder.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class MineOrderBean {

    /**
     * total : 2
     * orderList : [{"orderId":1,"sellerName":"小米旗舰店","image":"http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg","goodsName":null,"goodsPrice":998,"sp1":"1","sp2":"1","sp3":"1","goodsNum":1,"status":6},{"orderId":2,"sellerName":"小米旗舰店","image":"http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg","goodsName":null,"goodsPrice":999,"sp1":"土豪金","sp2":"16g","sp3":"","goodsNum":1,"status":6}]
     */
    private List<OrderListBean> orderList;

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * orderId : 1
         * sellerName : 小米旗舰店
         * image : http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg
         * goodsName : null
         * goodsPrice : 998
         * sp1 : 1
         * sp2 : 1
         * sp3 : 1
         * goodsNum : 1
         * status : 6
         */

        private int orderId;
        private String sellerName;
        private String image;
        private String goodsName;
        private int goodsPrice;
        private String sp1;
        private String sp2;
        private String sp3;
        private int goodsNum;
        private int status;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(int goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getSp1() {
            return sp1;
        }

        public void setSp1(String sp1) {
            this.sp1 = sp1;
        }

        public String getSp2() {
            return sp2;
        }

        public void setSp2(String sp2) {
            this.sp2 = sp2;
        }

        public String getSp3() {
            return sp3;
        }

        public void setSp3(String sp3) {
            this.sp3 = sp3;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
