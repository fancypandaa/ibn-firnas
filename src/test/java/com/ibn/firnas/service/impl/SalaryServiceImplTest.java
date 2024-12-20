package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.Salary;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.dto.mapper.SalaryMapper;
import com.ibn.firnas.repostiories.SalaryRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.service.SalaryService;
import com.ibn.firnas.utils.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SalaryServiceImplTest {
    @Mock
    private SalaryRepository salaryRepository;
    @Mock
    private SalaryMapper salaryMapper;
    @Mock
    private UserDetailsRepository userDetailsRepository;
    private SalaryService salaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        salaryService = new SalaryServiceImpl(salaryRepository,userDetailsRepository,salaryMapper);
    }

    @Test
    void findSalaryById() {
        Salary salary=newSalary();
        when(salaryRepository.findById(anyLong())).thenReturn(Optional.of(salary));
        SalaryDTO salaryDTO= salaryService.findSalaryById(anyLong());
        verify(salaryRepository,times(1)).findById(anyLong());
        assertNotEquals(salary.getSalaryId(),0);
        assertNotNull(salary);
    }

    @Test
    void addNewUserSalary() {
        Salary salary=newSalary();
        when(userDetailsRepository.findById(anyLong())).thenReturn(Optional.of(newUserDetails()));
        when(salaryRepository.save(any(Salary.class))).thenReturn(salary);
        Salary savedSalary= salaryRepository.save(salary);
        verify(salaryRepository,times(1)).save(salary);
        assertNotNull(newUserDetails());
        assertNotNull(salary);
        assertEquals(salary.getAvailability(),savedSalary.getAvailability());
        assertEquals(salary.getDegree(),savedSalary.getDegree());
    }

    @Test
    void updateUserSalary() {
        Salary salary=newSalary();
        when(salaryRepository.findById(anyLong())).thenReturn(Optional.of(newSalary()));
        salary.setBonus(5000.0);
        salary.setBasic(70000.0);
        when(salaryRepository.save(any(Salary.class))).thenReturn(salary);

        Salary updateUserSalary=salaryRepository.save(salary);
        verify(salaryRepository,times(1)).save(updateUserSalary);
        assertNotNull(salary);
        assertNotEquals(newSalary().getBonus(),updateUserSalary.getBonus());
        assertNotEquals(newSalary().getBasic(),updateUserSalary.getBasic());
        assertEquals(salary.getAvailability(),updateUserSalary.getAvailability());

    }
    private static Salary newSalary(){
        Salary salary=new Salary();
        salary.setBasic(50000.0);
        salary.setBonus(0.0);
        salary.setAvailability(true);
        salary.setDegree("Pilot");
        salary.setPenalties(0.0);
        salary.setCreatedAt(new Date());
        salary.setUpdatedAt(new Date());
        return salary;
    }

    private static UserDetails newUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(2L);
        userDetails.setFirstName("abc");
        userDetails.setLastName("def");
        userDetails.setAddress("aa-bb-cc");
        userDetails.setLicense("bbb-334-gdgd");
        userDetails.setGender(Gender.FEMALE);
        userDetails.setDateOfBirth("01-31-1990");
        userDetails.setJobTitle("Pilot");
        userDetails.setTotalFlightsHours(16L);
        return userDetails;
    }
}