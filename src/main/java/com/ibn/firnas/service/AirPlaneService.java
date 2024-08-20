package com.ibn.firnas.service;


import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.dto.airCrew.AirPlaneDTO;
import com.ibn.firnas.exception.CustomException;

public interface AirPlaneService {
    AirPlaneDTO findAirPlaneById(Long airPlaneId) throws CustomException;
    AirPlaneDTO addNewAirPlane(AirPlaneDTO airPlaneDTO)throws CustomException;
    AirPlaneDTO updateAirPlaneInfo(Long airPlaneId,AirPlaneDTO airPlaneDTO)throws CustomException;
    AirPlaneDTO assignFlightToAirPlane(Long airPlaneId,Long flightId)throws CustomException;

}
