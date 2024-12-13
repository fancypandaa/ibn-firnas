package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.User;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.mapper.UserMapper;
import com.ibn.firnas.repostiories.UserRepository;
import com.ibn.firnas.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository,userMapper);
    }

    @Test
    void createNewUser() {
        User user= savedUser();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(savedUser()));
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser =userRepository.save(savedUser());
        verify(userRepository, times(1)).save(any(User.class));
        assertNotEquals(savedUser.getUserId(),0);
        assertNotNull(savedUser.getUsername());
        assertNotEquals(savedUser.getPassword(),"88888888Abc");

    }
    private User savedUser(){
        User user = new User();
        user.setUserId(1L);
        user.setUsername("LOL");
        user.setEmail("user@mail.com");
        user.setPassword(passwordEncoder.encode("88888888Abc"));
        return user;
    }
}