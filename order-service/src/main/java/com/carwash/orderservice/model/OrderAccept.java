package com.carwash.orderservice.model;

public class OrderAccept {
    private int OrderId;
    private String washerName;

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getWasherName() {
        return washerName;
    }

    public void setWasherName(String washerName) {
        this.washerName = washerName;
    }
}
