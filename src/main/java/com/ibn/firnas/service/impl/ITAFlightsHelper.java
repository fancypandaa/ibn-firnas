package com.ibn.firnas.service.impl;

import com.ibn.firnas.dto.googleFlights.SearchParams;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
public class ITAFlightsHelper {


    protected static String helperGetFlights(){
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://serpapi.com/search.json")
                .queryParam("engine", "{engine}")
                .queryParam("departure_id", "{departure_id}")
                .queryParam("arrival_id", "{arrival_id}")
                .queryParam("outbound_date", "{outbound_date}")
                .queryParam("return_date", "{return_date}")
                .queryParam("currency", "{currency}")
                .queryParam("hl", "{hl}")
                .queryParam("api_key", "{api_key}")
                .encode()
                .toUriString();
        return urlTemplate;
    }
    protected static Map<String,String> returnFlightsParams(SearchParams flightsParams,String apiKey){
        Map<String, String> parameter = new HashMap<>();
        parameter.put("engine", "google_flights");
        parameter.put("departure_id", flightsParams.departure_id());
        parameter.put("arrival_id", flightsParams.arrival_id());
        parameter.put("outbound_date", flightsParams.outbound_date());
        parameter.put("return_date", flightsParams.return_date());
        parameter.put("currency", flightsParams.currency());
        parameter.put("hl", flightsParams.hl());
        parameter.put("api_key", apiKey);
        return parameter;
    }

}
