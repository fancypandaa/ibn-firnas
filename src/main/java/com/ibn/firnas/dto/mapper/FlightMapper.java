package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);
    Flight flightDTOtoFlight(FlightDTO flightDTO);
    FlightDTO flightToFlightDTO(Flight flight);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightFromDTO(FlightDTO flightDTO, @MappingTarget Flight flight);
}
