package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.LocationDTO;
import com.ibn.firnas.exception.CustomException;
import com.ibn.firnas.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping(LocationController.LOCATION_URI)
public class LocationController {
    public static final String LOCATION_URI="/api/aircrew/location";
    private final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @GetMapping("/{locationId}")
    public ResponseEntity<? super LocationDTO> findLocationById(@PathVariable Long locationId){
        try {
            LocationDTO locationDTO=locationService.findLocationById(locationId);
            return new ResponseEntity<>(locationDTO, HttpStatus.OK);
        }
        catch (CustomException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<? super LocationDTO> findLocationsByUserId(@PathVariable Long userId){
        try {
            List<LocationDTO> locationDTOList=locationService.findAllLocationsByUserId(userId);
            return new ResponseEntity<>(locationDTOList, HttpStatus.OK);
        }
        catch (CustomException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<? super LocationDTO> createNewLocation(@RequestBody LocationDTO locationDTO){
        try {
            LocationDTO savedLocationDTO=locationService.createNewLocationForUser(locationDTO);
            return new ResponseEntity<>(savedLocationDTO, HttpStatus.OK);
        }
        catch (CustomException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<? super LocationDTO> updateExistLocation(@PathVariable Long locationId,@RequestBody LocationDTO locationDTO){
        try {
            LocationDTO savedLocationDTO=locationService.updateLocationDetails(locationId,locationDTO);
            return new ResponseEntity<>(locationDTO, HttpStatus.OK);
        }
        catch (CustomException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
