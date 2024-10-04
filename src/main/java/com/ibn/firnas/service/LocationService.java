package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.LocationDTO;
import com.ibn.firnas.exception.CustomNotFoundException;

import java.util.List;

public interface LocationService {
    LocationDTO findLocationById(Long locationId);
    List<LocationDTO> findAllLocationsByUserId(Long userId);
    LocationDTO createNewLocationForUser(Long userId,LocationDTO locationDTO);
    LocationDTO updateLocationDetails(Long locationId,LocationDTO locationDTO);
}
