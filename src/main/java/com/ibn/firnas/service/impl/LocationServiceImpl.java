package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.Location;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.LocationDTO;
import com.ibn.firnas.dto.mapper.LocationMapper;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.repostiories.LocationRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.*;
import java.util.Optional;
@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final UserDetailsRepository userDetailsRepository;
    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper, UserDetailsRepository userDetailsRepository) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public LocationDTO findLocationById(Long locationId) {
        Optional<Location> optionalLocation = locationRepository.findById(locationId);
        if(!optionalLocation.isPresent()){
            throw new CustomNotFoundException("Location Not Found");
        }
        return locationMapper.locationToLocationDTO(optionalLocation.get());
    }
    @Override
    public List<LocationDTO> findAllLocationsByUserId(Long userId) {
        List<LocationDTO> locationDTOS=locationRepository
                .findLocationsByUserDetails_UserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(locationMapper::locationToLocationDTO)
                .collect(Collectors.toList());
        if(locationDTOS.size() < 1){
            throw new CustomNotFoundException("Location Not Found for user "+userId);
        }
        return locationDTOS;
    }
    @Override
    public LocationDTO createNewLocationForUser(Long userId,LocationDTO locationDTO) {
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if(!userDetails.isPresent()){
            throw new CustomNotFoundException("User Not found");
        }
        Location newLocation = locationMapper.locationDTOtoLocation(locationDTO);
        newLocation.setUserDetails(userDetails.get());
        Location location = locationRepository.save(newLocation);
        return locationMapper.locationToLocationDTO(location);
    }
    @Override
    public LocationDTO updateLocationDetails(Long locationId, LocationDTO locationDTO) {
        if(!locationRepository.existsById(locationId)){
            throw new CustomNotFoundException("Custom Not Found");
        }
        Location location = locationMapper.locationDTOtoLocation(locationDTO);
        location.setLocationId(locationId);
        Location updatedLocation = locationRepository.save(location);
        return locationMapper.locationToLocationDTO(updatedLocation);
    }
}
