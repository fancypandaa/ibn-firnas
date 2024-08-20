package com.ibn.firnas.service;

import com.ibn.firnas.domain.User;
import com.ibn.firnas.dto.airCrew.UserDTO;

public interface UserService {
    UserDTO createNewUser(UserDTO user);
}
