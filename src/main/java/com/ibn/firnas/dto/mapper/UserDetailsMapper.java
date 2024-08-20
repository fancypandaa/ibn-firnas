package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {
    UserDetailsMapper INSTANCE = Mappers.getMapper(UserDetailsMapper.class);
    UserDetailsDTO userDetailsToUserDetailsDTO(UserDetails userDetails);
    UserDetails userDetailsDTOtoUserDetails(UserDetailsDTO userDetailsDTO);
}

