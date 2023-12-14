package com.carwash.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value=NotFoundException .class)
    public ResponseEntity<String> exception(NotFoundException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value=OrderAlreadyExistsException .class)
    public ResponseEntity<String> exception(OrderAlreadyExistsException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

}
