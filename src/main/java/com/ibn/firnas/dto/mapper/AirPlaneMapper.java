package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirPlaneMapper {
    AirPlaneMapper INSTANCE= Mappers.getMapper(AirPlaneMapper.class);
    AirPlane airPlaneDTOtoAirPlane(AirPlaneDTO airPlaneDTO);
    AirPlaneDTO airPlaneToAirPlaneDTO(AirPlane airPlane);
}
