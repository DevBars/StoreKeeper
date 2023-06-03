package com.project.storekeeper.container;

import java.math.BigDecimal;

public class Delivery {
    public static final String techDeliveryID = "deliveryID";
    public static final String techDeliveryName = "deliveryName";
    public static final String techDeliveryPrice = "deliveryPrice";
    public static final String techDeliveryContact = "deliveryContact";
    private Integer deliveryID;
    private String deliveryName;
    private BigDecimal deliveryPrice;
    private Integer deliveryContact;
    public Delivery() { }
    public Delivery(Integer deliveryID,
             String deliveryName,
             BigDecimal deliveryPrice,
             Integer deliveryContact) {
        this.deliveryID = deliveryID;
        this.deliveryName = deliveryName;
        this.deliveryPrice = deliveryPrice;
        this.deliveryContact = deliveryContact;
    }
    public void setDeliveryID(Integer deliveryID) {
        this.deliveryID = deliveryID;
    }
    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }
    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
    public void setDeliveryContact(Integer deliveryContact) {
        this.deliveryContact = deliveryContact;
    }
    public Integer getDeliveryID() {
        return deliveryID;
    }
    public String getDeliveryName() {
        return deliveryName;
    }
    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }
    public Integer getDeliveryContact() {
        return deliveryContact;
    }
}