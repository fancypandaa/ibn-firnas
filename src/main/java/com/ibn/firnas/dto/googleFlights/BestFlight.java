package com.ibn.firnas.dto.googleFlights;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BestFlight(@JsonProperty("flights") FlightsRecord [] flights,
                         @JsonProperty("layovers") Layovers [] layovers,
                         @JsonProperty("total_duration") Integer totalDuration,
                         @JsonProperty("carbon_emissions") CarbonEmissions carbonEmissions,
                         Integer price, String type,
                         @JsonProperty("airline_logo") String airlineLogo,
                         String [] extensions,
                         @JsonProperty("departure_token") String departureToken,
                         @JsonProperty("booking_token") String bookingToken) {
}
