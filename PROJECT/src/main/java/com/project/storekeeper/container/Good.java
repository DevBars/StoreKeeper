package com.project.storekeeper.container;

public class Good {
    public static final String techGoodID = "goodID";
    public static final String techGoodName = "goodName";
    public static final String techGoodMaker = "goodMaker";
    public static final String techGoodVolume = "goodVolume";
    public static final String techGoodDescription = "goodDescription";
    private Integer goodID;
    private String goodName;
    private String goodMaker;
    private Integer goodVolume;
    private String goodDescription;
    public Good() { }
    public Good(Integer goodID,
                String goodName,
                String goodMaker,
                Integer goodVolume,
                String goodDescription) {
        this.goodID = goodID;
        this.goodName = goodName;
        this.goodMaker = goodMaker;
        this.goodVolume = goodVolume;
        this.goodDescription = goodDescription;
    }
    public void setGoodID(Integer goodID) {
        this.goodID = goodID;
    }
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
    public void setGoodMaker(String goodMaker) {
        this.goodMaker = goodMaker;
    }
    public void setGoodVolume(Integer goodVolume) {
        this.goodVolume = goodVolume;
    }
    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }
    public Integer getGoodID() {
        return goodID;
    }
    public String getGoodName() {
        return goodName;
    }
    public String getGoodMaker() {
        return goodMaker;
    }
    public Integer getGoodVolume() {
        return goodVolume;
    }
    public String getGoodDescription() {
        return goodDescription;
    }
}