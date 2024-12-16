package com.ibn.firnas.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.airCrew.UserDTO;
import com.ibn.firnas.service.UserService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class UserControllerTest {
    private static final long userId= 2L;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;
    private UserController userController;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    @Test
    void createNewUser() throws Exception {
        mockMvc.perform(post("/api/aircrew/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(anUser()))
                ).andExpect(status().isCreated())
                .andDo(print());
    }

    public static UserDTO anUser(){
        val userDTO = UserDTO.builder()
                .userId(userId)
                .username("exampleuser")
                .email("exampleuser@mail.com")
                .password("example")
                .build();
        return userDTO;
    }
}