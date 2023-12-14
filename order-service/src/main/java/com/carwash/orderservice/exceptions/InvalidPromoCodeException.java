package com.carwash.orderservice.exceptions;

public class InvalidPromoCodeException extends RuntimeException {

    public InvalidPromoCodeException(String messege){
        super(messege);
    }

}
