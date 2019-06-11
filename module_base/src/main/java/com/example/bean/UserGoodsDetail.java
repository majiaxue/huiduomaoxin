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
     * sellName: 一二三旗舰店
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

    private String id;
    private String productCategoryId;
    private Long feightTemplateId;
    private String productAttributeCategoryId;
    private String name;
    private String pic;
    private String productSn;
    private String deleteStatus;
    private String publishStatus;
    private String newStatus;
    private String recommandStatus;
    private String verifyStatus;
    private String sort;
    private String sale;
    private double price;
    private double promotionPrice;
    private String giftGrowth;
    private String giftPoint;
    private String usePointLimit;
    private String subTitle;
    private String description;
    private double originalPrice;
    private String stock;
    private String lowStock;
    private String unit;
    private double weight;
    private String previewStatus;
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
    private String promotionPerLimit;
    private String promotionType;
    private String brandName;
    private String productCategoryName;
    private Object supplyId;
    private Object createTime;
    private String sellerId;
    private String sellerName;
    private String sellerLogo;
    private Object skuStockList;
    private String cateParentId;
    private String is_favorite;
    private Object path;
    private StoInfoBean stoInfo;
    private List<Attributes> xsProductAttributes;
    private List productLadderList;
    private List productFullReductionList;
    private List memberPriceList;
    private List productAttributeValueList;

    public List<Attributes> getXsProductAttributes() {
        return xsProductAttributes;
    }

    public void setXsProductAttributes(List<Attributes> xsProductAttributes) {
        this.xsProductAttributes = xsProductAttributes;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Long feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }

    public String getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(String productAttributeCategoryId) {
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

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(String recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
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

    public String getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(String giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public String getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(String giftPoint) {
        this.giftPoint = giftPoint;
    }

    public String getUsePointLimit() {
        return usePointLimit;
    }

    public void setUsePointLimit(String usePointLimit) {
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getLowStock() {
        return lowStock;
    }

    public void setLowStock(String lowStock) {
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

    public String getPreviewStatus() {
        return previewStatus;
    }

    public void setPreviewStatus(String previewStatus) {
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

    public String getPromotionPerLimit() {
        return promotionPerLimit;
    }

    public void setPromotionPerLimit(String promotionPerLimit) {
        this.promotionPerLimit = promotionPerLimit;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Object getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(Object skuStockList) {
        this.skuStockList = skuStockList;
    }

    public String getCateParentId() {
        return cateParentId;
    }

    public void setCateParentId(String cateParentId) {
        this.cateParentId = cateParentId;
    }

    public String getIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(String is_favorite) {
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

    public List getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }

    public List getMemberPriceList() {
        return memberPriceList;
    }

    public void setMemberPriceList(List memberPriceList) {
        this.memberPriceList = memberPriceList;
    }

    public List getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public static class Attributes {

        /**
         * id : 1
         * productAttributeCategoryId : 1
         * name : 尺寸
         * selectType : 2
         * inputType : 1
         * inputList : s,m,l,xl,xxl,xxxl
         * sort : 0
         * filterType : 0
         * searchType : 0
         * relatedStatus : 0
         * handAddStatus : 0
         * type : 0
         */

        private int id;
        private int productAttributeCategoryId;
        private String name;
        private int selectType;
        private int inputType;
        private String inputList;
        private int sort;
        private int filterType;
        private int searchType;
        private int relatedStatus;
        private int handAddStatus;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getSelectType() {
            return selectType;
        }

        public void setSelectType(int selectType) {
            this.selectType = selectType;
        }

        public int getInputType() {
            return inputType;
        }

        public void setInputType(int inputType) {
            this.inputType = inputType;
        }

        public String getInputList() {
            return inputList;
        }

        public void setInputList(String inputList) {
            this.inputList = inputList;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getFilterType() {
            return filterType;
        }

        public void setFilterType(int filterType) {
            this.filterType = filterType;
        }

        public int getSearchType() {
            return searchType;
        }

        public void setSearchType(int searchType) {
            this.searchType = searchType;
        }

        public int getRelatedStatus() {
            return relatedStatus;
        }

        public void setRelatedStatus(int relatedStatus) {
            this.relatedStatus = relatedStatus;
        }

        public int getHandAddStatus() {
            return handAddStatus;
        }

        public void setHandAddStatus(int handAddStatus) {
            this.handAddStatus = handAddStatus;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "id=" + id +
                    ", productAttributeCategoryId=" + productAttributeCategoryId +
                    ", name='" + name + '\'' +
                    ", selectType=" + selectType +
                    ", inputType=" + inputType +
                    ", inputList='" + inputList + '\'' +
                    ", sort=" + sort +
                    ", filterType=" + filterType +
                    ", searchType=" + searchType +
                    ", relatedStatus=" + relatedStatus +
                    ", handAddStatus=" + handAddStatus +
                    ", type=" + type +
                    '}';
        }
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

                private String id;
                private String productId;
                private String skuCode;
                private double price;
                private Long stock;
                private Long lowStock;
                private String sp1;
                private String sp2;
                private String sp3;
                private String pic;
                private Long sale;
                private double promotionPrice;
                private Long lockStock;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getSkuCode() {
                    return skuCode;
                }

                public void setSkuCode(String skuCode) {
                    this.skuCode = skuCode;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public Long getStock() {
                    return stock;
                }

                public void setStock(Long stock) {
                    this.stock = stock;
                }

                public Long getLowStock() {
                    return lowStock;
                }

                public void setLowStock(Long lowStock) {
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

                public String getSp3() {
                    return sp3;
                }

                public void setSp3(String sp3) {
                    this.sp3 = sp3;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public Long getSale() {
                    return sale;
                }

                public void setSale(Long sale) {
                    this.sale = sale;
                }

                public double getPromotionPrice() {
                    return promotionPrice;
                }

                public void setPromotionPrice(double promotionPrice) {
                    this.promotionPrice = promotionPrice;
                }

                public Long getLockStock() {
                    return lockStock;
                }

                public void setLockStock(Long lockStock) {
                    this.lockStock = lockStock;
                }

                @Override
                public String toString() {
                    return "ListBean{" +
                            "id=" + id +
                            ", productId=" + productId +
                            ", skuCode='" + skuCode + '\'' +
                            ", price=" + price +
                            ", stock=" + stock +
                            ", lowStock=" + lowStock +
                            ", sp1='" + sp1 + '\'' +
                            ", sp2='" + sp2 + '\'' +
                            ", sp3=" + sp3 +
                            ", pic='" + pic + '\'' +
                            ", sale=" + sale +
                            ", promotionPrice=" + promotionPrice +
                            ", lockStock=" + lockStock +
                            '}';
                }
            }
        }

        @Override
        public String toString() {
            return "StoInfoBean{" +
                    "records=" + records +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserGoodsDetail{" +
                "id='" + id + '\'' +
                ", productCategoryId='" + productCategoryId + '\'' +
                ", feightTemplateId=" + feightTemplateId +
                ", productAttributeCategoryId='" + productAttributeCategoryId + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", productSn='" + productSn + '\'' +
                ", deleteStatus='" + deleteStatus + '\'' +
                ", publishStatus='" + publishStatus + '\'' +
                ", newStatus='" + newStatus + '\'' +
                ", recommandStatus='" + recommandStatus + '\'' +
                ", verifyStatus='" + verifyStatus + '\'' +
                ", sort='" + sort + '\'' +
                ", sale='" + sale + '\'' +
                ", price=" + price +
                ", promotionPrice=" + promotionPrice +
                ", giftGrowth='" + giftGrowth + '\'' +
                ", giftPoint='" + giftPoint + '\'' +
                ", usePointLimit='" + usePointLimit + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", description='" + description + '\'' +
                ", originalPrice=" + originalPrice +
                ", stock='" + stock + '\'' +
                ", lowStock='" + lowStock + '\'' +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                ", previewStatus='" + previewStatus + '\'' +
                ", serviceIds='" + serviceIds + '\'' +
                ", keywords='" + keywords + '\'' +
                ", note='" + note + '\'' +
                ", albumPics='" + albumPics + '\'' +
                ", detailTitle='" + detailTitle + '\'' +
                ", detailDesc='" + detailDesc + '\'' +
                ", detailHtml='" + detailHtml + '\'' +
                ", detailMobileHtml='" + detailMobileHtml + '\'' +
                ", promotionStartTime=" + promotionStartTime +
                ", promotionEndTime=" + promotionEndTime +
                ", promotionPerLimit='" + promotionPerLimit + '\'' +
                ", promotionType='" + promotionType + '\'' +
                ", brandName='" + brandName + '\'' +
                ", productCategoryName='" + productCategoryName + '\'' +
                ", supplyId=" + supplyId +
                ", createTime=" + createTime +
                ", sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerLogo='" + sellerLogo + '\'' +
                ", skuStockList=" + skuStockList +
                ", cateParentId='" + cateParentId + '\'' +
                ", is_favorite='" + is_favorite + '\'' +
                ", path=" + path +
                ", stoInfo=" + stoInfo +
                ", xsProductAttributes=" + xsProductAttributes +
                ", productLadderList=" + productLadderList +
                ", productFullReductionList=" + productFullReductionList +
                ", memberPriceList=" + memberPriceList +
                ", productAttributeValueList=" + productAttributeValueList +
                '}';
    }
}
