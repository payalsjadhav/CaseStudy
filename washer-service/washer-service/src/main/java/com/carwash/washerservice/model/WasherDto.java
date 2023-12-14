package com.carwash.washerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasherDto {

    private int userId;
    private String userName;
    private String fullName;
    private Gender gender;
    private String phoneNo;
    private String email;
    private int age;
    private Boolean isActive;

}
