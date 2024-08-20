package com.ibn.firnas.dto.googleFlights;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarbonEmissions(@JsonProperty("this_flight") Integer thisFlight,
                              @JsonProperty("typical_for_this_route") Integer typicalForThisRoute,
                              @JsonProperty("difference_percent") Integer differentPercent) {
}
