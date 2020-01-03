package com.realmmvvm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class StoreInfo extends RealmObject{
    @PrimaryKey
    private long id;
    private String order_id;
    private String Order_date;
    private String customer_id;
    private String customer_name;
    private String segment;
    private String country;
    private String city;
    private String product_id;
    private String category;
    private String sub_category;
    private String product_name;
    private double sales;
    private long quantity;
    private double discount;
    private double profit;

    public StoreInfo() {
    }

    public StoreInfo(long id, String order_id, String order_date, String customer_id, String customer_name, String segment, String country, String city, String product_id, String category, String sub_category, String product_name, double sales, long quantity, double discount, double profit) {
        this.id = id;
        this.order_id = order_id;
        Order_date = order_date;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.segment = segment;
        this.country = country;
        this.city = city;
        this.product_id = product_id;
        this.category = category;
        this.sub_category = sub_category;
        this.product_name = product_name;
        this.sales = sales;
        this.quantity = quantity;
        this.discount = discount;
        this.profit = profit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(String order_date) {
        Order_date = order_date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
