package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class LocalTuiKuanBean {

    /**
     * records : [{"id":33,"orderId":390,"companyAddressId":null,"productId":null,"orderSn":"157406191377359298","createTime":"2019-11-18 15:27:17","memberUsername":"13201835918","returnAmount":0,"returnName":"晓玉","returnPhone":"13201835918","returnStatus":0,"handleTime":null,"productPic":null,"productName":null,"productBrand":null,"productAttr":null,"productCount":null,"productPrice":null,"productRealPrice":0,"reason":null,"description":null,"proofPics":"","handleNote":null,"handleMan":null,"receiveMan":null,"receiveTime":null,"receiveNote":null,"sellerId":18,"userCode":"298242555449966592","pics":null,"localOrderItemList":null},{"id":34,"orderId":397,"companyAddressId":null,"productId":null,"orderSn":"157406293291459298","createTime":"2019-11-18 15:43:56","memberUsername":"13201835918","returnAmount":0,"returnName":"晓玉","returnPhone":"13201835918","returnStatus":0,"handleTime":null,"productPic":null,"productName":null,"productBrand":null,"productAttr":null,"productCount":null,"productPrice":null,"productRealPrice":0,"reason":null,"description":null,"proofPics":"","handleNote":null,"handleMan":null,"receiveMan":null,"receiveTime":null,"receiveNote":null,"sellerId":18,"userCode":"298242555449966592","pics":null,"localOrderItemList":null}]
     * total : 2
     * size : 10
     * current : 0
     * searchCount : true
     * pages : 1
     */

    private long total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "LocalTuiKuanBean{" +
                "total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", searchCount=" + searchCount +
                ", pages=" + pages +
                ", records=" + records +
                '}';
    }

    public static class RecordsBean {
        /**
         * id : 33
         * orderId : 390
         * companyAddressId : null
         * productId : null
         * orderSn : 157406191377359298
         * createTime : 2019-11-18 15:27:17
         * memberUsername : 13201835918
         * returnAmount : 0
         * returnName : 晓玉
         * returnPhone : 13201835918
         * returnStatus : 0
         * handleTime : null
         * productPic : null
         * productName : null
         * productBrand : null
         * productAttr : null
         * productCount : null
         * productPrice : null
         * productRealPrice : 0
         * reason : null
         * description : null
         * proofPics :
         * handleNote : null
         * handleMan : null
         * receiveMan : null
         * receiveTime : null
         * receiveNote : null
         * sellerId : 18
         * userCode : 298242555449966592
         * pics : null
         * localOrderItemList : null
         */

        private String id;
        private String orderId;
        private String companyAddressId;
        private String productId;
        private String orderSn;
        private String createTime;
        private String memberUsername;
        private double returnAmount;
        private String returnName;
        private String returnPhone;
        private String returnStatus;
        private String handleTime;
        private String productPic;
        private String productName;
        private String productBrand;
        private String productAttr;
        private int productCount;
        private String productPrice;
        private double productRealPrice;
        private String reason;
        private String description;
        private String proofPics;
        private String handleNote;
        private String handleMan;
        private String receiveMan;
        private String receiveTime;
        private String receiveNote;
        private String sellerId;
        private String userCode;
        private String pics;
        private List<LocalOrderItemBean> localOrderItemList;
        private Seller seller;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCompanyAddressId() {
            return companyAddressId;
        }

        public void setCompanyAddressId(String companyAddressId) {
            this.companyAddressId = companyAddressId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMemberUsername() {
            return memberUsername;
        }

        public void setMemberUsername(String memberUsername) {
            this.memberUsername = memberUsername;
        }

        public double getReturnAmount() {
            return returnAmount;
        }

        public void setReturnAmount(double returnAmount) {
            this.returnAmount = returnAmount;
        }

        public String getReturnName() {
            return returnName;
        }

        public void setReturnName(String returnName) {
            this.returnName = returnName;
        }

        public String getReturnPhone() {
            return returnPhone;
        }

        public void setReturnPhone(String returnPhone) {
            this.returnPhone = returnPhone;
        }

        public String getReturnStatus() {
            return returnStatus;
        }

        public void setReturnStatus(String returnStatus) {
            this.returnStatus = returnStatus;
        }

        public String getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(String handleTime) {
            this.handleTime = handleTime;
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

        public String getProductBrand() {
            return productBrand;
        }

        public void setProductBrand(String productBrand) {
            this.productBrand = productBrand;
        }

        public String getProductAttr() {
            return productAttr;
        }

        public void setProductAttr(String productAttr) {
            this.productAttr = productAttr;
        }

        public int getProductCount() {
            return productCount;
        }

        public void setProductCount(int productCount) {
            this.productCount = productCount;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public double getProductRealPrice() {
            return productRealPrice;
        }

        public void setProductRealPrice(double productRealPrice) {
            this.productRealPrice = productRealPrice;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProofPics() {
            return proofPics;
        }

        public void setProofPics(String proofPics) {
            this.proofPics = proofPics;
        }

        public String getHandleNote() {
            return handleNote;
        }

        public void setHandleNote(String handleNote) {
            this.handleNote = handleNote;
        }

        public String getHandleMan() {
            return handleMan;
        }

        public void setHandleMan(String handleMan) {
            this.handleMan = handleMan;
        }

        public String getReceiveMan() {
            return receiveMan;
        }

        public void setReceiveMan(String receiveMan) {
            this.receiveMan = receiveMan;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getReceiveNote() {
            return receiveNote;
        }

        public void setReceiveNote(String receiveNote) {
            this.receiveNote = receiveNote;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public List<LocalOrderItemBean> getLocalOrderItemList() {
            return localOrderItemList;
        }

        public void setLocalOrderItemList(List<LocalOrderItemBean> localOrderItemList) {
            this.localOrderItemList = localOrderItemList;
        }

        public Seller getSeller() {
            return seller;
        }

        public void setSeller(Seller seller) {
            this.seller = seller;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "id='" + id + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", companyAddressId='" + companyAddressId + '\'' +
                    ", productId='" + productId + '\'' +
                    ", orderSn='" + orderSn + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", memberUsername='" + memberUsername + '\'' +
                    ", returnAmount=" + returnAmount +
                    ", returnName='" + returnName + '\'' +
                    ", returnPhone='" + returnPhone + '\'' +
                    ", returnStatus='" + returnStatus + '\'' +
                    ", handleTime='" + handleTime + '\'' +
                    ", productPic='" + productPic + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productBrand='" + productBrand + '\'' +
                    ", productAttr='" + productAttr + '\'' +
                    ", productCount=" + productCount +
                    ", productPrice='" + productPrice + '\'' +
                    ", productRealPrice=" + productRealPrice +
                    ", reason='" + reason + '\'' +
                    ", description='" + description + '\'' +
                    ", proofPics='" + proofPics + '\'' +
                    ", handleNote='" + handleNote + '\'' +
                    ", handleMan='" + handleMan + '\'' +
                    ", receiveMan='" + receiveMan + '\'' +
                    ", receiveTime='" + receiveTime + '\'' +
                    ", receiveNote='" + receiveNote + '\'' +
                    ", sellerId='" + sellerId + '\'' +
                    ", userCode='" + userCode + '\'' +
                    ", pics='" + pics + '\'' +
                    ", localOrderItemList=" + localOrderItemList +
                    ", seller=" + seller +
                    '}';
        }

        public static class LocalOrderItemBean implements Serializable {
            /**
             * id : 1
             * orderSn : 12213
             * goodsId : 6
             * goodsNum : 2
             * goodsPrice : 10
             * goodsSpec : 大份
             * goodsName : 山沟的名字很长
             * goodsPic : http://192.168.0.17:9000/parameter/71093e43ac484951a4b62db9b6d13d67.png
             */

            private String id;
            private String orderSn;
            private String goodsId;
            private int goodsNum;
            private String goodsPrice;
            private String goodsSpec;
            private String goodsName;
            private String goodsPic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrderSn() {
                return orderSn;
            }

            public void setOrderSn(String orderSn) {
                this.orderSn = orderSn;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(String goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public String getGoodsSpec() {
                return goodsSpec;
            }

            public void setGoodsSpec(String goodsSpec) {
                this.goodsSpec = goodsSpec;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsPic() {
                return goodsPic;
            }

            public void setGoodsPic(String goodsPic) {
                this.goodsPic = goodsPic;
            }

            @Override
            public String toString() {
                return "LocalOrderItemListBean{" +
                        "id='" + id + '\'' +
                        ", orderSn='" + orderSn + '\'' +
                        ", goodsId='" + goodsId + '\'' +
                        ", goodsNum='" + goodsNum + '\'' +
                        ", goodsPrice=" + goodsPrice +
                        ", goodsSpec='" + goodsSpec + '\'' +
                        ", goodsName='" + goodsName + '\'' +
                        ", goodsPic='" + goodsPic + '\'' +
                        '}';
            }
        }

        public static class Seller {

            /**
             * id : 18
             * userCode : 349497299002458112
             * sellerShopName : 脆哨店铺
             * sellerLogo : http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg
             * sellerCategory : 食品
             * sellerName : 崔绍川
             * sellerPhone : 13253300882
             * sellerIdPositiveCardUrl : seller-7744959af1454ae2ab38e9e59fec39be..jpg
             * sellerIdBackCardUrl : seller-a2695f0ba84e4415bcb8b6229baf6920..jpg
             * sellerBusinessLicenseUrl : seller-1de27a6ecb94484db4c66e81efdb972f..jpg
             * sellerFoodSafetyPermitUrl :
             * sellerIscheck : 1
             * sellerAddredd : 河南省 郑州 迭部县 啦啦啦111
             * sellerStatus : 1
             * createTime : 2019-10-31 10:43:03
             * updateTime : 2019-11-18 10:26:23
             * sellerIntroduce :
             * favoriteId : null
             * sellerType : 1
             * sellerLon : 113.66378927001952
             * sellerLat : 34.7599163363232
             * sellerBusinessHours : 09:00-23:00
             * sellerPics : http://47.99.93.123:8083/parameter/977f63022cb44b0f87e8546fa516f0e8.jpg
             * amount : 0
             * minPoint : null
             * label : null
             * proportion : null
             * pigxxId : 18
             * hotRecommendStatus : 1
             * localRecommendStatus : 1
             */

            private String id;
            private String userCode;
            private String sellerShopName;
            private String sellerLogo;
            private String sellerCategory;
            private String sellerName;
            private String sellerPhone;
            private String sellerIdPositiveCardUrl;
            private String sellerIdBackCardUrl;
            private String sellerBusinessLicenseUrl;
            private String sellerFoodSafetyPermitUrl;
            private String sellerIscheck;
            private String sellerAddredd;
            private int sellerStatus;
            private String createTime;
            private String updateTime;
            private String sellerIntroduce;
            private String favoriteId;
            private int sellerType;
            private String sellerLon;
            private String sellerLat;
            private String sellerBusinessHours;
            private String sellerPics;
            private String amount;
            private String minPoint;
            private String label;
            private String proportion;
            private String pigxxId;
            private int hotRecommendStatus;
            private int localRecommendStatus;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getSellerShopName() {
                return sellerShopName;
            }

            public void setSellerShopName(String sellerShopName) {
                this.sellerShopName = sellerShopName;
            }

            public String getSellerLogo() {
                return sellerLogo;
            }

            public void setSellerLogo(String sellerLogo) {
                this.sellerLogo = sellerLogo;
            }

            public String getSellerCategory() {
                return sellerCategory;
            }

            public void setSellerCategory(String sellerCategory) {
                this.sellerCategory = sellerCategory;
            }

            public String getSellerName() {
                return sellerName;
            }

            public void setSellerName(String sellerName) {
                this.sellerName = sellerName;
            }

            public String getSellerPhone() {
                return sellerPhone;
            }

            public void setSellerPhone(String sellerPhone) {
                this.sellerPhone = sellerPhone;
            }

            public String getSellerIdPositiveCardUrl() {
                return sellerIdPositiveCardUrl;
            }

            public void setSellerIdPositiveCardUrl(String sellerIdPositiveCardUrl) {
                this.sellerIdPositiveCardUrl = sellerIdPositiveCardUrl;
            }

            public String getSellerIdBackCardUrl() {
                return sellerIdBackCardUrl;
            }

            public void setSellerIdBackCardUrl(String sellerIdBackCardUrl) {
                this.sellerIdBackCardUrl = sellerIdBackCardUrl;
            }

            public String getSellerBusinessLicenseUrl() {
                return sellerBusinessLicenseUrl;
            }

            public void setSellerBusinessLicenseUrl(String sellerBusinessLicenseUrl) {
                this.sellerBusinessLicenseUrl = sellerBusinessLicenseUrl;
            }

            public String getSellerFoodSafetyPermitUrl() {
                return sellerFoodSafetyPermitUrl;
            }

            public void setSellerFoodSafetyPermitUrl(String sellerFoodSafetyPermitUrl) {
                this.sellerFoodSafetyPermitUrl = sellerFoodSafetyPermitUrl;
            }

            public String getSellerIscheck() {
                return sellerIscheck;
            }

            public void setSellerIscheck(String sellerIscheck) {
                this.sellerIscheck = sellerIscheck;
            }

            public String getSellerAddredd() {
                return sellerAddredd;
            }

            public void setSellerAddredd(String sellerAddredd) {
                this.sellerAddredd = sellerAddredd;
            }

            public int getSellerStatus() {
                return sellerStatus;
            }

            public void setSellerStatus(int sellerStatus) {
                this.sellerStatus = sellerStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getSellerIntroduce() {
                return sellerIntroduce;
            }

            public void setSellerIntroduce(String sellerIntroduce) {
                this.sellerIntroduce = sellerIntroduce;
            }

            public String getFavoriteId() {
                return favoriteId;
            }

            public void setFavoriteId(String favoriteId) {
                this.favoriteId = favoriteId;
            }

            public int getSellerType() {
                return sellerType;
            }

            public void setSellerType(int sellerType) {
                this.sellerType = sellerType;
            }

            public String getSellerLon() {
                return sellerLon;
            }

            public void setSellerLon(String sellerLon) {
                this.sellerLon = sellerLon;
            }

            public String getSellerLat() {
                return sellerLat;
            }

            public void setSellerLat(String sellerLat) {
                this.sellerLat = sellerLat;
            }

            public String getSellerBusinessHours() {
                return sellerBusinessHours;
            }

            public void setSellerBusinessHours(String sellerBusinessHours) {
                this.sellerBusinessHours = sellerBusinessHours;
            }

            public String getSellerPics() {
                return sellerPics;
            }

            public void setSellerPics(String sellerPics) {
                this.sellerPics = sellerPics;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getMinPoint() {
                return minPoint;
            }

            public void setMinPoint(String minPoint) {
                this.minPoint = minPoint;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getProportion() {
                return proportion;
            }

            public void setProportion(String proportion) {
                this.proportion = proportion;
            }

            public String getPigxxId() {
                return pigxxId;
            }

            public void setPigxxId(String pigxxId) {
                this.pigxxId = pigxxId;
            }

            public int getHotRecommendStatus() {
                return hotRecommendStatus;
            }

            public void setHotRecommendStatus(int hotRecommendStatus) {
                this.hotRecommendStatus = hotRecommendStatus;
            }

            public int getLocalRecommendStatus() {
                return localRecommendStatus;
            }

            public void setLocalRecommendStatus(int localRecommendStatus) {
                this.localRecommendStatus = localRecommendStatus;
            }

            @Override
            public String toString() {
                return "Seller{" +
                        "id='" + id + '\'' +
                        ", userCode='" + userCode + '\'' +
                        ", sellerShopName='" + sellerShopName + '\'' +
                        ", sellerLogo='" + sellerLogo + '\'' +
                        ", sellerCategory='" + sellerCategory + '\'' +
                        ", sellerName='" + sellerName + '\'' +
                        ", sellerPhone='" + sellerPhone + '\'' +
                        ", sellerIdPositiveCardUrl='" + sellerIdPositiveCardUrl + '\'' +
                        ", sellerIdBackCardUrl='" + sellerIdBackCardUrl + '\'' +
                        ", sellerBusinessLicenseUrl='" + sellerBusinessLicenseUrl + '\'' +
                        ", sellerFoodSafetyPermitUrl='" + sellerFoodSafetyPermitUrl + '\'' +
                        ", sellerIscheck='" + sellerIscheck + '\'' +
                        ", sellerAddredd='" + sellerAddredd + '\'' +
                        ", sellerStatus=" + sellerStatus +
                        ", createTime='" + createTime + '\'' +
                        ", updateTime='" + updateTime + '\'' +
                        ", sellerIntroduce='" + sellerIntroduce + '\'' +
                        ", favoriteId='" + favoriteId + '\'' +
                        ", sellerType=" + sellerType +
                        ", sellerLon='" + sellerLon + '\'' +
                        ", sellerLat='" + sellerLat + '\'' +
                        ", sellerBusinessHours='" + sellerBusinessHours + '\'' +
                        ", sellerPics='" + sellerPics + '\'' +
                        ", amount='" + amount + '\'' +
                        ", minPoint='" + minPoint + '\'' +
                        ", label='" + label + '\'' +
                        ", proportion='" + proportion + '\'' +
                        ", pigxxId='" + pigxxId + '\'' +
                        ", hotRecommendStatus=" + hotRecommendStatus +
                        ", localRecommendStatus=" + localRecommendStatus +
                        '}';
            }
        }
    }
}
