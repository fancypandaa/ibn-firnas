package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.User;
import com.ibn.firnas.dto.airCrew.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);
}
