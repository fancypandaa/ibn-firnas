package com.ibn.firnas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.service.UserDetailsService;
import com.ibn.firnas.utils.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import lombok.val;
@SpringBootTest
class UserDetailsControllerTest {
    private static final String JOB_TITLE= "XXX";
    private static final long userId= 2L;
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
                .andExpect(jsonPath("jobTitle").value(JOB_TITLE))
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
        when(userDetailsService.updateUserDetails(anyLong(),any(UserDetailsDTO.class))).thenReturn(updatedUserDetails());
        mockMvc.perform(put("/api/aircrew/userDetails/{userId}",userId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(updatedUserDetails()))
                )
        .andExpect(status().isOk()).andReturn();
        verify(userDetailsService,times(1))
                .updateUserDetails(userId,updatedUserDetails());
        assertEquals(updatedUserDetails().userId(),userId);

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
    public static UserDetailsDTO updatedUserDetails(){
        val userDetailsDTO = UserDetailsDTO.builder()
                .userId(userId)
                .firstName("TRT")
                .lastName("DCD")
                .address("ccc-334-vvc")
                .jobTitle("XXX")
                .dateOfBirth("01-31-1997")
                .gender(Gender.FEMALE)
                .totalFlightsHours(34L)
                .license("vv-dd-ff")
                .build();
        return userDetailsDTO;
    }
}