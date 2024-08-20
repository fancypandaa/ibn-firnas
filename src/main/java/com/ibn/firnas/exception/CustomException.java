package com.ibn.firnas.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private HttpStatus statusCode;
    public CustomException(String message) {
        super(message);
    }
    public CustomException(String message,HttpStatus statusCode) {
        super(message);
        this.statusCode =statusCode;
    }
}
