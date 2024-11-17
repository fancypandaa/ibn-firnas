package com.ibn.firnas.service;


import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import java.util.*;
public interface AirPlaneService {
    AirPlaneDTO findAirPlaneById(Long airPlaneId);
    AirPlaneDTO addNewAirPlane(AirPlaneDTO airPlaneDTO);
    AirPlaneDTO updateAirPlaneInfo(Long airPlaneId,AirPlaneDTO airPlaneDTO);
    AirPlaneDTO assignFlightToAirPlane(Long airPlaneId, Long flightId);
    List<AirPlaneDTO> findAllAirPlanesWithLastCheckTankBetween(Date from,Date to);
}
