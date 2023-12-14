package com.carwash.orderservice.exceptions;

public class UserNameException extends RuntimeException {

    public UserNameException(String messege){
        super(messege);
    }
}
