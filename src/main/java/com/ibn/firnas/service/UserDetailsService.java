package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.UserDetailsDTO;

public interface UserDetailsService {
    UserDetailsDTO findUserDetailsById(Long userId);
    UserDetailsDTO createNewUserDetails(Long userId,UserDetailsDTO userDetailsDTO);
    UserDetailsDTO updateUserDetails(Long userId,UserDetailsDTO userDetailsDTO);

}
