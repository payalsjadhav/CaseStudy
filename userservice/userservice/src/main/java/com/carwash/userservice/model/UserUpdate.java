package com.carwash.userservice.model;

import lombok.Data;

@Data
public class UserUpdate {

    private String userName;
    private String fullName;
    private String phoneNo;
    private String email;
}
