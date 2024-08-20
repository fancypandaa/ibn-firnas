package com.ibn.firnas.service;

import com.ibn.firnas.domain.Location;
import com.ibn.firnas.dto.airCrew.LocationDTO;
import com.ibn.firnas.exception.CustomException;

import java.util.List;

public interface LocationService {
    LocationDTO findLocationById(Long locationId) throws CustomException;
    List<LocationDTO> findAllLocationsByUserId(Long userId) throws CustomException;

    LocationDTO createNewLocationForUser(LocationDTO locationDTO) throws CustomException;
    LocationDTO updateLocationDetails(Long locationId,LocationDTO locationDTO) throws CustomException;
}
