package com.ibn.firnas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.locator.GeoIP;
import com.ibn.firnas.locator.RawDBDemoGeoIPLocationService;
import com.ibn.firnas.locator.events.GeoEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GeoIPControllerTest {
    @Mock
    private RawDBDemoGeoIPLocationService rawDBDemoGeoIPLocationService;
    @Mock
    private GeoEventPublisher geoEventPublisher;
    private MockMvc mockMvc;
    private GeoIPController geoIPController;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        geoIPController=new GeoIPController(geoEventPublisher);
        mockMvc = MockMvcBuilders.standaloneSetup(geoIPController).build();

    }

    @Test
    void getLocation() throws Exception {
        mockMvc.perform(post("/GeoIPTest")
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .param("ipAddress","198.168.1.1")
                    ).andExpect(status().isOk())
                    .andDo(print())
                .andExpect(jsonPath("$.ipAddress").value("198.168.1.1"))
                .andExpect(jsonPath("$.city").isNotEmpty());

    }

    @Test
    void locationListener() throws Exception {
        mockMvc.perform(post("/GeoIPTest/{userId}",anyLong())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(newGeoIP())))
                .andExpect(status().isOk())
                .andReturn();
    }

    private static GeoIP newGeoIP(){
        GeoIP geoIP = new GeoIP("198.168.1.1","United States","Fort Worth",
                "32.7818","-97.5108");
        return geoIP;
    }
}