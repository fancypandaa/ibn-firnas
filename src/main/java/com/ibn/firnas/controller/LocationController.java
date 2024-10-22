package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.LocationDTO;
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
        LocationDTO locationDTO=locationService.findLocationById(locationId);
        return new ResponseEntity<>(locationDTO, HttpStatus.OK);
    }
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<? super LocationDTO> findLocationsByUserId(@PathVariable Long userId){
        List<LocationDTO> locationDTOList=locationService.findAllLocationsByUserId(userId);
        return new ResponseEntity<>(locationDTOList, HttpStatus.OK);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<? super LocationDTO> createNewLocation(@PathVariable Long userId,@RequestBody LocationDTO locationDTO){
        LocationDTO savedLocationDTO=locationService.createNewLocationForUser(userId,locationDTO);
        return new ResponseEntity<>(savedLocationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<? super LocationDTO> updateExistLocation(@PathVariable Long locationId,@RequestBody LocationDTO locationDTO){
        LocationDTO savedLocationDTO=locationService.updateLocationDetails(locationId,locationDTO);
        return new ResponseEntity<>(savedLocationDTO, HttpStatus.OK);
    }
}
