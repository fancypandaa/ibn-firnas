package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.User;
import com.ibn.firnas.dto.airCrew.UserDTO;
import com.ibn.firnas.dto.mapper.UserMapper;
import com.ibn.firnas.repostiories.UserRepository;
import com.ibn.firnas.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createNewUser(UserDTO newUser)
    {
        passwordEncoder.encode(newUser.password());
        User user = userMapper.userDTOtoUser(newUser);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }
}
