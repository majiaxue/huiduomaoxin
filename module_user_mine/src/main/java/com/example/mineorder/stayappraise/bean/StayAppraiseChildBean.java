package com.example.mineorder.stayappraise.bean;

/**
 * Created by cuihaohao on 2019/5/30
 * Describe:
 */
public class StayAppraiseChildBean {
    private int image;
    private String name;
    private String message;
    private String price;
    private String count;
    private String total;

    public StayAppraiseChildBean(int image, String name, String message, String price, String count, String total) {
        this.image = image;
        this.name = name;
        this.message = message;
        this.price = price;
        this.count = count;
        this.total = total;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
