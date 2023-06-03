package com.project.storekeeper.container;

public class Maker {
    public static final String techMakerID = "makerID";
    public static final String techMakerName = "makerName";
    private Integer makerID;
    private String makerName;
    public Maker() { }
    public Maker(Integer makerID,
                 String makerName) {
        this.makerID = makerID;
        this.makerName = makerName;
    }
    public void setMakerID(Integer makerID) {
        this.makerID = makerID;
    }
    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }
    public Integer getMakerID() {
        return makerID;
    }
    public String getMakerName() {
        return makerName;
    }
}