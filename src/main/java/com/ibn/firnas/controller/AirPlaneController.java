package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import com.ibn.firnas.service.AirPlaneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AirPlaneController.AIRPLANE_URI)
public class AirPlaneController {
    private static Logger log = LoggerFactory.getLogger(FlightController.class);
    public static final String AIRPLANE_URI="/api/aircrew/plane";
    private final AirPlaneService airPlaneService;

    public AirPlaneController(AirPlaneService airPlaneService) {
        this.airPlaneService = airPlaneService;
    }
    @GetMapping("/{planeId}")
    public ResponseEntity<?> getAirPlaneById(@PathVariable Long planeId){
        AirPlaneDTO airPlaneDTO= airPlaneService.findAirPlaneById(planeId);
        return new ResponseEntity<>(airPlaneDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewAirPlane(@RequestBody AirPlaneDTO airPlaneDTO){
        AirPlaneDTO savedAirPlane = airPlaneService.addNewAirPlane(airPlaneDTO);
        return new ResponseEntity<>(savedAirPlane,HttpStatus.CREATED);
    }
    @PatchMapping("/{planeId}")
    public ResponseEntity<?> updateAirPlane(@PathVariable Long planeId,@RequestBody AirPlaneDTO airPlaneDTO){
        AirPlaneDTO updatedAirPlane = airPlaneService.updateAirPlaneInfo(planeId,airPlaneDTO);
        return new ResponseEntity<>(updatedAirPlane,HttpStatus.OK);
    }
}
