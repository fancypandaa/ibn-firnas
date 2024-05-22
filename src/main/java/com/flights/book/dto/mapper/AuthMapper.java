package com.flights.book.dto.mapper;

import com.flights.book.dto.model.AuthDTO;
import com.flights.book.model.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);
    AuthDTO authToAuthDTO(AuthUser authUser);
}
