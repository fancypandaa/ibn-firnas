package com.ibn.firnas.controller.controllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@Configuration
@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler(value = {HttpMessageNotReadableException.class,ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException ex){
        ValidationErrorResponse error = new ValidationErrorResponse();
        for(ConstraintViolation violation: ex.getConstraintViolations()){
            error.getViolations().
                    add(new ViolationError(violation.getPropertyPath().toString(),violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ValidationErrorResponse error = new ValidationErrorResponse();
        for(FieldError fieldError: e.getBindingResult().getFieldErrors()){
            error.getViolations().add(new ViolationError(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        return error;
    }
}
