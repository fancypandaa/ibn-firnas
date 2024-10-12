package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.Location;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.*;
@Mapper(componentModel = "spring")
public interface UserDetailsMapper {
    @Named(value = "extractCurrentLocationId")
    default Long getCurrentLocation(Set<Location> locations){
        for(Location location:locations){
            if(location.getIsActive()){
                return location.getLocationId();
            }
        }
        return locations.stream().iterator().next().getLocationId();
    }
    UserDetailsMapper INSTANCE = Mappers.getMapper(UserDetailsMapper.class);
    @Mapping(source = "salary.salaryId", target = "salaryId")
    @Mapping(source = "locations" ,target = "locationId",qualifiedByName = "extractCurrentLocationId")
    UserDetailsDTO userDetailsToUserDetailsDTO(UserDetails userDetails);
    UserDetails userDetailsDTOtoUserDetails(UserDetailsDTO userDetailsDTO);
}

