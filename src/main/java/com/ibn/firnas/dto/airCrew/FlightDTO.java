package com.ibn.firnas.dto.airCrew;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibn.firnas.utils.enums.TripCategory;
import com.ibn.firnas.utils.enums.TripType;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public record FlightDTO(
        @NotNull
        TripCategory tripCategory,
        TripType tripType,
        Boolean showHidden,
        @NotBlank(message = "departureId cannot be null")
        String departureId,
        @NotBlank(message = "arrivalId cannot be null")
        String arrivalId,
        @NotBlank(message = "duration cannot be null")
        String duration,
        String details,
        HashMap<Integer,String> stops,
        String airportTakeOff,
        String airportTakeOffId,
        String takeOffTime,
        String airportLanding,
        String airportLandingId,
        String landingTime,
        List<String> extensions,
        @NotNull
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        Date outbound_date,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        Date return_date,
        String flightNumber,
        Date createdAt,
        Date updatedAt) {
}
