package com.carwash.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value =InvalidAgeException.class)
	public ResponseEntity<String> handleInvalidAgeException (Exception exception) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	@ExceptionHandler(value = InvalidEmailException.class)
	public ResponseEntity<String> handleInvalidEmailException(Exception exception) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	@ExceptionHandler(value = PhoneNoException.class)
	public ResponseEntity<String> handlePhoneNoException(Exception exception) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
	}

	@ExceptionHandler(value = UserNameException.class)
	public ResponseEntity<String> handleUserNameException(Exception exception) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
    }

	@ExceptionHandler(value = NameException.class)
	public ResponseEntity<String> handleNameException(Exception exception) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
	}

	@ExceptionHandler(value = PasswordException.class)
	public ResponseEntity<String> handlePasswordException(Exception exception) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
	}

	@ExceptionHandler(value = InvalidRoleException.class)
	public ResponseEntity<String> handleInvalidRoleException(Exception exception) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
	}


}