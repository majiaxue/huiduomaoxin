package com.example.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/15
 * Describe:
 */
public class JDTabBean {

    /**
     * code : 200
     * data : [{"grade":0,"name":"数码","id":652,"parentId":0},{"grade":0,"name":"电脑、办公","id":670,"parentId":0},{"grade":0,"name":"家用电器","id":737,"parentId":0},{"grade":0,"name":"食品饮料","id":1320,"parentId":0},{"grade":0,"name":"服饰内衣","id":1315,"parentId":0},{"grade":0,"name":"美妆护肤","id":1316,"parentId":0},{"grade":0,"name":"运动户外","id":1318,"parentId":0},{"grade":0,"name":"母婴","id":1319,"parentId":0},{"grade":0,"name":"家居日用","id":1620,"parentId":0},{"grade":0,"name":"图书","id":1713,"parentId":0},{"grade":0,"name":"礼品","id":1672,"parentId":0},{"grade":0,"name":"文娱","id":4053,"parentId":0},{"grade":0,"name":"本地生活/旅游出行","id":4938,"parentId":0},{"grade":0,"name":"钟表","id":5025,"parentId":0},{"grade":0,"name":"数字内容","id":5272,"parentId":0},{"grade":0,"name":"厨具","id":6196,"parentId":0},{"grade":0,"name":"珠宝首饰","id":6144,"parentId":0},{"grade":0,"name":"玩具乐器","id":6233,"parentId":0},{"grade":0,"name":"宠物生活","id":6994,"parentId":0},{"grade":0,"name":"汽车用品","id":6728,"parentId":0},{"grade":0,"name":"医药保健","id":9192,"parentId":0},{"grade":0,"name":"家装建材","id":9855,"parentId":0},{"grade":0,"name":"家具","id":9847,"parentId":0},{"grade":0,"name":"手机通讯","id":9987,"parentId":0},{"grade":0,"name":"鞋靴","id":11729,"parentId":0},{"grade":0,"name":"生鲜","id":12218,"parentId":0},{"grade":0,"name":"酒类","id":12259,"parentId":0},{"grade":0,"name":"团购","id":12367,"parentId":0},{"grade":0,"name":"整车","id":12379,"parentId":0},{"grade":0,"name":"农资园艺","id":12473,"parentId":0},{"grade":0,"name":"处方药","id":13314,"parentId":0},{"grade":0,"name":"教育培训","id":13678,"parentId":0},{"grade":0,"name":"二手商品","id":13765,"parentId":0},{"grade":0,"name":"邮币","id":13887,"parentId":0},{"grade":0,"name":"IP","id":13996,"parentId":0},{"grade":0,"name":"工业品","id":14065,"parentId":0},{"grade":0,"name":"房地产","id":15083,"parentId":0},{"grade":0,"name":"艺术品","id":15126,"parentId":0},{"grade":0,"name":"家纺","id":15248,"parentId":0},{"grade":0,"name":"家庭清洁/纸品","id":15901,"parentId":0},{"grade":0,"name":"京东服务","id":15980,"parentId":0},{"grade":0,"name":"个人护理","id":16750,"parentId":0},{"grade":0,"name":"箱包皮具","id":17329,"parentId":0},{"grade":0,"name":"非遗","id":18528,"parentId":0}]
     * msg : 数据获取成功.
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
         * grade : 0
         * name : 数码
         * id : 652
         * parentId : 0
         */

        private int grade;
        private String name;
        private int id;
        private int parentId;

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "grade=" + grade +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    ", parentId=" + parentId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JDTabBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
