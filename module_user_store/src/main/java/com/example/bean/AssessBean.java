package com.example.bean;

import java.util.List;

public class AssessBean {

    /**
     * records : [{"id":2,"memberNickName":"评论","memberIcon":"2","star":5,"content":"123","pics":"1","productId":38,"productName":"1","productAttribute":"1","memberIp":"1","showStatus":1,"collectCount":1,"readCount":1,"replayCount":1,"createTime":"2019-06-04 11:17:13","sellerId":1,"sellerDescribe":1,"sellerLogistics":1,"sellerServer":1}]
     * total : 1
     * size : 2
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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

    public static class RecordsBean {
        /**
         * id : 2
         * memberNickName : 评论
         * memberIcon : 2
         * star : 5
         * content : 123
         * pics : 1
         * productId : 38
         * productName : 1
         * productAttribute : 1
         * memberIp : 1
         * showStatus : 1
         * collectCount : 1
         * readCount : 1
         * replayCount : 1
         * createTime : 2019-06-04 11:17:13
         * sellerId : 1
         * sellerDescribe : 1
         * sellerLogistics : 1
         * sellerServer : 1
         */

        private int id;
        private String memberNickName;
        private String memberIcon;
        private int star;
        private String content;
        private String pics;
        private int productId;
        private String productName;
        private String productAttribute;
        private String memberIp;
        private int showStatus;
        private int collectCount;
        private int readCount;
        private int replayCount;
        private String createTime;
        private int sellerId;
        private int sellerDescribe;
        private int sellerLogistics;
        private int sellerServer;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemberNickName() {
            return memberNickName;
        }

        public void setMemberNickName(String memberNickName) {
            this.memberNickName = memberNickName;
        }

        public String getMemberIcon() {
            return memberIcon;
        }

        public void setMemberIcon(String memberIcon) {
            this.memberIcon = memberIcon;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductAttribute() {
            return productAttribute;
        }

        public void setProductAttribute(String productAttribute) {
            this.productAttribute = productAttribute;
        }

        public String getMemberIp() {
            return memberIp;
        }

        public void setMemberIp(String memberIp) {
            this.memberIp = memberIp;
        }

        public int getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(int showStatus) {
            this.showStatus = showStatus;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getReplayCount() {
            return replayCount;
        }

        public void setReplayCount(int replayCount) {
            this.replayCount = replayCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getSellerDescribe() {
            return sellerDescribe;
        }

        public void setSellerDescribe(int sellerDescribe) {
            this.sellerDescribe = sellerDescribe;
        }

        public int getSellerLogistics() {
            return sellerLogistics;
        }

        public void setSellerLogistics(int sellerLogistics) {
            this.sellerLogistics = sellerLogistics;
        }

        public int getSellerServer() {
            return sellerServer;
        }

        public void setSellerServer(int sellerServer) {
            this.sellerServer = sellerServer;
        }
    }
}
