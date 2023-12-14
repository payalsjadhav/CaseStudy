package com.carwash.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userName;
    private String fullName;
    private String phoneNo;
    private String email;
    private String password;
}
