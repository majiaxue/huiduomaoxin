package com.example.bean;

import java.io.Serializable;

public class LocalShopBean implements Serializable {

    /**
     * seller_lon : 116.42494202613686
     * seller_shop_name : 123123
     * seller_business_hours : 18:09:33  -  19:09:33
     * seller_business_license_url :
     * seller_id_back_card_url :
     * create_time : 2019-07-05 18:09:42
     * distance : 607183.3712051439
     * star : 4
     * seller_introduce :
     * seller_food_safety_permit_url : seller-dee17f863fdd481b88aeab99cb7a75f1.png
     * seller_id_positive_card_url :
     * seller_ischeck : true
     * seller_category_name : KTV
     * seller_addredd : 北京市 北京 丰台区 东高地
     * update_time : 2019-07-13 16:16:29
     * user_code :
     * seller_logo : seller-8ca73f2c5b984ed09e011701e85cbf6b.png
     * seller_name : 123123
     * seller_lat : 39.81223704079177
     * seller_type : 1
     * seller_phone : 123123
     * id : 94
     * seller_category : 10004
     * seller_status : true
     */

    private String seller_lon;
    private String seller_shop_name;    //商店名字
    private String seller_business_hours;
    private String seller_business_license_url;
    private String seller_id_back_card_url;
    private String create_time;
    private String distance;
    private int star;
    private String seller_introduce;
    private String seller_food_safety_permit_url;
    private String seller_id_positive_card_url;
    private boolean seller_ischeck;
    private String seller_category_name;
    private String seller_addredd;
    private String update_time;
    private String user_code;
    private String seller_logo;
    private String seller_name;     //开店人的姓名
    private String seller_lat;
    private String seller_type;
    private String seller_phone;
    private String id;
    private String seller_category;
    private boolean seller_status;

    public String getSeller_lon() {
        return seller_lon;
    }

    public void setSeller_lon(String seller_lon) {
        this.seller_lon = seller_lon;
    }

    public String getSeller_shop_name() {
        return seller_shop_name;
    }

    public void setSeller_shop_name(String seller_shop_name) {
        this.seller_shop_name = seller_shop_name;
    }

    public String getSeller_business_hours() {
        return seller_business_hours;
    }

    public void setSeller_business_hours(String seller_business_hours) {
        this.seller_business_hours = seller_business_hours;
    }

    public String getSeller_business_license_url() {
        return seller_business_license_url;
    }

    public void setSeller_business_license_url(String seller_business_license_url) {
        this.seller_business_license_url = seller_business_license_url;
    }

    public String getSeller_id_back_card_url() {
        return seller_id_back_card_url;
    }

    public void setSeller_id_back_card_url(String seller_id_back_card_url) {
        this.seller_id_back_card_url = seller_id_back_card_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getSeller_introduce() {
        return seller_introduce;
    }

    public void setSeller_introduce(String seller_introduce) {
        this.seller_introduce = seller_introduce;
    }

    public String getSeller_food_safety_permit_url() {
        return seller_food_safety_permit_url;
    }

    public void setSeller_food_safety_permit_url(String seller_food_safety_permit_url) {
        this.seller_food_safety_permit_url = seller_food_safety_permit_url;
    }

    public String getSeller_id_positive_card_url() {
        return seller_id_positive_card_url;
    }

    public void setSeller_id_positive_card_url(String seller_id_positive_card_url) {
        this.seller_id_positive_card_url = seller_id_positive_card_url;
    }

    public boolean isSeller_ischeck() {
        return seller_ischeck;
    }

    public void setSeller_ischeck(boolean seller_ischeck) {
        this.seller_ischeck = seller_ischeck;
    }

    public String getSeller_category_name() {
        return seller_category_name;
    }

    public void setSeller_category_name(String seller_category_name) {
        this.seller_category_name = seller_category_name;
    }

    public String getSeller_addredd() {
        return seller_addredd;
    }

    public void setSeller_addredd(String seller_addredd) {
        this.seller_addredd = seller_addredd;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getSeller_logo() {
        return seller_logo;
    }

    public void setSeller_logo(String seller_logo) {
        this.seller_logo = seller_logo;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_lat() {
        return seller_lat;
    }

    public void setSeller_lat(String seller_lat) {
        this.seller_lat = seller_lat;
    }

    public String getSeller_type() {
        return seller_type;
    }

    public void setSeller_type(String seller_type) {
        this.seller_type = seller_type;
    }

    public String getSeller_phone() {
        return seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        this.seller_phone = seller_phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeller_category() {
        return seller_category;
    }

    public void setSeller_category(String seller_category) {
        this.seller_category = seller_category;
    }

    public boolean isSeller_status() {
        return seller_status;
    }

    public void setSeller_status(boolean seller_status) {
        this.seller_status = seller_status;
    }

    @Override
    public String toString() {
        return "LocalShopBean{" +
                "seller_lon='" + seller_lon + '\'' +
                ", seller_shop_name='" + seller_shop_name + '\'' +
                ", seller_business_hours='" + seller_business_hours + '\'' +
                ", seller_business_license_url='" + seller_business_license_url + '\'' +
                ", seller_id_back_card_url='" + seller_id_back_card_url + '\'' +
                ", create_time='" + create_time + '\'' +
                ", distance='" + distance + '\'' +
                ", star=" + star +
                ", seller_introduce='" + seller_introduce + '\'' +
                ", seller_food_safety_permit_url='" + seller_food_safety_permit_url + '\'' +
                ", seller_id_positive_card_url='" + seller_id_positive_card_url + '\'' +
                ", seller_ischeck=" + seller_ischeck +
                ", seller_category_name='" + seller_category_name + '\'' +
                ", seller_addredd='" + seller_addredd + '\'' +
                ", update_time='" + update_time + '\'' +
                ", user_code='" + user_code + '\'' +
                ", seller_logo='" + seller_logo + '\'' +
                ", seller_name='" + seller_name + '\'' +
                ", seller_lat='" + seller_lat + '\'' +
                ", seller_type='" + seller_type + '\'' +
                ", seller_phone='" + seller_phone + '\'' +
                ", id='" + id + '\'' +
                ", seller_category='" + seller_category + '\'' +
                ", seller_status=" + seller_status +
                '}';
    }
}
