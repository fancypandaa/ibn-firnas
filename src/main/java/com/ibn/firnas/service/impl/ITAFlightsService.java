package com.ibn.firnas.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.googleFlights.AllFlights;
import com.ibn.firnas.dto.googleFlights.SearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.ibn.firnas.service.impl.ITAFlightsHelper.*;
@Service
public class ITAFlightsService {
    @Value("${keys.ati_api_key}")
    protected String apiKey;
    private static Logger log = LoggerFactory.getLogger(ITAFlightsService.class);
    private final RestTemplate restTemplate;
    public ITAFlightsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    @Async
    public CompletableFuture<AllFlights> getFlightsOptions(SearchParams flightsParams) throws InterruptedException{
        log.info("your flight options will arrive soon ....");

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        Map<String,String> params= returnFlightsParams(flightsParams,apiKey);
        log.info(params.toString());

        HttpEntity<Object> response =restTemplate.exchange(helperGetFlights(), HttpMethod.GET,entity,Object.class,params);

        ObjectMapper objectMapper= new ObjectMapper();
        Object res=response.getBody();
        AllFlights flights=objectMapper.convertValue(res, AllFlights.class);
        log.info(Arrays.stream(flights.flights()).collect(Collectors.toList()).toString());
        return CompletableFuture.completedFuture(flights);
    }

}
