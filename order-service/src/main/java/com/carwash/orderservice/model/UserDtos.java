package com.carwash.orderservice.model;


import lombok.Data;

@Data
public class UserDtos {
    private int userId;
    private String userName;
    private String fullName;
    private String phoneNo;
    private String email;
}
