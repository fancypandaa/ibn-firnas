package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.exception.CustomException;
import com.ibn.firnas.service.FlightService;
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

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping("/{flightId}")
    public ResponseEntity<? super FlightDTO> getFlightById(@PathVariable Long flightId){
        try {
            FlightDTO flightDTO= flightService.findFlightsById(flightId);
            return new ResponseEntity<>(flightDTO, HttpStatus.OK);
        }
        catch (CustomException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping
    public ResponseEntity<?> createNewFlight(@RequestBody FlightDTO flightDTO){
        try {
            FlightDTO savedFlight = flightService.createNewFlight(flightDTO);
            return new ResponseEntity<>(flightDTO,HttpStatus.CREATED);
        }
        catch (CustomException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/{flightId}")
    public ResponseEntity<? super FlightDTO> updateFlight(@PathVariable Long flightId,@RequestBody FlightDTO flightDTO){
        try {
            FlightDTO updatedFlight= flightService.updateExistsFlight(flightId,flightDTO);
            return new ResponseEntity<>(updatedFlight,HttpStatus.OK);
        }
        catch (CustomException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
