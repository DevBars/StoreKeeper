package com.project.storekeeper.container;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Movement {
    public static final String techMovementID = "movementID";
    public static final String techMovementStatus = "movementStatus";
    public static final String techMovementGood = "movementGood";
    public static final String techMovementVolume = "movementVolume";
    public static final String techMovementPrice = "movementPrice";
    public static final String techMovementContact = "movementContact";
    public static final String techMovementDelivery = "movementDelivery";
    public static final String techMovementStartDate = "movementStartDate";
    public static final String techMovementStartTime = "movementStartTime";
    public static final String techMovementActive = "movementActive";
    public static final String techMovementEndDate = "movementEndDate";
    public static final String techMovementEndTime = "movementEndTime";
    private Integer movementID;
    private Status movementStatus;
    private Integer movementGood;
    private Integer movementVolume;
    private BigDecimal movementPrice;
    private Integer movementContact;
    private Integer movementDelivery;
    private LocalDate movementStartDate;
    private LocalTime movementStartTime;
    private Boolean movementActive;
    private LocalDate movementEndDate;
    private LocalTime movementEndTime;
    public Movement() { }
    public Movement(Integer movementID,
                    Status movementStatus,
                    Integer movementGood,
                    Integer movementVolume,
                    BigDecimal movementPrice,
                    Integer movementContact,
                    Integer movementDelivery,
                    LocalDate movementStartDate,
                    LocalTime movementStartTime,
                    Boolean movementActive,
                    LocalDate movementEndDate,
                    LocalTime movementEndTime) {
        this.movementID = movementID;
        this.movementStatus = movementStatus;
        this.movementGood = movementGood;
        this.movementVolume = movementVolume;
        this.movementPrice = movementPrice;
        this.movementContact = movementContact;
        this.movementDelivery = movementDelivery;
        this.movementStartDate = movementStartDate;
        this.movementStartTime = movementStartTime;
        this.movementActive = movementActive;
        this.movementEndDate = movementEndDate;
        this.movementEndTime = movementEndTime;
    }
    public void setMovementID(Integer movementID) {
        this.movementID = movementID;
    }
    public void setMovementStatus(Status movementStatus) {
        this.movementStatus = movementStatus;
    }
    public void setMovementGood(Integer movementGood) {
        this.movementGood = movementGood;
    }
    public void setMovementVolume(Integer movementVolume) {
        this.movementVolume = movementVolume;
    }
    public void setMovementPrice(BigDecimal movementPrice) {
        this.movementPrice = movementPrice;
    }
    public void setMovementContact(Integer movementContact) {
        this.movementContact = movementContact;
    }
    public void setMovementDelivery(Integer movementDelivery) {
        this.movementDelivery = movementDelivery;
    }
    public void setMovementStartDate(LocalDate movementStartDate) {
        this.movementStartDate = movementStartDate;
    }
    public void setMovementStartTime(LocalTime movementStartTime) {
        this.movementStartTime = movementStartTime;
    }
    public void setMovementActive(Boolean movementActive) {
        this.movementActive = movementActive;
    }
    public void setMovementEndDate(LocalDate movementEndDate) {
        this.movementEndDate = movementEndDate;
    }
    public void setMovementEndTime(LocalTime movementEndTime) {
        this.movementEndTime = movementEndTime;
    }
    public Integer getMovementID() {
        return movementID;
    }
    public Status getMovementStatus() {
        return movementStatus;
    }
    public Integer getMovementGood() {
        return movementGood;
    }
    public Integer getMovementVolume() {
        return movementVolume;
    }
    public BigDecimal getMovementPrice() {
        return movementPrice;
    }
    public Integer getMovementContact() {
        return movementContact;
    }
    public Integer getMovementDelivery() {
        return movementDelivery;
    }
    public LocalDate getMovementStartDate() {
        return movementStartDate;
    }
    public LocalTime getMovementStartTime() {
        return movementStartTime;
    }
    public Boolean getMovementActive() {
        return movementActive;
    }
    public LocalDate getMovementEndDate() {
        return movementEndDate;
    }
    public LocalTime getMovementEndTime() {
        return movementEndTime;
    }
}