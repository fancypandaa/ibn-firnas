package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.service.FlightService;
import com.ibn.firnas.utils.ErrorMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(FlightController.FLIGHT_URI)
public class FlightController {
    private static Logger log = LoggerFactory.getLogger(FlightController.class);
    public static final String FLIGHT_URI="/api/aircrew/flight";
    private final FlightService flightService;
    private final ErrorMapper errorMapper;

    public FlightController(FlightService flightService, ErrorMapper errorMapper) {
        this.flightService = flightService;
        this.errorMapper = errorMapper;
    }
    @GetMapping("/{flightId}")
    public ResponseEntity<? super FlightDTO> getFlightById(@PathVariable Long flightId){
        FlightDTO flightDTO= flightService.findFlightsById(flightId);
        return new ResponseEntity<>(flightDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewFlight(@Valid @RequestBody FlightDTO flightDTO){
            FlightDTO savedFlight = flightService.createNewFlight(flightDTO);
            return new ResponseEntity<>(savedFlight,HttpStatus.CREATED);
    }
    @PatchMapping("/{flightId}")
    public ResponseEntity<? super FlightDTO> updateFlight(@PathVariable Long flightId,@Valid @RequestBody FlightDTO flightDTO){
        FlightDTO updatedFlight= flightService.updateExistsFlight(flightId,flightDTO);
        return new ResponseEntity<>(updatedFlight,HttpStatus.OK);
    }
}
