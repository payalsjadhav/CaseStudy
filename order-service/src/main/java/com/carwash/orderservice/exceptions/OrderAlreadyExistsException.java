package com.carwash.orderservice.exceptions;

public class OrderAlreadyExistsException extends RuntimeException {

    public OrderAlreadyExistsException(String messege){
        super(messege);
    }

}
