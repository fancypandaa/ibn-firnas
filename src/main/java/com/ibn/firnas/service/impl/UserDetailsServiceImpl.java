package com.ibn.firnas.service.impl;


import com.ibn.firnas.domain.User;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.dto.mapper.UserDetailsMapper;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.repostiories.UserRepository;
import com.ibn.firnas.service.UserDetailsService;
import com.ibn.firnas.specifications.UserDetailsSpecification;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserDetailsMapper userDetailsMapper;
    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserDetailsMapper userDetailsMapper, UserDetailsRepository userDetailsRepository, UserRepository userRepository) {
        this.userDetailsMapper = userDetailsMapper;
        this.userDetailsRepository = userDetailsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsDTO findUserDetailsById(Long userId) {
        Optional<UserDetails> optionalUserDetails= userDetailsRepository.findById(userId);
        if(!optionalUserDetails.isPresent()){
            throw  new CustomNotFoundException("User Not Found");
        }
        logger.info(optionalUserDetails.get().toString());
        return userDetailsMapper.userDetailsToUserDetailsDTO(optionalUserDetails.get());
    }

    @Override
    public UserDetailsDTO createNewUserDetails(Long userId,UserDetailsDTO userDetailsDTO){
        Optional<User> optionalUser= userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new CustomNotFoundException("User Not found");
        }
        UserDetails newUserDetails= userDetailsMapper.userDetailsDTOtoUserDetails(userDetailsDTO);
        newUserDetails.setUser(optionalUser.get());
        UserDetails savedUserDetails = userDetailsRepository.save(newUserDetails);
        return userDetailsMapper.userDetailsToUserDetailsDTO(savedUserDetails);
    }

    @Override
    public UserDetailsDTO updateUserDetails(Long userId, UserDetailsDTO userDetailsDTO)  {
        Optional<UserDetails> optionalUser= userDetailsRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new CustomNotFoundException("User Not found");
        }
        UserDetails updatedUserDetails = userDetailsMapper.userDetailsDTOtoUserDetails(userDetailsDTO);
        updatedUserDetails.setUserId(optionalUser.get().getUserId());
        return userDetailsMapper.
                userDetailsToUserDetailsDTO(userDetailsRepository.save(updatedUserDetails));
    }

    @Override
    public List<UserDetailsDTO> findAllNamesLike(Optional<String> optionalFirstName, Optional<String> optionalLastName) {
        String firstName = optionalFirstName.isPresent()? optionalFirstName.get() :  "";
        String lastName = optionalLastName.isPresent()? optionalFirstName.get() : "";
        List<UserDetails> userDetails = userDetailsRepository.
                findAll(UserDetailsSpecification.nameLike(firstName,lastName));
        return userDetailsMapper.usersDetailToUserDetailDTOs(userDetails);
    }
}
