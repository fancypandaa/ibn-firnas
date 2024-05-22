package com.flights.book.dto.model;

import lombok.Data;

@Data
public class AuthDTO {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String role;
}
