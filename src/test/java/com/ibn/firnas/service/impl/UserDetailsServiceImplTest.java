package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.User;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.dto.mapper.UserDetailsMapper;
import com.ibn.firnas.service.UserDetailsService;
import com.ibn.firnas.utils.enums.Gender;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.repostiories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserDetailsServiceImplTest {
    @Mock
    private UserDetailsRepository userDetailsRepository;
    @Mock
    private UserDetailsMapper userDetailsMapper;
    @Mock
    private UserRepository userRepository;
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new UserDetailsServiceImpl(userDetailsMapper,userDetailsRepository,userRepository);
    }

    @Test
    void findUserDetailsById()  {
        UserDetails userDetails= newUserDetails();
        when(userDetailsRepository.findById(anyLong())).thenReturn(Optional.of(userDetails));
        UserDetailsDTO saved=userDetailsService.findUserDetailsById(anyLong());
        verify(userDetailsRepository, times(1)).findById(anyLong());
        assertNotEquals(userDetails.getUserId(),0);
        assertNotNull(userDetails.getUser());
    }

    @Test
    void createNewUserDetails() {
        UserDetails userDetails= newUserDetails();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(savedUser()));
        when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(userDetails);
        UserDetails savedUserDetails =userDetailsRepository.save(newUserDetails());
        verify(userDetailsRepository, times(1)).save(any(UserDetails.class));
        assertNotEquals(savedUserDetails.getUserId(),0);
        assertNotNull(savedUserDetails.getUser());
        assertEquals(savedUserDetails.getFirstName(),userDetails.getFirstName());
        assertEquals(savedUserDetails.getLicense(),userDetails.getLicense());
    }

    @Test
    void createNewUserDetailsNullViolations(){
        UserDetails userDetails= newUserDetails();
        userDetails.setLicense(null);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(savedUser()));
        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException("Make Sure License, Date Of Birth and Total Flight Hours not empty..."));
    }
    @Test
    void patchUserDetails()  {
        UserDetails updated = newUserDetails();
        updated.setJobTitle("XXX");
        updated.setLicense("555-555-222");
        when(userRepository.existsById(anyLong())).thenReturn(true);
        boolean existUser= userRepository.existsById(anyLong());
        when(userDetailsRepository.save(updated)).thenReturn(updated);
        UserDetails afterUpdate= userDetailsRepository.save(updated);
        verify(userRepository,times(1)).existsById(anyLong());
        verify(userDetailsRepository,times(1)).save(updated);
        assertNotEquals(updated.getLicense(),newUserDetails().getLicense());
    }

        private static UserDetails newUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("abc");
        userDetails.setLastName("def");
        userDetails.setAddress("aa-bb-cc");
        userDetails.setLicense("bbb-334-gdgd");
        userDetails.setGender(Gender.FEMALE);
        userDetails.setDateOfBirth("01-31-1990");
        userDetails.setJobTitle("Pilot");
        userDetails.setTotalFlightsHours(16L);
        userDetails.setUser(savedUser());
        return userDetails;
    }
    private static User savedUser(){
        User user = new User();
        user.setUserId(1L);
        user.setUserName("LOL");
        user.setEmail("user@mail.com");
        user.setPassword("88888888Abc");
        return user;
    }
}