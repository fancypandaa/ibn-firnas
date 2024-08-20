package com.ibn.firnas.dto.mapper;


import com.ibn.firnas.domain.Location;
import com.ibn.firnas.dto.airCrew.LocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
    Location locationDTOtoLocation(LocationDTO locationDTO);
    LocationDTO locationToLocationDTO(Location location);

}
