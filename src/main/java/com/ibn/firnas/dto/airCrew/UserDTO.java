package com.ibn.firnas.dto.airCrew;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.Date;
@Builder
public record UserDTO(Long userId,
                      @NotBlank(message = "User name cannot be null")
                      String username,
                      String email,
                      @Size(min = 10,max = 30, message = "password should be 8 up to 30 characters")
                      String password,
                      Date createdAt,
                      Date updateAt) {
}
