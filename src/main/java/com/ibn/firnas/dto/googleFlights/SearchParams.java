package com.ibn.firnas.dto.googleFlights;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchParams (String engine, String hl, String gl, String departure_id,
                            String arrival_id, String outbound_date,
                            String return_date, String currency,
                            String type,int travel_class,boolean show_hidden) {
}
