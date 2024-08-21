package com.ibn.firnas.dto.airCrew;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocationDTO(Long locationId,
                          @NotBlank(message = "Make sure to Set IP Address")
                          @Pattern(regexp="^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
                          String ipAddress,
                          @NotBlank(message = "country cannot be null")
                          String country,
                          @NotBlank(message = "time cannot be null")
                          String time,

                          @NotBlank(message = "lat cannot be null")
                          String lat,

                          @NotBlank(message = "lng cannot be null")
                          String lng,

                          @NotBlank(message = "isActive cannot be null")
                          Boolean isActive
) {

    public LocationDTO(String ipAddress, String country, String time, String lat, String lng, Boolean isActive) {
        this(null, ipAddress, country, time, lat, lng, isActive);
    }
}
