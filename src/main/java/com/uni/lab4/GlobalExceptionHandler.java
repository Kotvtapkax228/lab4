package com.uni.lab4;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<ApiError> handleDoctorsException(BookNotFoundException e){
        ApiError error = new ApiError(e.getMessage());
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<ApiError>(error, status);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleNullPointerException(Exception e) {
        ApiError error = new ApiError(e.getMessage());
        error.setException("Exception: " + e.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(error, status);
    }
}
