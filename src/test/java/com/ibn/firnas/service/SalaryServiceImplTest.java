package com.ibn.firnas.service;

import com.ibn.firnas.domain.Salary;
import com.ibn.firnas.domain.User;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.dto.mapper.SalaryMapper;
import com.ibn.firnas.exception.CustomException;
import com.ibn.firnas.repostiories.SalaryRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.utils.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
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
    void findSalaryById() throws CustomException {
        Salary salary=newSalary();
        when(salaryRepository.findById(anyLong())).thenReturn(Optional.of(salary));
        SalaryDTO salaryDTO= salaryService.findSalaryById(anyLong());
        verify(salaryRepository,times(1)).findById(anyLong());
        assertNotEquals(salary.getSalaryId(),0);
        assertNotNull(salary);
    }

    @Test
    void addNewUserSalary() throws CustomException {
        Salary salary=newSalary();
        when(userDetailsRepository.findById(anyLong())).thenReturn(Optional.of(newUserDetails()));
        when(salaryRepository.save(any(Salary.class))).thenReturn(salary);
        Salary savedSalary= salaryRepository.save(salary);
        verify(salaryRepository,times(1)).save(salary);
        assertNotNull(newUserDetails());
        assertNotNull(salary);
        assertEquals(salary.isAvailability(),savedSalary.isAvailability());
        assertEquals(salary.getDegree(),savedSalary.getDegree());


    }

    @Test
    void updateUserSalary() throws CustomException {
        Salary salary=newSalary();
        when(salaryRepository.findById(anyLong())).thenReturn(Optional.of(newSalary()));
        salary.setBonus(BigDecimal.valueOf(5000));
        salary.setBasic(BigDecimal.valueOf(70000));
        when(salaryRepository.save(any(Salary.class))).thenReturn(salary);

        Salary updateUserSalary=salaryRepository.save(salary);
        verify(salaryRepository,times(1)).save(updateUserSalary);
        assertNotNull(salary);
        assertNotEquals(newSalary().getBonus(),updateUserSalary.getBonus());
        assertNotEquals(newSalary().getBasic(),updateUserSalary.getBasic());
        assertEquals(salary.isAvailability(),updateUserSalary.isAvailability());

    }
    private static Salary newSalary(){
        Salary salary=new Salary();
        salary.setBasic(BigDecimal.valueOf(50000));
        salary.setBonus(BigDecimal.valueOf(0));
        salary.setAvailability(true);
        salary.setDegree("Pilot");
        salary.setPenalties(BigDecimal.valueOf(0));
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