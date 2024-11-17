package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import java.util.*;
@Mapper(componentModel = "spring")
public interface AirPlaneMapper {
    @Named("exportLastFlight")
    default Long getLastFlight(Set<Flight> flights){
        return flights.stream().iterator().next().getFlightId();
    }
    AirPlaneMapper INSTANCE= Mappers.getMapper(AirPlaneMapper.class);
    AirPlane airPlaneDTOtoAirPlane(AirPlaneDTO airPlaneDTO);
    @Mapping(source = "flights",target = "flightId" , qualifiedByName = "exportLastFlight")
    AirPlaneDTO airPlaneToAirPlaneDTO(AirPlane airPlane);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAirPlaneFromDTO(AirPlaneDTO airPlaneDTO, @MappingTarget AirPlane airPlane);
    List<AirPlaneDTO> airPlanesToAirPlaneDTOs(List<AirPlane> airPlanes);
}
