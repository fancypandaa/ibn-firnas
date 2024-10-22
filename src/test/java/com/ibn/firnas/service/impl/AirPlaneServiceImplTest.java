package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import com.ibn.firnas.dto.mapper.AirPlaneMapper;
import com.ibn.firnas.repostiories.AirPlaneRepository;
import com.ibn.firnas.repostiories.FlightRepository;
import com.ibn.firnas.service.AirPlaneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AirPlaneServiceImplTest {
    @Mock
    private AirPlaneRepository airPlaneRepository;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private AirPlaneMapper airPlaneMapper;
    private AirPlaneService airPlaneService;
    private
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        airPlaneService = new AirPlaneServiceImpl(airPlaneRepository,flightRepository,airPlaneMapper);
    }

    @Test
    void findAirPlaneById() {
        AirPlane airPlane= anAirPlane();
        when(airPlaneRepository.findById(anyLong())).thenReturn(Optional.of(airPlane));
        AirPlane found =airPlaneRepository.findById(anyLong()).get();
        verify(airPlaneRepository,times(1)).findById(anyLong());
        assertNotNull(found);
    }

    @Test
    void addNewAirPlane() {
        AirPlane airPlane = anAirPlane();
        when(airPlaneRepository.save(any(AirPlane.class))).thenReturn(anAirPlane());
        AirPlane created = airPlaneRepository.save(airPlane);
        verify(airPlaneRepository,times(1)).save(airPlane);
        assertNotNull(created);
        assertEquals(created.getAirline(),airPlane.getAirline());
    }

    @Test
    void updateAirPlaneInfo() {
        when(airPlaneRepository.findById(anyLong())).thenReturn(Optional.of(anAirPlane()));
        AirPlane exist = airPlaneRepository.findById(anyLong()).get();
        exist.setNumberOfSeats(100);
        exist.setTotalFlightHours(60);
        when(airPlaneRepository.save(any(AirPlane.class))).thenReturn(exist);
        AirPlane updated = airPlaneRepository.save(exist);
        verify(airPlaneRepository,times(1)).findById(anyLong());
        verify(airPlaneRepository,times(1)).save(updated);
        assertNotNull(updated);
        assertEquals(updated.getAirline(),exist.getAirline());
        assertEquals(anAirPlane().getNumberOfSeats().equals(updated.getNumberOfSeats()),false);
    }

    @Test
    void assignFlightToAirPlane() {
    }
    private static AirPlane anAirPlane(){
        AirPlane airPlane = new AirPlane();
        airPlane.setPlaneId(1L);
        airPlane.setCompanyName("DDD");
        airPlane.setPlaneName("XCCV");
        airPlane.setAirline("FFF");
        airPlane.setFuelTankCapacity(1212);
        airPlane.setNumberOfSeats(50);
        airPlane.setLastCheckIn(new Date());
        airPlane.setTotalFlightHours(55);
        return airPlane;
    }

}