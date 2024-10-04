package com.ibn.firnas.service;


import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;

public interface AirPlaneService {
    AirPlaneDTO findAirPlaneById(Long airPlaneId);
    AirPlaneDTO addNewAirPlane(AirPlaneDTO airPlaneDTO);
    AirPlaneDTO updateAirPlaneInfo(Long airPlaneId,AirPlaneDTO airPlaneDTO);
    AirPlane assignFlightToAirPlane(Long airPlaneId, Long flightId);

}
