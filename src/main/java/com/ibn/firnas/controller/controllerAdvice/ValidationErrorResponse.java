package com.ibn.firnas.controller.controllerAdvice;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
public class ValidationErrorResponse {
    private List<ViolationError> violations = new ArrayList<>();
}
