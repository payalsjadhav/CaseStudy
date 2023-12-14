package com.carwash.washerservice.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class Washer {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private int userId;
    private String userName;
    @NonNull
    private String fullName;
    private Gender gender;
    @NonNull
    private String phoneNo;
    private String email;
    @NonNull
    private String password;
    private int age;
    @NonNull
    private String role;
    private Boolean isActive;

}
