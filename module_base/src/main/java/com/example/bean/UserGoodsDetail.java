package com.example.bean;

import java.util.List;

public class UserGoodsDetail {

    /**
     * id : 39
     * productCategoryId : 92
     * feightTemplateId : 0
     * productAttributeCategoryId : 1
     * name : 夜店女装夜总会性感连衣裙气质大露背修身包臀开叉夜场裙子小礼服
     * pic : http://192.168.1.5:9000/goods/d099e84d9b294fcaabd6d91db3d16f83.jpg
     * productSn : LW6788C17
     * deleteStatus : 0
     * publishStatus : 1
     * newStatus : 1
     * recommandStatus : 1
     * verifyStatus : 1
     * sort : 0
     * sale : 0
     * price : 193.0
     * promotionPrice : 1.0
     * giftGrowth : 123
     * giftPoint : 123
     * usePointLimit : 123
     * subTitle : 闪亮水钻+网纱 做工精细 性感大露背
     * description : 性感女装
     * originalPrice : 193.0
     * stock : 100
     * lowStock : 0
     * unit : 件
     * weight : 0.0
     * previewStatus : 0
     * serviceIds : 1,2,3
     * keywords : 闪亮水钻+网纱 做工精细 性感大露背
     * note :
     * albumPics : http://192.168.1.5:9000/goods/379d96753eda43a0b18013627d26901f.jpg,http://192.168.1.5:9000/goods/3fb81dcba0c64cea8e0f33708df1c003.jpg
     * detailTitle : 夜店女装夜总会性感连衣裙气质大露背修身包臀开叉夜场裙子小礼服
     * detailDesc : 闪亮水钻+网纱 做工精细 性感大露背
     * detailHtml : <p><img src="http://192.168.1.5:9000/goods/107559a8a9b34b4c97436497484fbd66.jpg"></p><p><img src="http://192.168.1.5:9000/goods/b424833470684a918c29b152fa90e3ef.jpg"></p><p><img src="http://192.168.1.5:9000/goods/10edd5816b674d17b420d8a034cc84c2.jpg"></p><p><img src="http://192.168.1.5:9000/goods/fa99e8f4663942659ad445b0d396b2f8.jpg"></p>
     * detailMobileHtml :
     * promotionStartTime : null
     * promotionEndTime : null
     * promotionPerLimit : 0
     * promotionType : 4
     * brandName :
     * productCategoryName : 短裙
     * supplyId : null
     * createTime : null
     * sellerId : 11
     * productLadderList : []
     * productFullReductionList : []
     * memberPriceList : []
     * skuStockList : null
     * productAttributeValueList : []
     * cateParentId : 80
     * is_favorite : 0
     * path : null
     * stoInfo : {"records":[{"skuName":"黑色","list":[{"id":263,"productId":39,"skuCode":"201905270039001","price":null,"stock":0,"lowStock":0,"sp1":"黑色","sp2":"M","sp3":null,"pic":"http://192.168.1.5:9000/goods/4edc88f93808416fa93698c5b975688a.jpg","sale":null,"promotionPrice":null,"lockStock":0},{"id":264,"productId":39,"skuCode":"201905270039002","price":null,"stock":0,"lowStock":0,"sp1":"黑色","sp2":"XL","sp3":null,"pic":"http://192.168.1.5:9000/goods/4edc88f93808416fa93698c5b975688a.jpg","sale":null,"promotionPrice":null,"lockStock":0}]},{"skuName":"白色","list":[{"id":265,"productId":39,"skuCode":"201905270039003","price":null,"stock":0,"lowStock":0,"sp1":"白色","sp2":"M","sp3":null,"pic":"http://192.168.1.5:9000/goods/6079d34a440b4c8a80d1e1521def1600.jpg","sale":null,"promotionPrice":null,"lockStock":0},{"id":266,"productId":39,"skuCode":"201905270039004","price":null,"stock":0,"lowStock":0,"sp1":"白色","sp2":"XL","sp3":null,"pic":"http://192.168.1.5:9000/goods/6079d34a440b4c8a80d1e1521def1600.jpg","sale":null,"promotionPrice":null,"lockStock":0}]}]}
     */

    private int id;
    private int productCategoryId;
    private int feightTemplateId;
    private int productAttributeCategoryId;
    private String name;
    private String pic;
    private String productSn;
    private int deleteStatus;
    private int publishStatus;
    private int newStatus;
    private int recommandStatus;
    private int verifyStatus;
    private int sort;
    private int sale;
    private double price;
    private double promotionPrice;
    private int giftGrowth;
    private int giftPoint;
    private int usePointLimit;
    private String subTitle;
    private String description;
    private double originalPrice;
    private int stock;
    private int lowStock;
    private String unit;
    private double weight;
    private int previewStatus;
    private String serviceIds;
    private String keywords;
    private String note;
    private String albumPics;
    private String detailTitle;
    private String detailDesc;
    private String detailHtml;
    private String detailMobileHtml;
    private Object promotionStartTime;
    private Object promotionEndTime;
    private int promotionPerLimit;
    private int promotionType;
    private String brandName;
    private String productCategoryName;
    private Object supplyId;
    private Object createTime;
    private int sellerId;
    private Object skuStockList;
    private int cateParentId;
    private int is_favorite;
    private Object path;
    private StoInfoBean stoInfo;
    private List<?> productLadderList;
    private List<?> productFullReductionList;
    private List<?> memberPriceList;
    private List<?> productAttributeValueList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(int feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }

    public int getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(int productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }

    public int getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(int newStatus) {
        this.newStatus = newStatus;
    }

    public int getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(int recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public int getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(int giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public int getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(int giftPoint) {
        this.giftPoint = giftPoint;
    }

    public int getUsePointLimit() {
        return usePointLimit;
    }

    public void setUsePointLimit(int usePointLimit) {
        this.usePointLimit = usePointLimit;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getLowStock() {
        return lowStock;
    }

    public void setLowStock(int lowStock) {
        this.lowStock = lowStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPreviewStatus() {
        return previewStatus;
    }

    public void setPreviewStatus(int previewStatus) {
        this.previewStatus = previewStatus;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public String getDetailMobileHtml() {
        return detailMobileHtml;
    }

    public void setDetailMobileHtml(String detailMobileHtml) {
        this.detailMobileHtml = detailMobileHtml;
    }

    public Object getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(Object promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public Object getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(Object promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public int getPromotionPerLimit() {
        return promotionPerLimit;
    }

    public void setPromotionPerLimit(int promotionPerLimit) {
        this.promotionPerLimit = promotionPerLimit;
    }

    public int getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(int promotionType) {
        this.promotionType = promotionType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Object getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Object supplyId) {
        this.supplyId = supplyId;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Object getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(Object skuStockList) {
        this.skuStockList = skuStockList;
    }

    public int getCateParentId() {
        return cateParentId;
    }

    public void setCateParentId(int cateParentId) {
        this.cateParentId = cateParentId;
    }

    public int getIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(int is_favorite) {
        this.is_favorite = is_favorite;
    }

    public Object getPath() {
        return path;
    }

    public void setPath(Object path) {
        this.path = path;
    }

    public StoInfoBean getStoInfo() {
        return stoInfo;
    }

    public void setStoInfo(StoInfoBean stoInfo) {
        this.stoInfo = stoInfo;
    }

    public List<?> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<?> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<?> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<?> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }

    public List<?> getMemberPriceList() {
        return memberPriceList;
    }

    public void setMemberPriceList(List<?> memberPriceList) {
        this.memberPriceList = memberPriceList;
    }

    public List<?> getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List<?> productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public static class StoInfoBean {
        private List<RecordsBean> records;

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * skuName : 黑色
             * list : [{"id":263,"productId":39,"skuCode":"201905270039001","price":null,"stock":0,"lowStock":0,"sp1":"黑色","sp2":"M","sp3":null,"pic":"http://192.168.1.5:9000/goods/4edc88f93808416fa93698c5b975688a.jpg","sale":null,"promotionPrice":null,"lockStock":0},{"id":264,"productId":39,"skuCode":"201905270039002","price":null,"stock":0,"lowStock":0,"sp1":"黑色","sp2":"XL","sp3":null,"pic":"http://192.168.1.5:9000/goods/4edc88f93808416fa93698c5b975688a.jpg","sale":null,"promotionPrice":null,"lockStock":0}]
             */

            private String skuName;
            private List<ListBean> list;

            public String getSkuName() {
                return skuName;
            }

            public void setSkuName(String skuName) {
                this.skuName = skuName;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 263
                 * productId : 39
                 * skuCode : 201905270039001
                 * price : null
                 * stock : 0
                 * lowStock : 0
                 * sp1 : 黑色
                 * sp2 : M
                 * sp3 : null
                 * pic : http://192.168.1.5:9000/goods/4edc88f93808416fa93698c5b975688a.jpg
                 * sale : null
                 * promotionPrice : null
                 * lockStock : 0
                 */

                private int id;
                private int productId;
                private String skuCode;
                private Object price;
                private int stock;
                private int lowStock;
                private String sp1;
                private String sp2;
                private Object sp3;
                private String pic;
                private Object sale;
                private Object promotionPrice;
                private int lockStock;

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

                public String getSkuCode() {
                    return skuCode;
                }

                public void setSkuCode(String skuCode) {
                    this.skuCode = skuCode;
                }

                public Object getPrice() {
                    return price;
                }

                public void setPrice(Object price) {
                    this.price = price;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public int getLowStock() {
                    return lowStock;
                }

                public void setLowStock(int lowStock) {
                    this.lowStock = lowStock;
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

                public Object getSp3() {
                    return sp3;
                }

                public void setSp3(Object sp3) {
                    this.sp3 = sp3;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public Object getSale() {
                    return sale;
                }

                public void setSale(Object sale) {
                    this.sale = sale;
                }

                public Object getPromotionPrice() {
                    return promotionPrice;
                }

                public void setPromotionPrice(Object promotionPrice) {
                    this.promotionPrice = promotionPrice;
                }

                public int getLockStock() {
                    return lockStock;
                }

                public void setLockStock(int lockStock) {
                    this.lockStock = lockStock;
                }
            }
        }
    }
}
