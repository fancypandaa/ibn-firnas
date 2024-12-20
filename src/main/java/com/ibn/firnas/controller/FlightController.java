package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.FlightAllDTO;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.service.FlightService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping(FlightController.FLIGHT_URI)
public class FlightController {
    private static Logger log = LoggerFactory.getLogger(FlightController.class);
    public static final String FLIGHT_URI="/api/aircrew/flight";
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping("/flightCrew/{flightId}")
    public ResponseEntity<? super FlightAllDTO> getFlightAndCrewById(@PathVariable Long flightId){
        FlightAllDTO flightDTO= flightService.findFlightAndCrewById(flightId);
        return new ResponseEntity<>(flightDTO, HttpStatus.OK);
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
    @PostMapping("/flightCrew")
    public ResponseEntity<?> createNewFlightWithCrew(@Valid @RequestBody FlightDTO flightDTO){
        FlightDTO savedFlight = flightService.createNewFlightWithCrew(flightDTO);
        return new ResponseEntity<>(savedFlight,HttpStatus.CREATED);
    }
    @PostMapping("/flightCrew/assign/{flightId}")
    public ResponseEntity<?> assignCrewMemberToFlight(@PathVariable Long flightId, @RequestBody List<Long> userIds){
        FlightDTO savedFlight = flightService.assignFlightCrew(flightId,userIds);
        return new ResponseEntity<>(savedFlight,HttpStatus.CREATED);
    }
    @PatchMapping("/{flightId}")
    public ResponseEntity<? super FlightDTO> updateFlight(@PathVariable Long flightId,@Valid @RequestBody FlightDTO flightDTO){
        FlightDTO updatedFlight= flightService.updateExistsFlight(flightId,flightDTO);
        return new ResponseEntity<>(updatedFlight,HttpStatus.OK);
    }
}
