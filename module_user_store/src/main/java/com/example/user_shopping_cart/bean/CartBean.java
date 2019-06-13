package com.example.user_shopping_cart.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartBean {


    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * sellerId : 1
         * sellerName : 小米旗舰店
         * items : [{"id":1,"productId":1,"productSkuId":238,"userId":1,"quantity":1,"price":999,"sp1":"土豪金","sp2":"16g","sp3":"","productPic":"http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg","productName":"小米6","productSubTitle":"小米6-双核-土豪金-16g","memberNickname":"1","createDate":"2019-05-31 14:29:50","modifyDate":"2019-05-31 14:29:52","productCategoryId":1,"productBrand":"小米","productSn":"1","productAttr":"1","checked":1,"sellerId":1,"sellerName":"小米旗舰店"},{"id":2,"productId":1,"productSkuId":238,"userId":1,"quantity":1,"price":888,"sp1":"土豪金","sp2":"16g","sp3":"","productPic":"http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg","productName":"小米8","productSubTitle":"小米8-八核-土豪金-128g","memberNickname":"1","createDate":"2019-06-04 10:09:34","modifyDate":"2019-06-04 10:09:36","productCategoryId":1,"productBrand":"小米","productSn":"1","productAttr":"1","checked":1,"sellerId":1,"sellerName":"小米旗舰店"}]
         */

        private int sellerId;
        private String sellerName;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private List<ItemsBean> items;

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 1
             * productId : 1
             * productSkuId : 238
             * userId : 1
             * quantity : 1
             * price : 999
             * sp1 : 土豪金
             * sp2 : 16g
             * sp3 :
             * productPic : http://192.168.1.5:9000/seller/a34a9e8cd52f4b1f9f779f107cd511a3.jpg
             * productName : 小米6
             * productSubTitle : 小米6-双核-土豪金-16g
             * memberNickname : 1
             * createDate : 2019-05-31 14:29:50
             * modifyDate : 2019-05-31 14:29:52
             * productCategoryId : 1
             * productBrand : 小米
             * productSn : 1
             * productAttr : 1
             * checked : 1
             * sellerId : 1
             * sellerName : 小米旗舰店
             */

            private int id;
            private int productId;
            private int productSkuId;
            private int userId;
            private int quantity;
            private int price;
            private String sp1;
            private String sp2;
            private String sp3;
            private String productPic;
            private String productName;
            private String productSubTitle;
            private String memberNickname;
            private String createDate;
            private String modifyDate;
            private int productCategoryId;
            private String productBrand;
            private String productSn;
            private String productAttr;
            private int checked;
            private int sellerId;
            private String sellerName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getProductSkuId() {
                return productSkuId;
            }

            public void setProductSkuId(int productSkuId) {
                this.productSkuId = productSkuId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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

            public String getProductPic() {
                return productPic;
            }

            public void setProductPic(String productPic) {
                this.productPic = productPic;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductSubTitle() {
                return productSubTitle;
            }

            public void setProductSubTitle(String productSubTitle) {
                this.productSubTitle = productSubTitle;
            }

            public String getMemberNickname() {
                return memberNickname;
            }

            public void setMemberNickname(String memberNickname) {
                this.memberNickname = memberNickname;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public int getProductCategoryId() {
                return productCategoryId;
            }

            public void setProductCategoryId(int productCategoryId) {
                this.productCategoryId = productCategoryId;
            }

            public String getProductBrand() {
                return productBrand;
            }

            public void setProductBrand(String productBrand) {
                this.productBrand = productBrand;
            }

            public String getProductSn() {
                return productSn;
            }

            public void setProductSn(String productSn) {
                this.productSn = productSn;
            }

            public String getProductAttr() {
                return productAttr;
            }

            public void setProductAttr(String productAttr) {
                this.productAttr = productAttr;
            }

            public int getChecked() {
                return checked;
            }

            public void setChecked(int checked) {
                this.checked = checked;
            }

            public int getSellerId() {
                return sellerId;
            }

            public void setSellerId(int sellerId) {
                this.sellerId = sellerId;
            }

            public String getSellerName() {
                return sellerName;
            }

            public void setSellerName(String sellerName) {
                this.sellerName = sellerName;
            }
        }
    }
}
