package com.ibn.firnas.dto.googleFlights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AllFlights(@JsonProperty("search_parameters") FlightsParams searchParams,@JsonProperty("best_flights") BestFlight [] flights,
                         @JsonProperty("other_flights") BestFlight [] otherFlights,
                         @JsonProperty("price_insights") PriceInsights priceInsights) {
}
