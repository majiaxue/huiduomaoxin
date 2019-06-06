package com.example.secondarydetails.bean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryTabBean {

    /**
     * goods_cats_get_response : {"goods_cats_list":[{"level":1,"cat_name":"男装","parent_cat_id":0,"cat_id":239},{"level":1,"cat_name":"办公用品","parent_cat_id":0,"cat_id":2603},{"level":1,"cat_name":"文化用品","parent_cat_id":0,"cat_id":2629},{"level":1,"cat_name":"3C数码配件","parent_cat_id":0,"cat_id":2933},{"level":1,"cat_name":"古董/邮币/字画/收藏","parent_cat_id":0,"cat_id":5217},{"level":1,"cat_name":"影音电器","parent_cat_id":0,"cat_id":5752},{"level":1,"cat_name":"手机","parent_cat_id":0,"cat_id":5834},{"level":1,"cat_name":"二手数码","parent_cat_id":0,"cat_id":5839},{"level":1,"cat_name":"电脑","parent_cat_id":0,"cat_id":5851},{"level":1,"cat_name":"数码相机/单反相机/摄像机","parent_cat_id":0,"cat_id":5906},{"level":1,"cat_name":"智能设备","parent_cat_id":0,"cat_id":5921},{"level":1,"cat_name":"厨房电器","parent_cat_id":0,"cat_id":5955},{"level":1,"cat_name":"大家电","parent_cat_id":0,"cat_id":6076},{"level":1,"cat_name":"生活电器","parent_cat_id":0,"cat_id":6128},{"level":1,"cat_name":"个人护理保健","parent_cat_id":0,"cat_id":6209},{"level":1,"cat_name":"网络设备","parent_cat_id":0,"cat_id":6290},{"level":1,"cat_name":"零食/坚果/特产","parent_cat_id":0,"cat_id":6398},{"level":1,"cat_name":"咖啡/麦片/冲饮","parent_cat_id":0,"cat_id":6536},{"level":1,"cat_name":"茶","parent_cat_id":0,"cat_id":6586},{"level":1,"cat_name":"粮油米面/南北干货/调味品","parent_cat_id":0,"cat_id":6630},{"level":1,"cat_name":"酒类","parent_cat_id":0,"cat_id":6758},{"level":1,"cat_name":"保健食品/膳食营养补充食品","parent_cat_id":0,"cat_id":6785},{"level":1,"cat_name":"传统滋补品","parent_cat_id":0,"cat_id":6883},{"level":1,"cat_name":"电子元器件市场","parent_cat_id":0,"cat_id":7323},{"level":1,"cat_name":"摩托车/装备/配件","parent_cat_id":0,"cat_id":7629},{"level":1,"cat_name":"汽车/用品/配件/改装","parent_cat_id":0,"cat_id":7639},{"level":1,"cat_name":"水产肉类/新鲜蔬果/熟食","parent_cat_id":0,"cat_id":8172},{"level":1,"cat_name":"女装/女士精品","parent_cat_id":0,"cat_id":8439},{"level":1,"cat_name":"新车/二手车","parent_cat_id":0,"cat_id":8502},{"level":1,"cat_name":"流行男鞋","parent_cat_id":0,"cat_id":8508},{"level":1,"cat_name":"女鞋","parent_cat_id":0,"cat_id":8509},{"level":1,"cat_name":"箱包皮具/女包/男包","parent_cat_id":0,"cat_id":8538},{"level":1,"cat_name":"内衣裤袜","parent_cat_id":0,"cat_id":8583},{"level":1,"cat_name":"腕表眼镜","parent_cat_id":0,"cat_id":8634},{"level":1,"cat_name":"服饰配件","parent_cat_id":0,"cat_id":8669},{"level":1,"cat_name":"本地化生活服务","parent_cat_id":0,"cat_id":8721},{"level":1,"cat_name":"电影/演出/体育赛事","parent_cat_id":0,"cat_id":8722},{"level":1,"cat_name":"个性定制/设计服务","parent_cat_id":0,"cat_id":8723},{"level":1,"cat_name":"购物卡/礼品卡/代金券","parent_cat_id":0,"cat_id":8724},{"level":1,"cat_name":"婚庆/摄影/摄像服务","parent_cat_id":0,"cat_id":8725},{"level":1,"cat_name":"教育培训","parent_cat_id":0,"cat_id":8726},{"level":1,"cat_name":"景点门票/周边游","parent_cat_id":0,"cat_id":8727},{"level":1,"cat_name":"旅游路线/商品/服务","parent_cat_id":0,"cat_id":8728},{"level":1,"cat_name":"生活缴费","parent_cat_id":0,"cat_id":8729},{"level":1,"cat_name":"影视/会员/腾讯QQ专区","parent_cat_id":0,"cat_id":8730},{"level":1,"cat_name":"特价酒店/客栈/公寓旅馆","parent_cat_id":0,"cat_id":8731},{"level":1,"cat_name":"网络服务/软件","parent_cat_id":0,"cat_id":8732},{"level":1,"cat_name":"网上营业厅","parent_cat_id":0,"cat_id":8733},{"level":1,"cat_name":"休闲娱乐","parent_cat_id":0,"cat_id":8734},{"level":1,"cat_name":"游戏服务/直播","parent_cat_id":0,"cat_id":8736},{"level":1,"cat_name":"床上用品","parent_cat_id":0,"cat_id":9313},{"level":1,"cat_name":"电子/电工","parent_cat_id":0,"cat_id":9314},{"level":1,"cat_name":"基础建材","parent_cat_id":0,"cat_id":9315},{"level":1,"cat_name":"家居饰品","parent_cat_id":0,"cat_id":9316},{"level":1,"cat_name":"家装灯饰光源","parent_cat_id":0,"cat_id":9317},{"level":1,"cat_name":"家装主材","parent_cat_id":0,"cat_id":9318},{"level":1,"cat_name":"居家布艺","parent_cat_id":0,"cat_id":9319},{"level":1,"cat_name":"全屋定制","parent_cat_id":0,"cat_id":9320},{"level":1,"cat_name":"商业/办公家具","parent_cat_id":0,"cat_id":9321},{"level":1,"cat_name":"特色手工艺","parent_cat_id":0,"cat_id":9322},{"level":1,"cat_name":"五金工具","parent_cat_id":0,"cat_id":9323},{"level":1,"cat_name":"住宅家具","parent_cat_id":0,"cat_id":9324},{"level":1,"cat_name":"电动车/配件/交通工具","parent_cat_id":0,"cat_id":11683},{"level":1,"cat_name":"户外/登山/旅行野营用品","parent_cat_id":0,"cat_id":11684},{"level":1,"cat_name":"运动/瑜伽/健身/球类","parent_cat_id":0,"cat_id":11685},{"level":1,"cat_name":"运动包/户外包/配件","parent_cat_id":0,"cat_id":11686},{"level":1,"cat_name":"运动服/休闲服","parent_cat_id":0,"cat_id":11687},{"level":1,"cat_name":"运动鞋","parent_cat_id":0,"cat_id":11688},{"level":1,"cat_name":"自行车/骑行装备/零配件","parent_cat_id":0,"cat_id":11689},{"level":1,"cat_name":"OTC药品/医疗器械/计生用品","parent_cat_id":0,"cat_id":13176},{"level":1,"cat_name":"精制中药材","parent_cat_id":0,"cat_id":13177},{"level":1,"cat_name":"隐形眼镜/护理液","parent_cat_id":0,"cat_id":13178},{"level":1,"cat_name":"奶粉/辅食/营养品/零食","parent_cat_id":0,"cat_id":14697},{"level":1,"cat_name":"尿片/洗护/喂哺/推车床","parent_cat_id":0,"cat_id":14740},{"level":1,"cat_name":"童鞋/婴儿鞋/亲子鞋","parent_cat_id":0,"cat_id":14933},{"level":1,"cat_name":"童装/婴儿装/亲子装","parent_cat_id":0,"cat_id":14966},{"level":1,"cat_name":"玩具/童车/益智/积木/模型","parent_cat_id":0,"cat_id":15083},{"level":1,"cat_name":"孕产妇用品/孕妇装/营养","parent_cat_id":0,"cat_id":15356},{"level":1,"cat_name":"书籍/杂志/报纸","parent_cat_id":0,"cat_id":15543},{"level":1,"cat_name":"农机/农具/农膜","parent_cat_id":0,"cat_id":16155},{"level":1,"cat_name":"畜牧/养殖物资","parent_cat_id":0,"cat_id":16192},{"level":1,"cat_name":"农用物资","parent_cat_id":0,"cat_id":16209},{"level":1,"cat_name":"成人用品/情趣用品","parent_cat_id":0,"cat_id":16237},{"level":1,"cat_name":"宠物/宠物食品及用品","parent_cat_id":0,"cat_id":16288},{"level":1,"cat_name":"餐饮具","parent_cat_id":0,"cat_id":16548},{"level":1,"cat_name":"厨房/烹饪用具","parent_cat_id":0,"cat_id":16676},{"level":1,"cat_name":"收纳整理","parent_cat_id":0,"cat_id":16794},{"level":1,"cat_name":"家庭/个人清洁工具","parent_cat_id":0,"cat_id":16901},{"level":1,"cat_name":"居家日用","parent_cat_id":0,"cat_id":16989},{"level":1,"cat_name":"节庆用品/礼品","parent_cat_id":0,"cat_id":17134},{"level":1,"cat_name":"烟品/打火机/瑞士军刀","parent_cat_id":0,"cat_id":17249},{"level":1,"cat_name":"洗护清洁剂/卫生巾/纸/香薰","parent_cat_id":0,"cat_id":17285},{"level":1,"cat_name":"饰品/流行首饰/时尚饰品","parent_cat_id":0,"cat_id":17412},{"level":1,"cat_name":"珠宝/钻石/翡翠/黄金","parent_cat_id":0,"cat_id":17455},{"level":1,"cat_name":"鲜花速递/花卉仿真/绿植园艺","parent_cat_id":0,"cat_id":17671},{"level":1,"cat_name":"乐器/吉他/钢琴/配件","parent_cat_id":0,"cat_id":17803},{"level":1,"cat_name":"装修设计/施工/监理","parent_cat_id":0,"cat_id":18088},{"level":1,"cat_name":"医疗健康服务","parent_cat_id":0,"cat_id":18270},{"level":1,"cat_name":"彩妆/香水/美妆工具","parent_cat_id":0,"cat_id":18482},{"level":1,"cat_name":"美容美体仪器","parent_cat_id":0,"cat_id":18574},{"level":1,"cat_name":"美发护发/假发","parent_cat_id":0,"cat_id":18601},{"level":1,"cat_name":"美容护肤/美体/精油","parent_cat_id":0,"cat_id":18637}]}
     */

    private GoodsCatsGetResponseBean goods_cats_get_response;

    public GoodsCatsGetResponseBean getGoods_cats_get_response() {
        return goods_cats_get_response;
    }

    public void setGoods_cats_get_response(GoodsCatsGetResponseBean goods_cats_get_response) {
        this.goods_cats_get_response = goods_cats_get_response;
    }

    public static class GoodsCatsGetResponseBean {
        private List<GoodsCatsListBean> goods_cats_list;

        public List<GoodsCatsListBean> getGoods_cats_list() {
            return goods_cats_list;
        }

        public void setGoods_cats_list(List<GoodsCatsListBean> goods_cats_list) {
            this.goods_cats_list = goods_cats_list;
        }

        public static class GoodsCatsListBean {
            /**
             * level : 1
             * cat_name : 男装
             * parent_cat_id : 0
             * cat_id : 239
             */

            private int level;
            private String cat_name;
            private int parent_cat_id;
            private int cat_id;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public int getParent_cat_id() {
                return parent_cat_id;
            }

            public void setParent_cat_id(int parent_cat_id) {
                this.parent_cat_id = parent_cat_id;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }
        }
    }
}
