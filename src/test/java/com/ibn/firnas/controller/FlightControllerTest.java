package com.ibn.firnas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.airCrew.FlightAllDTO;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.service.FlightService;
import com.ibn.firnas.utils.enums.Gender;
import com.ibn.firnas.utils.enums.TripCategory;
import com.ibn.firnas.utils.enums.TripType;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FlightControllerTest {
    private static final long flightId =1L;
    private static final long userId =1L;
    private static final long userIdI =2L;

    @Mock
    private FlightService flightService;
    private FlightController flightController;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        flightController = new FlightController(flightService);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    void getFlightAndCrewById() throws Exception {
        when(flightService.findFlightAndCrewById(anyLong())).thenReturn(anFlightAllDTO());
        mockMvc.perform(get("/api/aircrew/flight/flightCrew/{flightId}",flightId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId").value(1))
                .andExpect(jsonPath("$.userDetails",hasSize(1)))
                .andDo(print());
        verify(flightService,times(1)).findFlightAndCrewById(anyLong());

    }

    @Test
    void getFlightById() throws Exception {
        when(flightService.findFlightsById(anyLong())).thenReturn(anFlightDTO());
        mockMvc.perform(get("/api/aircrew/flight/{flightId}",flightId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId").value(1))
                .andDo(print());
        verify(flightService,times(1)).findFlightsById(anyLong());
    }

    @Test
    void createNewFlightWithCrew() throws Exception {
        when(flightService.createNewFlightWithCrew(any(FlightDTO.class))).thenReturn(anFlightDTO());
        mockMvc.perform(post("/api/aircrew/flight/flightCrew")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(anFlightDTO())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightId").value(1))
                .andExpect(jsonPath("$.userIds",hasSize(2)))
                .andDo(print());
        verify(flightService,times(1)).createNewFlightWithCrew(any(FlightDTO.class));
    }

    @Test
    void updateFlight() {

    }
    private static FlightDTO anFlightDTO(){
        val flightDto = FlightDTO.builder()
                .flightId(1L)
                .flightNumber("3VCS2")
                .airportLandingId("")
                .arrivalId("EGY")
                .departureId("UK")
                .details("EGY -> UK")
                .duration("3 HR:50 M")
                .flightNumber("JX22")
                .outbound_date(new Date())
                .return_date(new Date())
                .tripType(TripType.INTERNAL)
                .tripCategory(TripCategory.ROUND_TRIP)
                .userIds(List.of(userId,userIdI))
                .build();
        return flightDto;
    }
    private static FlightAllDTO anFlightAllDTO(){
        val flightDto = FlightAllDTO.builder()
                .flightId(1L)
                .flightNumber("3VCS2")
                .airportLandingId("")
                .arrivalId("EGY")
                .details("UK")
                .duration("3 HR:50 M")
                .flightNumber("JX22")
                .outbound_date(new Date())
                .return_date(new Date())
                .tripType(TripType.INTERNAL)
                .tripCategory(TripCategory.ROUND_TRIP)
                .userDetails(Set.of(userDetails()))
                .build();
        return flightDto;
    }
    public static UserDetailsDTO userDetails(){
        val userDetailsDTO = UserDetailsDTO.builder()
                .userId(userId)
                .firstName("ABC")
                .lastName("DEF")
                .address("bbb-334-vvc")
                .jobTitle("XXX")
                .dateOfBirth("01-31-1990")
                .gender(Gender.MALE)
                .totalFlightsHours(30L)
                .license("aa-bb-cc")
                .build();
        return userDetailsDTO;
    }
}