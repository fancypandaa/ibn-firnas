package com.ibn.firnas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.airCrew.LocationDTO;
import com.ibn.firnas.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import lombok.val;
import java.util.*;
@SpringBootTest
class LocationControllerTest {
    private static final Long locationId =1L;
    private static final long userId= 1L;
    @Mock
    private LocationService locationService;
    private LocationController locationController;
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        locationController = new LocationController(locationService);
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }

    @Test
    void findLocationById() throws Exception {
        when(locationService.findLocationById(anyLong())).thenReturn(anLocation());
        mockMvc.perform(get("/api/aircrew/location/{locationId}",locationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locationId").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.city").value("Paris"))
                .andDo(print());
        verify(locationService,times(1)).findLocationById(anyLong());
    }
    @Test
    void findAllLocationsByUserId() throws Exception {
        when(locationService.findAllLocationsByUserId(anyLong())).thenReturn(List.of(anLocation(),anLocationI()));
        mockMvc.perform(get("/api/aircrew/location/byUser/{userId}",userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
        verify(locationService,times(1)).findAllLocationsByUserId(anyLong());


    }
    @Test
    void createNewLocation() throws Exception {
        when(locationService.createNewLocationForUser(anyLong(),any(LocationDTO.class))).thenReturn(anLocation());
        mockMvc.perform(post("/api/aircrew/location/{userId}",userId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(anLocation())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.locationId").value(1))
                .andExpect(jsonPath("$.city").value("Paris"))
                .andDo(print());
        verify(locationService,times(1))
                .createNewLocationForUser(anyLong(),any(LocationDTO.class));

    }

    @Test
    void updateExistLocation() throws Exception {
        when(locationService.updateLocationDetails(anyLong(),any(LocationDTO.class)))
                .thenReturn(anLocation());
        mockMvc.perform(put("/api/aircrew/location/{userId}",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(anLocation())))
                .andExpect(status().isOk())
                .andDo(print());
        verify(locationService,times(1))
                .updateLocationDetails(anyLong(),any(LocationDTO.class));

    }
    private static LocationDTO anLocation(){
        val locationDto = LocationDTO.builder()
                .locationId(1L)
                .lat("33.00")
                .lng("29.88")
                .city("Paris")
                .country("France")
                .ipAddress("192.168.1.1")
                .time("2024-09-30T23:44:04.385411262Z")
                .isActive(Boolean.TRUE)
                .userId(1L)
                .build();
        return locationDto;
    }
    private static LocationDTO anLocationI(){
        val locationDto = LocationDTO.builder()
                .locationId(1L)
                .lat("34.00")
                .lng("26.88")
                .city("London")
                .country("England")
                .ipAddress("192.168.0.1")
                .time("2024-09-30T23:44:04.385411262Z")
                .isActive(Boolean.FALSE)
                .userId(1L)
                .build();
        return locationDto;
    }
}