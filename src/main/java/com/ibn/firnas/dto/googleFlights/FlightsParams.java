package com.ibn.firnas.dto.googleFlights;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FlightsParams(String engine, String hl,String gl, @JsonProperty("departure_id") String departureId,
                           @JsonProperty("arrival_id") String arrivalId, @JsonProperty("outbound_date") String outboundDate,
                           @JsonProperty("return_date") String returnDate, String currency) {
}
