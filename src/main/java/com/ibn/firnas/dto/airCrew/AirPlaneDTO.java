package com.ibn.firnas.dto.airCrew;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Date;
@Builder
public record AirPlaneDTO(
        Long planeId,
        @NotBlank(message = "companyName cannot be null")
        String companyName,
        @NotBlank(message = "planeName cannot be null")
        String planeName,
        @NotBlank(message = "planeName cannot be null")
        String airline,
        String model,
        String airlineLogo,
        @Max(100000)
        Integer totalFlightHours,
        Integer fuelTankCapacity,
        Date lastCheckIn,
        @Min(3)
        Integer numberOfSeats,
        Date createdAt,
        Date updateAt,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long flightId
) {
}


