package com.carwash.orderservice.model;

import com.mongodb.lang.NonNull;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDetails {

    private int orderId;
    private String userName;
    private String fullName;
    private String phoneNo;
    private String email;
    private String street;
    private String city;
    private int pincode;
    private String carBrand;
    private String model;
    private String color;
    private String packageName;
    private String washerName;
    private String washerFullName;
    private String washerphoneNo;
    private String washeremail;
    private double totalPrice;
    private String orderStatus;
    private Date placedOn;

}
