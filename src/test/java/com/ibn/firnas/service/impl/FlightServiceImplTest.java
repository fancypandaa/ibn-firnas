package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.dto.mapper.FlightMapper;
import com.ibn.firnas.repostiories.FlightRepository;
import com.ibn.firnas.service.FlightService;
import com.ibn.firnas.utils.enums.TripCategory;
import com.ibn.firnas.utils.enums.TripType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@SpringBootTest
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private FlightMapper flightMapper;
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        flightService = new FlightServiceImpl(flightRepository,flightMapper);
    }

    @Test
    void findFlightsById() {
        Flight flight = anFlightEntity();
        when(flightRepository.findById(anyLong())).thenReturn(Optional.of(flight));
        Flight existFlight = flightRepository.findById(anyLong()).get();
        flightService.findFlightsById(anyLong());
        verify(flightRepository,times(2)).findById(anyLong());
        assertNotNull(existFlight);
    }

    @Test
    void createNewFlight() {
        Flight flight = anFlightEntity();
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        Flight existFlight = flightRepository.save(flight);
        verify(flightRepository,times(1)).save(any());
        assertNotNull(existFlight);
        assertEquals(flight.getTripType(),existFlight.getTripType());
    }

    @Test
    void updateExistsFlight() {
        when(flightRepository.findById(anyLong())).thenReturn(Optional.of(anFlightEntity()));
        Flight existFlight = flightRepository.findById(anyLong()).get();
        existFlight.setDuration("3 HR 30 M");
        existFlight.setTripType(TripType.INTERNAL);
        when(flightRepository.save(any(Flight.class))).thenReturn(existFlight);
        Flight updatedFlight = flightRepository.save(existFlight);
        verify(flightRepository,times(1)).findById(anyLong());
        verify(flightRepository,times(1)).save(any());
        assertNotNull(updatedFlight);
        assertNotEquals(updatedFlight.getDuration(),anFlightEntity().getDuration());
        assertEquals(updatedFlight.getFlightNumber(),existFlight.getFlightNumber());
    }
    private static Flight anFlightEntity(){
        Flight flight= new Flight();
        flight.setFlightId(1L);
        flight.setFlightNumber("XXX");
        flight.setTripType(TripType.PRIVATE);
        flight.setTakeOffTime("11:00");
        flight.setLandingTime("14:00");
        flight.setTripCategory(TripCategory.ROUND_TRIP);
        flight.setAirportLanding("XYZ");
        flight.setAirportLandingId("CF1");
        flight.setAirportTakeOff("ABC");
        flight.setAirportTakeOffId("CD2");
        flight.setDuration("2 HR 30 M");
        flight.setOutbound_date(new Date());
        flight.setReturn_date(new Date());
        return flight;
    }
}