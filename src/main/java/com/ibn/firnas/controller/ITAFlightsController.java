package com.ibn.firnas.controller;

import com.ibn.firnas.dto.googleFlights.AllFlights;
import com.ibn.firnas.dto.googleFlights.SearchParams;
import com.ibn.firnas.service.impl.ITAFlightsService;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.*;
@RestController
@RequestMapping("/api/ITA")
public class ITAFlightsController {
    private static Logger log = LoggerFactory.getLogger(ITAFlightsController.class);
    private final ITAFlightsService itaFlightsService;

    public ITAFlightsController(ITAFlightsService itaFlightsService) {
        this.itaFlightsService = itaFlightsService;
    }
    @PostMapping
    public void searchFlights(@RequestBody SearchParams searchParams) throws InterruptedException, ExecutionException{
        log.info("searching start");
        CompletableFuture<AllFlights> flightsCompletableFuture=itaFlightsService.getFlightsOptions(searchParams);
    }

}
