package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.Flight;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.FlightAllDTO;
import com.ibn.firnas.dto.airCrew.FlightDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",uses = FlightMapper.class )
public interface FlightMapper {
    @Named(value = "countPatients")
    static List<Long> countPatients(Set<UserDetails> userDetails) {
        if(userDetails == null) {
            return new ArrayList<>();
        }
        return userDetails.stream().map(UserDetails::getUserId).collect(Collectors.toList());
    }
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    Flight flightDTOtoFlight(FlightDTO flightDTO);
    @Mapping(source = "userDetails", target = "userIds",qualifiedByName="countPatients")
    FlightDTO flightToFlightDTO(Flight flight);
    Flight flightAllDTOtoFlight(FlightAllDTO flightAllDTO);
    FlightAllDTO flightToFlightAllDTO(Flight flight);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightFromDTO(FlightDTO flightDTO, @MappingTarget Flight flight);


}
