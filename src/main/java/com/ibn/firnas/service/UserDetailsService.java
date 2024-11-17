package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.UserDetailsDTO;

import java.util.*;

public interface UserDetailsService {
    UserDetailsDTO findUserDetailsById(Long userId);
    UserDetailsDTO createNewUserDetails(Long userId,UserDetailsDTO userDetailsDTO);
    UserDetailsDTO updateUserDetails(Long userId,UserDetailsDTO userDetailsDTO);
    List<UserDetailsDTO> findAllNamesLike(Optional<String> firstName, Optional<String> lastName);
}
