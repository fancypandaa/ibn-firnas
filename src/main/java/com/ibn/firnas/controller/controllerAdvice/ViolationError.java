package com.ibn.firnas.controller.controllerAdvice;

import lombok.AllArgsConstructor;

public record ViolationError(String fieldName, String message) {

    public ViolationError(String fieldName, String message) {
        this.fieldName=fieldName;
        this.message=message;
    }
}
