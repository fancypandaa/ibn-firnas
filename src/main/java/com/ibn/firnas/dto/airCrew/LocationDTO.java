package com.ibn.firnas.dto.airCrew;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

public record LocationDTO(Long locationId,
                          @NotBlank(message = "Make sure to Set IP Address")
                          String ipAddress,
                          @NotBlank(message = "country cannot be null")
                          String country,
                          @NotBlank(message = "city cannot be null")
                          String city,
                          @NotBlank(message = "time cannot be null")
                          String time,
                          @NotBlank(message = "lat cannot be null")
                          String lat,
                          @NotBlank(message = "lng cannot be null")
                          String lng,
                          @NotBlank(message = "isActive cannot be null")
                          Boolean isActive,
                          @JsonInclude(value = JsonInclude.Include.NON_NULL)
                          Long userId
) {}
