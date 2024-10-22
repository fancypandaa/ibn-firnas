package com.ibn.firnas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.service.SalaryService;
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

import java.util.Date;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@SpringBootTest

class SalaryControllerTest {
    @Mock
    private SalaryService salaryService;
    private SalaryController salaryController;
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    private static final long salaryId= 1L;
    private static final long userId= 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        salaryController = new SalaryController(salaryService);
        mockMvc = MockMvcBuilders.standaloneSetup(salaryController).build();
    }

    @Test
    void getSalaryById() throws Exception {

        when(salaryService.findSalaryById(anyLong())).thenReturn(anSalary());
        mockMvc.perform(get("/api/aircrew/salary/{salaryId}",salaryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salaryId").value(1))
                .andReturn();
        verify(salaryService,times(1)).findSalaryById(anyLong());

    }

    @Test
    void createNewUserDetails() throws Exception {
        when(salaryService.addNewUserSalary(anyLong(),any(SalaryDTO.class))).thenReturn(anSalary());
        mockMvc.perform(post("/api/aircrew/salary/{salaryId}",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(anSalary())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.salaryId").value(1))
                .andDo(print());

    }

    @Test
    void updateUserSalary() throws Exception{
        when(salaryService.updateUserSalary(anyLong(),any(SalaryDTO.class))).thenReturn(anSalaryI());
        mockMvc.perform(put("/api/aircrew/salary/{salaryId}",salaryId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(anSalary())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salaryId").value(1))
                .andDo(print());
        verify(salaryService,times(1)).updateUserSalary(anyLong(),any(SalaryDTO.class));

    }
    private static SalaryDTO anSalary(){
        val salaryDto = SalaryDTO.builder()
                .salaryId(1L)
                .basic(20000.00)
                .bonus(00.00)
                .availability(Boolean.TRUE)
                .penalties(0.0)
                .degree("A")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        return salaryDto;
    }
    private static SalaryDTO anSalaryI(){
        val salaryDto = SalaryDTO.builder()
                .salaryId(1L)
                .basic(30000.00)
                .bonus(1000.00)
                .availability(Boolean.TRUE)
                .penalties(0.0)
                .degree("B")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        return salaryDto;
    }
}