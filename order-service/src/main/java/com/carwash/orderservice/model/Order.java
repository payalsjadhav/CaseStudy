package com.carwash.orderservice.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";

    @Id
    private int orderId;
    @NonNull
    private String userName;
    @NonNull
    private String street;
    private String city;
    private int pincode;
    private String carBrand;
    private String model;
    private String color;
    @NonNull
    private String packageName;
    private String washerName;
    @NonNull
    private double totalPrice;
    @NonNull
    private String orderStatus;
    private Date placedOn;


}
