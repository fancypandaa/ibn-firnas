package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);
    Flight flightDTOtoFlight(FlightDTO flightDTO);
    FlightDTO flightToFlightDTO(Flight flight);
}
