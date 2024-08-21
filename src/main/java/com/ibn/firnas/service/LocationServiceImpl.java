package com.ibn.firnas.service;

import com.ibn.firnas.domain.Location;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.LocationDTO;
import com.ibn.firnas.dto.mapper.LocationMapper;
import com.ibn.firnas.exception.CustomException;
import com.ibn.firnas.repostiories.LocationRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.*;
import java.util.Optional;
@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final UserDetailsRepository userDetailsRepository;
    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper, UserDetailsRepository userDetailsRepository) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public LocationDTO findLocationById(Long locationId) throws CustomException{
        Optional<Location> optionalLocation = locationRepository.findById(locationId);
        if(!optionalLocation.isPresent()){
            throw new CustomException("Location Not Found");
        }
        return locationMapper.locationToLocationDTO(optionalLocation.get());
    }
    @Override
    public List<LocationDTO> findAllLocationsByUserId(Long userId) throws CustomException {
        List<LocationDTO> locationDTOS=locationRepository
                .findLocationsByUserDetails_UserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(locationMapper::locationToLocationDTO)
                .collect(Collectors.toList());
        if(locationDTOS.size() < 1){
            throw new CustomException("Location Not Found for user "+userId);
        }
        return locationDTOS;
    }
    @Override
    public LocationDTO createNewLocationForUser(Long userId,LocationDTO locationDTO) throws CustomException {
        Optional<UserDetails> userDetails = userDetailsRepository.findById(userId);
        if(!userDetails.isPresent()){
            throw new CustomException("User Not found");
        }
        Location newLocation = locationMapper.locationDTOtoLocation(locationDTO);
        newLocation.setUserDetails(userDetails.get());
        Location location = locationRepository.save(newLocation);
        return locationMapper.locationToLocationDTO(location);
    }
    @Override
    public LocationDTO updateLocationDetails(Long locationId, LocationDTO locationDTO) throws CustomException {
        if(!locationRepository.existsById(locationId)){
            throw new CustomException("Custom Not Found", HttpStatus.NOT_FOUND);
        }
        Location location = locationMapper.locationDTOtoLocation(locationDTO);
        location.setLocationId(locationId);
        Location updatedLocation = locationRepository.save(location);
        return locationMapper.locationToLocationDTO(updatedLocation);
    }
}
