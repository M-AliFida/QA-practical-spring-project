package com.app.SuperMarketSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundException {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> userNotFound(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}