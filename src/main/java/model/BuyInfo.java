package model;

import java.text.DecimalFormat;

public class BuyInfo {
    private Long uid;
    private Long gid;
    private int num;
    private String gname;
    private String category;
    private String description;
    private double price;
    private double cost;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void calcCost() {
        DecimalFormat df = new DecimalFormat("0.00");
        this.cost = this.getPrice()*this.getNum();
        this.cost = Double.parseDouble(df.format(this.cost));
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
