package com.ibn.firnas.dto.googleFlights;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
public record FlightsRecord(@JsonProperty("departure_airport") AirportDetails departure,
                            @JsonProperty("arrival_airport") AirportDetails arrivalTime,
                            Integer duration, String airplane, String airline,
                            @JsonProperty("airline_logo") String airlineLogo,
                            @JsonProperty("travel_class") String travelClass,
                            @JsonProperty("flight_number") String flightNumber,
                            String [] extensions,
                            @JsonProperty("ticket_also_sold_by") ArrayList<String> ticketAlsoSoldBy,
                            String legroom, boolean overnight,
                            @JsonProperty("often_delayed_by_over_30_min") boolean oftenDelayedByOver30min) {
}
