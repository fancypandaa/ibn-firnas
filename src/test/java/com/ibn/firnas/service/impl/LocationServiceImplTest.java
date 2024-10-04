package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.Location;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.mapper.LocationMapper;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.repostiories.LocationRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.service.LocationService;
import com.ibn.firnas.utils.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class LocationServiceImplTest {
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private LocationMapper locationMapper;
    @Mock
    private UserDetailsRepository userDetailsRepository;
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        locationService = new LocationServiceImpl(locationRepository,locationMapper,userDetailsRepository);
    }

    @Test
    void findLocationById()  {
        Location location=getLocation();
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(location));
        Location newLoc= locationRepository.findById(anyLong()).get();
        locationService.findLocationById(anyLong());
        verify(locationRepository,times(2)).findById(anyLong());
        assertNotNull(newLoc);
        assertEquals(newLoc.getCountry(), location.getCountry());
    }

    @Test
    void findAllLocationsByUserId() {
        List<Location> locationList=List.of(getLocation(),getLocationI());
        when(locationRepository.findLocationsByUserDetails_UserIdOrderByCreatedAtDesc(anyLong()))
                .thenReturn(locationList);
        locationService.findAllLocationsByUserId(anyLong());
        verify(locationRepository,times(1))
                .findLocationsByUserDetails_UserIdOrderByCreatedAtDesc(anyLong());
        verify(locationMapper,times(2)).
                locationToLocationDTO(any(Location.class));
        assertEquals(locationList.size(),2);
        assertNotNull(locationList.get(1));
    }

    @Test
    void createNewLocationForUser() {
        Location location=getLocation();
        when(userDetailsRepository.findById(anyLong())).thenReturn(Optional.of(newUserDetails()));
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        Optional<UserDetails> userDetails=userDetailsRepository.findById(anyLong());
        Location location1= locationRepository.save(location);
        location1.setUserDetails(userDetails.get());
        verify(locationRepository,times(1)).save(location1);
        verify(userDetailsRepository,times(1)).findById(anyLong());
        assertNotNull(location1);
        assertNotNull(userDetails.get());
        assertEquals(location1.getIpAddress(),location.getIpAddress());
    }

    @Test
    void updateLocationDetails() {
        Location location=getLocation();
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(location));
        Optional<Location> found=locationRepository.findById(anyLong());
        found.get().setIpAddress("192.168.0.3");
        found.get().setTime("23:20:00");
        when(locationRepository.save(location)).thenReturn(location);
        Location updated = locationRepository.save(found.get());
        verify(locationRepository,times(1)).findById(anyLong());
        verify(locationRepository,times(1)).save(any(Location.class));
        assertNotNull(updated);
        assertNotEquals(updated.getIpAddress(),getLocation().getIpAddress());
        assertNotEquals(updated.getTime(),getLocation().getTime());
        assertEquals(updated.getCountry(),getLocation().getCountry());
    }
    private Location getLocation(){
        Location location=new Location();
        location.setIpAddress("192.168.1.1");
        location.setCountry("GER");
        location.setLng("34.4444");
        location.setLat("35.555");
        location.setTime("22:25:00");
        location.setIsActive(true);
        location.setCreatedAt(new Date());
        location.setUpdateAt(new Date());
        location.setUserDetails(newUserDetails());
        return location;
    }
    private Location getLocationI(){
        Location location=new Location();
        location.setIpAddress("192.168.1.2");
        location.setCountry("GER");
        location.setLng("36.4444");
        location.setLat("35.555");
        location.setTime("22:25:00");
        location.setIsActive(true);
        location.setCreatedAt(new Date());
        location.setUpdateAt(new Date());
        location.setUserDetails(newUserDetails());
        return location;
    }
    private static UserDetails newUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(2L);
        userDetails.setFirstName("abc");
        userDetails.setLastName("def");
        userDetails.setAddress("aa-bb-cc");
        userDetails.setLicense("bbb-334-gdgd");
        userDetails.setGender(Gender.FEMALE);
        userDetails.setDateOfBirth("01-31-1990");
        userDetails.setJobTitle("Pilot");
        userDetails.setTotalFlightsHours(16L);
        return userDetails;
    }
}