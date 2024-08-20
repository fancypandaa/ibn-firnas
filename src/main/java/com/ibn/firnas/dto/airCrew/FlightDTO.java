package com.ibn.firnas.dto.airCrew;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibn.firnas.utils.enums.TripCategory;
import com.ibn.firnas.utils.enums.TripType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public record FlightDTO(TripCategory tripCategory, TripType tripType, Boolean showHidden, String departureId,
                        String arrivalId, String duration, String details, HashMap<Integer,String> stops,
                        String airportTakeOff, String airportTakeOffId, String takeOffTime,
                        String airportLanding, String airportLandingId, String landingTime,
                        List<String> extensions, @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") Date outbound_date,@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  Date return_date, String flightNumber,
                        Date createdAt, Date updatedAt) {
}
