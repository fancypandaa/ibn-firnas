package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.exception.CustomException;

public interface UserDetailsService {
    UserDetailsDTO findUserDetailsById(Long userId) throws CustomException;
    UserDetailsDTO createNewUserDetails(Long userId,UserDetailsDTO userDetailsDTO) throws CustomException;
    UserDetailsDTO updateUserDetails(Long userId,UserDetailsDTO userDetailsDTO) throws CustomException;

}
