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
    @Named(value = "extractUserDetailsId")
    static List<Long> extractUserDetailsId(Set<UserDetails> userDetails) {
        if(userDetails == null) {
            return new ArrayList<>();
        }
        return userDetails.stream().map(UserDetails::getUserId).collect(Collectors.toList());
    }
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    Flight flightDTOtoFlight(FlightDTO flightDTO);
    @Mapping(source = "userDetails", target = "userIds",qualifiedByName="extractUserDetailsId")
    FlightDTO flightToFlightDTO(Flight flight);
    FlightAllDTO flightToFlightAllDTO(Flight flight);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightFromDTO(FlightDTO flightDTO, @MappingTarget Flight flight);


}
