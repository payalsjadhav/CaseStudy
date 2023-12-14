package com.carwash.packageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PackageExceptionHandler  {

	@ExceptionHandler(value=PackageNotFoundException .class)
	public ResponseEntity<String> exception(PackageNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= PackageAlreadyExistsException.class)
	public ResponseEntity<String> exception(PackageAlreadyExistsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
