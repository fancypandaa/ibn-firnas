package com.ibn.firnas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.utils.enums.Gender;
import com.ibn.firnas.service.UserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@SpringBootTest
class UserDetailsControllerTest {
    @Mock
    private UserDetailsService userDetailsService;
    private MockMvc mockMvc;
    private UserDetailsController userDetailsController;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDetailsController = new UserDetailsController(userDetailsService);
        mockMvc = MockMvcBuilders.standaloneSetup(userDetailsController).build();
    }

    @Test
    void getUserDetails() throws Exception {
        when(userDetailsService.findUserDetailsById(anyLong())).thenReturn(userDetails());
        mockMvc.perform(get("/api/aircrew/userDetails/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("jobTitle").value("Pilot"))
                .andExpect(jsonPath("totalFlightsHours").isNumber())
                .andDo(print());
    }

    @Test
    void createNewUserDetails() throws Exception {
        mockMvc.perform(post("/api/aircrew/userDetails/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(userDetails()))
        ).andExpect(status().isCreated())
                .andDo(print());

    }


    @Test
    void updateUserDetails() throws Exception {
        Long userId=2L;
        when(userDetailsService.updateUserDetails(2L,userDetails())).thenReturn(updatedUserDetails());
        MvcResult result2 =mockMvc.perform(patch("/api/aircrew/userDetails/{userId}",userId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(updatedUserDetails()))
                )
        .andExpect(status().isOk()).andReturn();

        verify(userDetailsService).updateUserDetails(userId,updatedUserDetails());

        assertEquals(updatedUserDetails().userId(),userId);

    }
    private static UserDetailsDTO userDetails(){
        UserDetailsDTO userDetails = new UserDetailsDTO(2L,
                "abc","def","Pilot","bbb-334-gdgd", Gender.FEMALE,"aa-bb-cc"
        ,"01-31-1990",14L);
        return userDetails;
    }
    private static UserDetailsDTO updatedUserDetails(){
        UserDetailsDTO userDetails = new UserDetailsDTO(2L,
                "abc","def","PilotI","bbb-334-XXX",Gender.FEMALE,"aa-bb-cc"
                ,"01-31-1990",30L);
        return userDetails;
    }


}