package com.ibn.firnas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import com.ibn.firnas.service.AirPlaneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import lombok.val;
import java.util.*;
@SpringBootTest
class AirPlaneControllerTest {
    private static final Long airPlaneId =1L;
    private static final long flightId= 1L;
    @Mock
    private AirPlaneService airPlaneService;
    private AirPlaneController airPlaneController;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        airPlaneController = new AirPlaneController(airPlaneService);
        mockMvc = MockMvcBuilders.standaloneSetup(airPlaneController).build();
    }

    @Test
    void getAirPlaneById() throws Exception {
        when(airPlaneService.findAirPlaneById(anyLong())).thenReturn(anAirPlan());
        mockMvc.perform(get("/api/aircrew/plane/{airPlaneId}",airPlaneId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planeId").value(1))
                .andExpect(jsonPath("$.flightId").value(1))
                .andDo(print());
        verify(airPlaneService,times(1)).findAirPlaneById(anyLong());
    }

    @Test
    void createNewAirPlane() throws Exception {
        when(airPlaneService.addNewAirPlane(any(AirPlaneDTO.class))).thenReturn(anAirPlan());
        mockMvc.perform(post("/api/aircrew/plane")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(anAirPlan())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.planeId").value(1))
                .andDo(print());
        verify(airPlaneService,times(1)).addNewAirPlane(any(AirPlaneDTO.class));
    }

    @Test
    void updateAirPlane() throws Exception {
        when(airPlaneService.updateAirPlaneInfo(anyLong(),any(AirPlaneDTO.class))).thenReturn(anAirPlan());
        mockMvc.perform(patch("/api/aircrew/plane/{airPlaneId}",airPlaneId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(anAirPlanI())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planeId").value(1))
                .andDo(print());
        verify(airPlaneService,times(1)).updateAirPlaneInfo(anyLong(),any(AirPlaneDTO.class));

    }
    private static AirPlaneDTO anAirPlan(){
        val airPlane = AirPlaneDTO.builder()
                .planeId(airPlaneId)
                .planeName("XXX")
                .airline("ABC")
                .model("V00")
                .companyName("BVB")
                .numberOfSeats(300)
                .fuelTankCapacity(20)
                .totalFlightHours(100)
                .lastCheckIn(new Date())
                .airline("VVV")
                .airlineLogo("https://abc.com")
                .flightId(flightId)
                .createdAt(new Date())
                .build();
        return airPlane;
    }
    private static AirPlaneDTO anAirPlanI(){
        val airPlane = AirPlaneDTO.builder()
                .planeId(airPlaneId)
                .planeName("XXX")
                .airline("ABC")
                .model("V00")
                .companyName("BVB")
                .numberOfSeats(200)
                .fuelTankCapacity(20)
                .totalFlightHours(150)
                .lastCheckIn(new Date())
                .airline("VVV")
                .airlineLogo("https://abc.com")
                .flightId(flightId)
                .createdAt(new Date())
                .build();
        return airPlane;
    }
}